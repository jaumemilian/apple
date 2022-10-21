# Apple IMPL

This module is the core implementation of the service: Apple.

_**NOTE: VERY IMPORTANT!!**_ : This class is NOT the REST implementation. 
It has no binding code in this module. If this class begins to migrate to 
contain JSON or any REST or other binding specific code, it will need to be 
fixed.
 We may start to incorporate code validators to have build warnings if code creep.

This module uses Spring Auto-Configuration to instantiate the implementation class of the Apple interface.
See [Auto-Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-auto-configuration.html) 
for more details.


##Bootstrap properties

As you develop the implementation class, you may want to have some bootstrap parameters.
These will be read from the Application.yaml(see spring for better details) file from the SpringBoot application.
Modify the AppleServiceProperties file.

Example of a properties class.
```
public class AppleServiceProperties {

    private boolean enableCaching;
    public AppleServiceProperties() {}
    public AppleServiceProperties(boolean enableCaching) {
        this.urls = urls;
    }
    public boolean isEnableCaching() {
        return enableCaching;
    }
    public void setEnableCaching(boolean enableCaching) {
        this.enableCaching = enableCaching;
    }

    @Override
    public String toString() {
        return "PoliciesRestClientProperties{" +
                "enableCaching=" + enableCaching +
                '}';
    }
}
```

##Security
When the bean AppleService is instantiated, the SecurityContext is autowired 
and passed in the constructor. Review the `common-security` module within the services-SDK for details.

 






