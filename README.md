Cloud Storage API
=================

A API for developer to upload files to Cloud Storage such as S3, or Google Storage

Example Usage
=============
Spring configuration
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="
            http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd">
  <bean id="credential" class="me.dreamand.cloud.Credential">
    <property name="key" value="YOUR_KEY" />
    <property name="secret" value="YOUR_SECRET" />
  </bean>
  <bean class="me.dreamand.cloud.storage.service.S3UploadService">
    <property name="permission" value="PUBLIC" />
    <property name="credential" ref="credential" />
    <property name="properties">
      <map>
        <entry key="bucket" value="YOUR_BUCKET" />
        <entry key="key" value="YOUR_KEY" />
      </map>
    </property>
  </bean>
</beans>
```
Once configured, `@Inject` to your Java code to use!
```java
import me.dreamand.cloud.storage.service.UploadService;
import org.springframework.web.multipart.MultipartFile;

@Inject
UploadService uploadService;

@RequestMapping(value = "/new", method = RequestMethod.POST)
public String doUpload(MultipartFile file) {
    uploadService.upload(
        file.getInputStream(),
        file.getOriginalFilename(),
        file.getContentType());
    return "success";
}
```
