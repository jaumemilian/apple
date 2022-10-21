# Apple INF

This module is for the Apple interface and the POJOs.

_**NOTE: VERY IMPORTANT!!**_ :This is NOT related to REST. 
PLEASE DONT MAKE THAT MISTAKE. No Binding related classes.

Standard implementation classes are:
* [AppleServiceImpl](../apple-impl/README.md)
* [AppleServiceMocker](../apple-mocker/README.md)
* [AppleRestClient](../apple-rest-client/README.md)

Notice that the interface is annotated with `@AsgService`. This is used with the ASG Intelij
plugin. Please see the SDK documentation for the plugin use.

As you develop the your service, Apple, you will have objects 
passed in to methods or returned. These classes should go in the pojos directory.

These pojos are not related to REST or any other protocol binding. 
DON'T pass any of the SWAGGER generated code in the interface.

