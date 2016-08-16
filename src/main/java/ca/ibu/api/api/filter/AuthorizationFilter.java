/**
 * @author chr0n0metriq: jk
 * 
 * ca.ibu.api.api.filter
 * AuthorizationFilter.java
 * Aug 15, 2016
 * 
 */
package ca.ibu.api.api.filter;

// java
import java.io.IOException;

// javax
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// ibu
import ca.ibu.api.api.annotation.Secured;


/**
 * @author jk
 *
 */
@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    SecurityContext securityContext;
    
    static final Logger LOG = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Context
    private ResourceInfo resourceInfo;

    /* (non-Javadoc)
     * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.ContainerRequestContext)
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // TODO Auto-generated method stub

    }

}
