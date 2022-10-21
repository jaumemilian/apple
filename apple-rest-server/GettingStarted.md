# Getting started with the Rest Server

There are two parts for this.

[jump](src/main/java/com/asg/services/apple)


## Create the Resource from the Swagger generated interface

_Part One_
- Create a resource class that implements the interface.
- Annotate the class with  `@UserResource`, `@AdminResource` or `@Resource`.

For the methods, throw the following exception.
`NotImplementedException`

We will fill in the implementation later. Now go to the INF module.

## Fill in the implementation

_Part Two_

At this point, you have all the java interfaces done, the pojos, the skeletal java 
implementations done. You even have the REST client classes completed.

Now we need to implement the methods.

**NOTE: Never interact with java implementation directly!**

The concept is the following:
- Keep these methods VERY LIGHT
- Accept a DTO
- Convert to POJO
- Issue service request
- Convert POJO response to DTO

Don't forget to write your [unit tests](src/test/java/com/asg/services/apple).