/**
 * @author chr0n0metriq: jk
 * 
 * ca.ibu.api.db
 * MongoManaged.java
 * Aug 20, 2016
 * 
 */
package ca.ibu.api.db;

// mongo
import com.mongodb.MongoClient;

// dw
import io.dropwizard.lifecycle.Managed;


/**
 * @author jk
 *
 */
public class MongoManaged implements Managed {
 
    private MongoClient mongo;
 
    public MongoManaged(MongoClient mongo) {
        this.mongo = mongo;
    }

    /* (non-Javadoc)
     * @see io.dropwizard.lifecycle.Managed#start()
     */
    @Override
    public void start() throws Exception {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see io.dropwizard.lifecycle.Managed#stop()
     */
    @Override
    public void stop() throws Exception {
        mongo.close();
    }

}
