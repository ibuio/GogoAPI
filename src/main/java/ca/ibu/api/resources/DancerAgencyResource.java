/**
 * @author chr0n0metriq: jk
 * 
 *         ca.ibu.api.resources
 *         DancerAgencyResource.java
 *         Sep 11, 2016
 * 
 */
package ca.ibu.api.resources;

// javax
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// metrics
import com.codahale.metrics.annotation.Timed;

// mongodb
import net.vz.mongodb.jackson.JacksonDBCollection;

// ibu
import ca.ibu.api.api.pojo.DancerAgency;

/**
 * @author jk
 *
 */

@Path("/agency")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class DancerAgencyResource {

    private JacksonDBCollection<DancerAgency, String> collection;
    
    public DancerAgencyResource(JacksonDBCollection<DancerAgency, String> agencies) {
        this.collection = agencies;
    }
 
    @POST
    @Timed
    public Response publishNewBlog(@Valid DancerAgency agency) {
        collection.insert(agency);
        return Response.noContent().build();
    }
}
