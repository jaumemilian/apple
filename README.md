# Apple service


## Overview

The service is the code that does the work.

REST is the binding to get access to the service.

In the future, there could be other bindings such as gRPC and Message bus, 
but it doesn't change the service implementation.

The service implementation is based on an interface. In fact, there are several implementations.
- apple-impl   -- The real service implementation.
- apple-mocker -- The mocking implementation of the interface. Useful when creating unit tests and rapid development.
- apple-rest-client -- The rest client implementation of the interface. When communicating with the service over rest, this class will be used to communicate with the rest server.

##How to use

You don't want to instantiate these classes directly. 

As you can see from the example below, there is no mention of these classes, only the interface.
Spring will autowire. All the client has to do is depend on the proper implementation
module. They can change the dependency to any other auto-configurable module with implementations of the 
interface AppleService.

Example of client code:

```java
public class MyClient{
   
    @Autowired
    private AppleService service;
    
    
    public String myCall(){
        return service.getName();
    }
}

```





## Common service functionality

## Packaging Specific
* [Apple ear](./apple-ear/README.md)  -- Module that builds the EAR file
* [Apple war](./apple-war/README.md)  -- Module that builds the WAR file.

## Service 
* [Apple inf](./apple-inf/README.md)  -- Module that builds the Service Interface.
* [Apple impl](./apple-impl/README.md)  -- Module that builds the real implementation of the interface.


## Rest Specific
* [Apple rest common](./apple-rest-common/README.md)  -- holds all the common rest classes used by both the rest client and rest server modules.
* [Apple rest server](./apple-rest-server/README.md)  -- holds the implmention class for the REST resource and Swagger generated classes. This is very light-weigh.
* [Apple rest client](./apple-rest-client/README.md)  -- holds the implementation class of the service interface. This will be able to convert from service POJOs to REST DTOs and then call rest server.

## Testing specific
* [Apple mocker](./apple-mocker/README.md)  -- holds the implementation of the mocked implementation of the service interface.
* [Apple test](./apple-test/README.md)  -- Holds test related classes.

## Helm Scripts for Kubernetes deployment
* [Apple](./apple/README.md)  -- holds the helm script details for the apple service.

