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
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// javax
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;

// slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// ibu
import ca.ibu.api.api.Enum.RoleEnum;
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

        LOG.info("filter()");
        securityContext = requestContext.getSecurityContext();
        boolean isAllowed = false;

        Class<?> resourceClass = resourceInfo.getResourceClass();
        List<RoleEnum> classRoles = extractRoles(resourceClass);

        // Get the resource method which matches with the requested URL
        // Extract the roles declared by it
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<RoleEnum> methodRoles = extractRoles(resourceMethod);

        try {

            // Check if the user is allowed to execute the method
            // The method annotations override the class annotations
            if (methodRoles.isEmpty()) {
                LOG.info("Checking permissions on CLASS level");
                isAllowed = checkPermissions(classRoles, requestContext);
            } else {
                LOG.info("Checking permissions on METHOD level");
                isAllowed = checkPermissions(methodRoles, requestContext);
            }

            // Throw an Exception if the user has not permission to execute the method
            if(isAllowed == false) {
                LOG.warn("USER IS NOT ALLOWED TO COMPLETE THE REQUEST. ABORT.");
                ResponseBuilder builder = null;
                String response = "User not allowed to perform this request";
                builder = Response.status(Response.Status.FORBIDDEN).entity(response);
                throw new WebApplicationException(builder.build());
            }

        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }
        
    }

    /**
     * @param annotatedElement
     * @return
     */
    private List<RoleEnum> extractRoles(AnnotatedElement annotatedElement) {
        //LOGGER.traceEntry();
        
        if (annotatedElement == null) {
            return new ArrayList<RoleEnum>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<RoleEnum>();
            } else {
                RoleEnum[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }
    /**
     * @param allowedRoles
     * @return
     * @throws Exception
     */
    private boolean checkPermissions(List<RoleEnum> allowedRoles,ContainerRequestContext requestContext) throws Exception {
        //LOGGER.traceEntry();

        boolean isAllowed = false;
        String role = (String) requestContext.getProperty("role");

        // Check if the user contains one of the allowed roles
        // loop on allowedRoles List and if there is a match, set isAllowed to true
        for(RoleEnum roleEnum : allowedRoles) {
            String strRoleEnum = (String.valueOf(roleEnum));
            if(strRoleEnum.equalsIgnoreCase(role)) {
                //LOGGER.info("Role {} is allowed to perform the request!", roleName);
                isAllowed = true;
                break;
            }
        }

        return isAllowed;
    }

}
