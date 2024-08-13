package org.acme;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginGoogleResource {
   @ConfigProperty(name = "quarkus.oidc.client-id")
    String clientId;

    @ConfigProperty(name = "quarkus.oidc.credentials.secret")
    String clientSecret;

    @ConfigProperty(name = "quarkus.oidc.redirect-uri")
    String redirectUri;

    private static final String GOOGLE_LOGOUT_URL = "https://accounts.google.com/Logout";

    @GET
    @Path("/entrar")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login() {
        String authorizationUrl = "https://accounts.google.com/o/oauth2/v2/auth" +
                "?response_type=code" +
                "&client_id=" + clientId +
                "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8) +
                "&scope=openid+profile+email";

        return Response.seeOther(URI.create(authorizationUrl)).build();
    }

    @GET
    @Path("/callback")
    @Produces(MediaType.TEXT_PLAIN)
    public Response callback(@QueryParam("code") String code) {

        if (code == null || code.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("A autoriação foi negada pelo usuário.")
                           .build();
        }

        return Response.ok("Código de autorização: " + code).build();
    }

    @GET
    @Path("/logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout() {
        return Response.seeOther(java.net.URI.create(GOOGLE_LOGOUT_URL)).build();
    }

    
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "Pong";
    }
}

