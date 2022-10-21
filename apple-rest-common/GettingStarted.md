# Getting started in the Rest Common Module

[module](src/main/resources)

At this point, you have just created the Maven project.

You will edit the [definition.yaml](src/main/resources/definition.yaml).


It is best to edit this using the [swagger editor](https://editor.swagger.io/).


After you are done, save it back here. 

Building the module will create the following:

- Interfaces based on the `tags` in the swagger definition. [here](target/generated-sources/swagger/src/main/java/com/asg/services/apple/api)
- DTOs are created from the `components` in the swagger definition. [here](target/generated-sources/swagger/src/main/java/com/asg/services/apple/dto)

You will need to know about these when working on the rest server, next.