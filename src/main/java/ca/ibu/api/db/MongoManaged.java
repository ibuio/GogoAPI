/**
 * @author chr0n0metriq: jk
 * 
 * ca.ibu.api.db
 * MongoManaged.java
 * Aug 20, 2016
 * 
 */
package ca.ibu.api.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// mongo
import com.mongodb.MongoClient;

// dw
import io.dropwizard.lifecycle.Managed;


/**
 * @author jk
 *
 */
public class MongoManaged implements Managed {

    static final Logger LOG = LoggerFactory.getLogger(MongoManaged.class);
 
    private MongoClient mongoClient;
 
    public MongoManaged(MongoClient mongo) {
        this.mongoClient = mongo;
    }

    /* (non-Javadoc)
     * @see io.dropwizard.lifecycle.Managed#start()
     */
    @Override
    public void start() throws Exception {
        LOG.info("MongoClient is starting up...");
        
        //LOG.info(mongoClient.getAddress().getHost());
    }

    /* (non-Javadoc)
     * @see io.dropwizard.lifecycle.Managed#stop()
     */
    @Override
    public void stop() throws Exception {
        LOG.info("MongoClient is being shut down...");
        mongoClient.close();
    }

}
