/**
 * 
 */
package me.dreamand.cloud.storage.service;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import me.dreamand.cloud.Credential;
import me.dreamand.cloud.storage.permission.Permissions;

/**
 * @author Pau Kiat Wee (mailto:paukiatwee@gmail.com)
 * @since 1.0.0
 */
public interface UploadService {
    
    /**
     * Set the permission of the file
     * PRIVATE (Default)
     * PUBLIC
     * @param permissions
     */
    void setPermission(Permissions permissions);
    
    /**
     * Set the credential for accessing the storage service
     * @param credential
     */
    void setCredential(Credential credential);
    
    /**
     * Set extra properties for different storage service provider
     * @param properties
     */
    void setProperties(Map<String, String> properties);
    
    /**
     * Upload file to cloud storage
     * @param file to be upload
     * @return the url to the file
     */
    String upload(File file);
    
    /**
     * Upload file to cloud storage
     * @param path where the file upload to
     * @param file to be upload
     * @return the url to the file
     */
    String upload(String path, File file);
    
    /**
     * Upload file via input stream
     * @param is of the file
     * @param name of the file
     * @param contentType of the file
     * @return the url to the file
     */
    String upload(InputStream is, String name, String contentType);
    
    /**
     * Upload file via input stream
     * @param path where the file upload to
     * @param is of the file
     * @param name of the file
     * @param contentType of the file
     * @return the url to the file
     */
    String upload(String path, InputStream is, String name, String contentType);
}
