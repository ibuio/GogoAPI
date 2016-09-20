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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

// bson
import org.bson.Document;
import org.mongodb.morphia.Datastore;

// metrics
import com.codahale.metrics.annotation.Timed;
import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;

// mongodb
import static com.mongodb.client.model.Filters.*;

// ibu
import ca.ibu.api.api.pojo.DancerAgency;

/**
 * @author jk
 *
 *         Resource to manage Dancer Agencies. Calls are public
 */

@Path("/agency")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class DancerAgencyResource {

    // private JacksonDBCollection<DancerAgency, String> collection;
    //private MongoCollection<Document> collection;
    private Datastore datastore;

    // public DancerAgencyResource(JacksonDBCollection<DancerAgency, String> agencies) {
    // this.collection = agencies;
    // }
    
    //public DancerAgencyResource(MongoCollection<Document> agencies) {
    //    this.collection = agencies;
    //}
    public DancerAgencyResource(Datastore datastore) {
        this.datastore = datastore;
    }

    /**
     * @param id
     * @return
     */
//    @GET
//    @Path("{id}")
//    @Timed
//    public Response getDancerAgency(@PathParam("id") String id) {
//        Document doc = Document.parse(this.collection.find(eq("id", id)).first().toJson());
//
//        if (doc != null) {
//            return Response.status(Status.FOUND).entity(doc).build();
//        }
//        else
//            return Response.status(Status.NO_CONTENT).build();
//    }

    /**
     * @return
     */
//    @GET
//    @Timed
//    public Response getDancerAgencies(@QueryParam("phone") String phone) {
//        // now use a range query to get a larger subset
//        Block<Document> printBlock = new Block<Document>() {
//
//            @Override
//            public void apply(final Document document) {
//                System.out.println(document.toJson());
//            }
//        };
//        collection.find(eq("phone", phone)).forEach(printBlock);
//
//        if (printBlock != null) {
//            return Response.status(Status.FOUND).entity(collection).build();
//        }
//        else
//            return Response.status(Status.NO_CONTENT).build();
//    }

    /**
     * @param agency
     * @return
     */
    @POST
    @Timed
    public Response createDancerAgency(@Valid DancerAgency agency) {

        datastore.save(agency);
        return Response.status(Status.CREATED).entity(agency).build();
        
        
//        Gson gson = new Gson();
//
//        // Parse to bson document and insert
//        Document doc = Document.parse(gson.toJson(agency));
//
//        try {
//            collection.insertOne(doc);
//        }
//        catch (MongoWriteException | MongoWriteConcernException e) {
//            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
//        }
//        return Response.status(Status.CREATED).entity(agency).build();
    }
}
