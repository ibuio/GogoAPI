/**
 * @author chr0n0metriq: jk
 * 
 *         ca.ibu.api.api.filter
 *         AuthenticationFilter.java
 *         Aug 15, 2016
 * 
 */
package ca.ibu.api.api.filter;

// java
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
// import java.util.Base64;
import java.util.Map;
import java.util.regex.Pattern;

// javax
import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// auth0
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

// ibu
import ca.ibu.api.api.annotation.Secured;
import ca.ibu.api.client.Auth0JerseyClient;

/**
 * @author jk
 *
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private JWTVerifier jwtVerifier;
    static final Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);
    private Auth0JerseyClient auth0Client;

    @Context
    HttpServletRequest  request;

    /*
     * (non-Javadoc)
     * 
     * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.ContainerRequestContext)
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String token = null;
        String username = null;
        Map<String, Object> decoded = null;
        Client client;
        //Client client = ClientBuilder.newClient();
        Response resp = null;
        String strJUser = null;
        
        ClientConfig clientConfig = new ClientConfig();
        client = ClientBuilder.newClient(clientConfig);
        auth0Client = new Auth0JerseyClient(client);
        
        
        //new Base64(true);
        // instanciate token verifier object
        // Base64.getDecoder().decode(encoded);
        // jwtVerifier = new JWTVerifier(Base64.getDecoder().decode(System.getenv("AUTH0_CLIENT_SECRET")), System.getenv("AUTH0_CLIENT_ID"));
        //jwtVerifier = new JWTVerifier(Base64.decodeBase64(System.getenv("AUTH0_CLIENT_SECRET")), System.getenv("AUTH0_CLIENT_ID"));

        try {
            // get token from header
            token = getToken(requestContext);

            // Validate the token
            //decoded = validateToken(token);
            
            strJUser = auth0Client.getUser(token);
            if(strJUser != null) {
                LOG.debug("User access_token is valid. User info: " +  strJUser);
            }
            else {
                String strJError = "{\"message\":\"The authentication token could not be verified.\"}";
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).type("application/json").entity(strJError).build());
            }
        }
        catch (Exception e) {
            LOG.error("Exception while validating token.", e);
            String strJError = "{\"message\":\"The authentication token could not be verified.\"}";
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).type("application/json").entity(strJError).build());
        }
    }

    /**
     * @param requestContext
     * @return
     * @throws NotAuthorizedException
     */
    private String getToken(ContainerRequestContext requestContext) throws NotAuthorizedException {
        // logger.traceEntry();
        String token = null;

        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            LOG.warn("Authorization header must be provided");
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String[] parts = authorizationHeader.split(" ");

        if (parts.length != 2) {
            LOG.warn("Unauthorized: Format is Authorization: Bearer [token]");
            throw new NotAuthorizedException("Unauthorized: Format is Authorization: Bearer [token]");
        }

        String scheme = parts[0];
        String credentials = parts[1];

        Pattern pattern = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(scheme).matches()) {
            token = credentials;
        }

        return token;
    }
    
    /**
     * @param token
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalStateException
     * @throws IOException
     * @throws SignatureException
     * @throws JWTVerifyException
     */
    private Map<String, Object> validateToken(String token) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, IOException, SignatureException, JWTVerifyException {
        //logger.traceEntry();
        return jwtVerifier.verify(token);
    }

}
