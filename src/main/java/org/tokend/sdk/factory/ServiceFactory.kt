package org.tokend.sdk.factory

import okhttp3.OkHttpClient
import org.tokend.sdk.api.general.GeneralService
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.signing.SignInterceptor
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.tfa.TfaInterceptor
import org.tokend.sdk.tfa.TfaVerificationService
import org.tokend.sdk.utils.CookieJarProvider
import org.tokend.sdk.utils.TimeCorrectionProvider
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*

class ServiceFactory(private val url: String,
                     private val userAgent: String? = null) {
    fun getTfaVerificationService(): TfaVerificationService {
        return getCustomService(TfaVerificationService::class.java,
                HttpClientFactory().getBaseHttpClientBuilder(
                        userAgent = userAgent
                ).build())
    }

    @JvmOverloads
    fun <T> getCustomService(serviceClass: Class<T>,
                             requestSigner: RequestSigner? = null,
                             tfaCallback: TfaCallback? = null,
                             cookieJarProvider: CookieJarProvider? = null): T {
        val client =
                HttpClientFactory().getBaseHttpClientBuilder(
                        cookieJarProvider = cookieJarProvider,
                        userAgent = userAgent
                )
                        .addInterceptor(
                                TfaInterceptor(getTfaVerificationService(),
                                        tfaCallback)
                        )
                        .apply {
                            if (requestSigner != null) {
                                addInterceptor(
                                        SignInterceptor(
                                                url,
                                                requestSigner,
                                                getTimeCorrectionProvider(),
                                                SIGNATURE_VALID_SEC
                                        )
                                )
                            }
                        }
                        .build()

        val retrofit = getBaseRetrofitConfig(client).build()

        return retrofit.create(serviceClass)
    }

    fun <T> getCustomService(serviceClass: Class<T>, httpClient: OkHttpClient): T {
        return getBaseRetrofitConfig(httpClient)
                .build()
                .create(serviceClass)
    }

    private fun getBaseRetrofitConfig(httpClient: OkHttpClient)
            : Retrofit.Builder {
        return Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonFactory().getBaseGsonConverterFactory())
                .baseUrl(url)
                .client(httpClient)
    }

    private fun getTimeCorrectionProvider(): TimeCorrectionProvider {
        return object : TimeCorrectionProvider {
            override fun getTimeCorrection(): Long {
                return timeCorrections.getOrPut(url) {
                    return@getOrPut try {
                        val currentTime = Date().time / 1000
                        val serverTime = getCustomService(GeneralService::class.java)
                                .getSystemInfo()
                                .execute()
                                .body()
                                .currentTime

                        serverTime - currentTime
                    } catch (e: Exception) {
                        e.printStackTrace()
                        0L
                    }
                }
            }
        }
    }

    companion object {
        // Time corrections by URL.
        private var timeCorrections = mutableMapOf<String, Long>()

        const val SIGNATURE_VALID_SEC = 60
    }
}