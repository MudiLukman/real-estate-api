package framework.adapters.input.rest.oauth.google;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("oauth/google")
public class GoogleAuthResource {

    @Inject GoogleAuthService googleAuthService;

    @GET
    @Path("/authorize")
    public Response authorize() {
        return Response.seeOther(URI.create(googleAuthService.authorize())).build();
    }

    @GET
    @Path("/callback")
    public void handleCallback(@QueryParam("code") String code, @QueryParam("state") String state) {
        googleAuthService.exchangeAuthCode(code);
    }
}
