/**
 * @author chr0n0metriq: jk
 * 
 *         ca.ibu.api.health
 *         MongoHealthCheck.java
 *         Aug 20, 2016
 * 
 */
package ca.ibu.api.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 * @author jk
 *
 */
public class MongoHealthCheck extends HealthCheck {

    static final Logger LOG = LoggerFactory.getLogger(MongoHealthCheck.class);

    private MongoClient mongoClient;


    /**
     * @param _mongoClient
     */
    public MongoHealthCheck(MongoClient _mongoClient) {
        this.mongoClient = _mongoClient;
    }

    /* (non-Javadoc)
     * @see com.codahale.metrics.health.HealthCheck#check()
     */
    @Override
    protected Result check() throws Exception {
        LOG.debug("CHECK");

        MongoDatabase db = this.mongoClient.getDatabase(System.getenv("MONGO_DB"));
        if (db != null)
            return Result.healthy();
        else
            return Result.unhealthy("Cannot connect to " + System.getenv("MONGO_DB") + " DB.");
    }

}
