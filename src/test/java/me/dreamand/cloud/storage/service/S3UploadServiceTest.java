/**
 * 
 */
package me.dreamand.cloud.storage.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import me.dreamand.cloud.Credential;
import me.dreamand.cloud.storage.permission.Permissions;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Pau Kiat Wee (mailto:paukiatwee@gmail.com)
 *
 */
@Ignore
public class S3UploadServiceTest {
    
    UploadService target = new S3UploadService();
    Credential credential = new Credential("YOUR_KEY", "YOUR_SECRET");
    String bucket = "YOUR_BUCKET";
    String folder = "test";
    
    @Before
    public void before() {
        target.setCredential(credential);
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("bucket", bucket);
        properties.put("key", folder);
        target.setProperties(properties);
        target.setPermission(Permissions.PUBLIC);
    }
    
    @Test
    public void uploadWithFile() throws IOException {
        String result = target.upload(createSampleFile());
        System.out.println(result);
    }
    
    @Test
    public void uploadWithInputStream() throws IOException {
        File file = createSampleFile();
        InputStream is = new FileInputStream(file);
        String result = target.upload(is, file.getName(), "text/plain");
        System.out.println(result);
    }

    private File createSampleFile() throws IOException {
        File file = File.createTempFile("aws-java-sdk-", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.write("01234567890112345678901234\n");
        writer.write("!@#$%^&*()-=[]{};':',.<>/?\n");
        writer.write("01234567890112345678901234\n");
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.close();

        return file;
    }
    
}
