/**
 * @author chr0n0metriq: jk
 * 
 *         ca.ibu.api.api.Enum
 *         RoleEnum.java
 *         Aug 15, 2016
 * 
 */
package ca.ibu.api.api.Enum;

/**
 * @author jk
 *
 */
public enum RoleEnum {
    gogoadmin("gogoadmin"),         // role pour les admin gogo
    gogomanager("gogomanager"),     // role pour les gestionnaires gogo
    clubadmin("clubadmin"),         // role pour les gestionnaires de club
    restuser("restuser"),           // role utilise par les taches automatisees
    dancer("dancer"),               // dancer, obviously
    passwordless("passwordless");   // role for password less users

    public String value;

    private RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
