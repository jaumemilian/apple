# Apple rest server

This module will hold the REST Resource implementation class.  This class will implement the Swagger Generated interface.
It will be annotated with either:
- AdminResource --> Adds Security Constraint to the resource.
- UserResource --> No Security Constraint.

The war file will scan the resources with these annotations. These classes will be registered 
for the Jersey servlet in that war.

### Swagger codegen 
The REST interface class will be dynamically generated from swagger codegen and placed into the target directory.

**NOTE**: Please don't get confused with this interface and service interface in apple-inf. 
   
   - Swagger Generated REST interface is for REST 
   - Service Interface is generic (non-binding specific)
   


The AppleResource will implement the SWAGGER generated interface. Basic concept for each method in 
this class:

  1.  Take the JSON classes passed in, and convert them to the Generic POJOs that are defined in Apple-inf module.
  2.  Call the service method
  3.  Take the returned POJOs and convert them to JSON files that were generated from the Swagger codegen
  
  
  
  