/**
 * @author chr0n0metriq: jk
 * 
 * ca.ibu.api.api
 * DancerAgency.java
 * Aug 14, 2016
 * 
 */
package ca.ibu.api.api.pojo;

// java
import java.io.Serializable;
import java.util.UUID;

// bson
import org.bson.types.ObjectId;

// hibernate
import org.hibernate.validator.constraints.NotBlank;

// mongodb
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;

/**
 * @author jk
 *
 */
@Entity("agencies")
@Indexes(
        @Index(fields = @Field("name"))
       )
public class DancerAgency{


    @Id
    private ObjectId id;
    
    @NotBlank
    //@Property("a_different_name") // annotiation @Property would be use to serialize property with a different name 
    private String name;
    
    @NotBlank
    private String address;
    
    @NotBlank
    private String phone;
    
    // @Reference for a property that references another pojo

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getAddress() {
        return address;
    }

    
    public void setAddress(String address) {
        this.address = address;
    }

    
    public String getPhone() {
        return phone;
    }

    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    public ObjectId getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DancerAgency other = (DancerAgency) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        }
        else if (!address.equals(other.address))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        }
        else if (!phone.equals(other.phone))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DancerAgency [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + "]";
    }
    
}
