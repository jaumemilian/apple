# Getting started with the REST Client

[jump](src/main/java/com/asg/services/apple)

In this module, you will create the implementation classes that communicate to it's REST service.

The logic of these methods are the reversal of the REST Server implementation.

- Keep these methods VERY LIGHT.
- Accept a POJO inbound.
- Convert to a DTO.
- Issue REST Request.
- If response is good, convert DTO Response to POJO and return.
- If response is bad, convert the HTTP Error code to the proper exception.

The reason why we need to translate error codes to exception is because we don't want the 
developer, using our service, to have to make code changes when calling our service embedded in their product or via rest.
From their perspective, the interaction must always be the same.

Don't forget to write your [unit tests](src/test/java/com/asg/services/apple).