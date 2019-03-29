# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

Please check our [developers guide](https://gitlab.com/tokend/developers-guide)
for further information about branching and tagging conventions.

## [2.2.0] 2019-03-29

### Added
- `op_no_entry` error code to `TransactionFailedException`

### Fixed
- Missing paging params for key-value page get method in API v3
- Missing fields in `MatchedOrder` model

## [2.1.0] 2019-03-07

### Added
- All TLS suites support in in HTTP client
- Extensions with typed getters for details of 
`ReviewableRequestResource` (`.getTypedRequestDetails`)
 and `OperationResource` (`.getTypedDetails`)

### Fixed
- Actualized documents API
- Actualized reviewable requests API V3

## [2.0.0] 2019-02-28

### Added
- `v3` APIs
- Complete update password method to the `KeyServer`
- Ability to submit `Transaction` itself without conversion to `Base64`
- `SaleState` enum
- `BlobType` enum
- `.bitmask` extension for `Int` collections

### Changed
- Wallet library version to `3.0.1`
- Minimal supported Horizon version to `3.0.1`
- Minimal supported API version to `3.0.0`
- Params required for password change/recovery 
- ProGuard rules

### Deprecated
- APIs replaced by `v3` and related entities

### Removed
- `forceContentType` param of `TokenDApi`, now it's `true` by default
- `accessTypes` from `AuthRequest`

[2.0.0]: https://github.com/tokend/kotlin-sdk/compare/1.2.18...2.0.0
[2.1.0]: https://github.com/tokend/kotlin-sdk/compare/2.0.0...2.1.0
[2.2.0]: https://github.com/tokend/kotlin-sdk/compare/2.1.0...2.2.0