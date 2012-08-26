/**
 * 
 */
package me.dreamand.cloud;

/**
 * @author Pau Kiat Wee (mailto:paukiatwee@gmail.com)
 *
 */
public class Credential {
    
    private String key;
    private String secret;
    
    /**
     * 
     */
    public Credential() {
        super();
    }
    
    /**
     * @param key
     * @param secret
     */
    public Credential(String key, String secret) {
        super();
        this.key = key;
        this.secret = secret;
    }
    
    
    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }
    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }
    /**
     * @return the secret
     */
    public String getSecret() {
        return secret;
    }
    /**
     * @param secret the secret to set
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }
    

}
