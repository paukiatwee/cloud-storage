/**
 * 
 */
package me.dreamand.cloud.storage.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import me.dreamand.cloud.Credential;
import me.dreamand.cloud.storage.permission.Permissions;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * A AWS S3 implementation for the upload service
 * 
 * @author Pau Kiat Wee (mailto:paukiatwee@gmail.com)
 * @since 1.0.0
 */
public class S3UploadService implements UploadService {
    
    private AmazonS3 client;
    private Permissions permissions = Permissions.PRIVATE;
    private String bucket;
    private String key;
    public static final String URL_PATTERN = "https://%s.s3.amazonaws.com/%s%s";
    

    /**
     * 
     */
    public S3UploadService() {
        super();
        client = new AmazonS3Client();
    }

    @Override
    public void setPermission(Permissions permissions) {
        this.permissions = permissions;
    }
    
    @Override
    public void setCredential(Credential credential) {
        AWSCredentials awsCredentials = new BasicAWSCredentials(credential.getKey(), credential.getSecret());
        client = new AmazonS3Client(awsCredentials );
    }

    @Override
    public String upload(File file) {
        PutObjectRequest request = new PutObjectRequest(getBucket(), getKey() + file.getName(), file);
        switch (permissions) {
        case PRIVATE:
            // do nothing, default is private
            break;
        case PUBLIC:
            request.setCannedAcl(CannedAccessControlList.PublicRead);
            break;
        }
        client.putObject(request);
        return String.format(URL_PATTERN, getBucket(), getKey(), file.getName());
    }
    
    @Override
    public String upload(InputStream is, String name, String contentType) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        long length = 0;
        try {
            length = is.available();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        metadata.setContentLength(length);
        PutObjectRequest request = new PutObjectRequest(getBucket(), getKey() + name, is, metadata);
        switch (permissions) {
        case PRIVATE:
            // do nothing, default is private
            break;
        case PUBLIC:
            request.setCannedAcl(CannedAccessControlList.PublicRead);
            break;
        }
        client.putObject(request);
        return String.format(URL_PATTERN, getBucket(), getKey(), name);
    }
    
    /**
     * Set the AWS S3 properties
     * <code>bucket</code> for the bucket name.
     * <code>key</code> for the key of the file, which is the folder where the file will
     * upload to, default value is empty string.
     */
    @Override
    public void setProperties(Map<String, String> properties) {
        bucket = properties.get("bucket");
        key = properties.get("key");
        if(bucket == null) {
            throw new IllegalArgumentException("\"bucket\" is not set, this is required property.");
        }
        if(key == null) {
            key = "";
        } else if(!key.endsWith("/")) {
            key = key + "/";
        }
    }
    
    protected String getBucket() {
        return bucket;
    }
    
    protected String getKey() {
        return key;
    }

}
