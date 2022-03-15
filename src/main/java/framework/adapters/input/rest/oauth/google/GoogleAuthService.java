package framework.adapters.input.rest.oauth.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class GoogleAuthService {

    @ConfigProperty(name = "GOOGLE_OAUTH_CLIENT_ID")
    String clientId;
    @ConfigProperty(name = "GOOGLE_OAUTH_CLIENT_SECRET")
    String clientSecret;

    private GoogleAuthorizationCodeFlow authorizationCodeFlow;
    private String redirectUrl;

    @PostConstruct
    public void init() {
        redirectUrl = "http://localhost:8080/oauth/google/callback";

        this.authorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                clientId,
                clientSecret,
                List.of("openid", "email", "profile")
        ).build();
    }

    public String authorize() {
        return buildAuthorizationUrl();
    }

    public void exchangeAuthCode(String code) {
        try {
            var tokenResponse = authorizationCodeFlow.newTokenRequest(code)
                    .setRedirectUri(redirectUrl)
                    .execute()
                    .parseIdToken()
                    .getPayload();

            //extract user info from tokenResponse and create account
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String buildAuthorizationUrl() {
        return authorizationCodeFlow.newAuthorizationUrl()
                .setRedirectUri(redirectUrl)
                .set("nonce", "someNonce")
                .build();
    }
}
