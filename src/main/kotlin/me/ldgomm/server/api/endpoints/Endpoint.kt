package me.ldgomm.server.api.endpoints

sealed class Endpoint(val path: String) {
    object RootRoute : Endpoint("/")
    object AuthorizedRoute : Endpoint("/authorized")
    object UnauthorizedRoute : Endpoint("/unauthorized")
    object GoogleTokenVerificationRoute : Endpoint("/google_token_verification")
    object AppleTokenVerificationRoute : Endpoint("/apple_token_verification")
    object ClientRoute : Endpoint("/client")
    object PartnerRoute : Endpoint("/partner")
    object OfferRoute : Endpoint("/offer")
    object SignOutRoute : Endpoint("/sign_out")
}