# Getting started with the INF

In this module, you need to create the Java Interfaces and the POJOs.

In the INF module, the classes are protocol agnostic.

- Create [interfaces](src/main/java/com/asg/services/apple) and the methods similar to the interfaces generated by the swagger codegen.
- Create the [POJOs](src/main/java/com/asg/services/apple/pojos) similar to the DTOs.

Initially you may consider to use the interfaces and DTOs from the REST-common. Please refrain.
You will want to grow your service to support mulitple protocols.