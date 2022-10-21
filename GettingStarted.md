# Getting started with your new service

Congratulations! You are on your way to developing a great service. Let's help you on your journey. 

## Overview

You used the SDK's archetype to create your service, apple. You should understand what the modules are that were created.

|Module   |Description   |
|---|---|
| [apple](apple)   | This directory holds the files used for helm chart.   |
| [apple-impl](apple-impl) | This module holds the implementation classes for the service. |
| [apple-inf](apple-inf) | This module holds the interfaces and pojos for the service |
| [apple-rest-client](apple-rest-client) | This module holds the classes used to communicate to the apple REST Resource. |
| [apple-rest-common](apple-rest-common) | This module holds the common classes between the rest server and the rest clent module. This will also hold the swagger definition file.|
| [apple-rest-server](apple-rest-server) | This module holds the resource classes that will be exposed. |
| [apple-test](apple-test) |  |
| [apple-war](apple-war) | This is the war module for the service. |



If you are just getting started creating a service, you should read each getting started sections in the following order:
Before going on, in the terminal, `mvn clean install`.<br/>
Then, refresh the Maven project with IntelliJ.

- <b>[Getting Started](apple-rest-common/GettingStarted.md) in the apple-rest-common.</b>   Create your swagger.
- <b>[GettingStarted](apple-rest-server/GettingStarted.md) in the apple-rest-server.</b> Create your Interfaces and stub out the methods.
- <b>[GettingStarted](apple-inf/GettingStarted.md) in the apple-inf.</b> Create the common POJOs and Java interfaces for you service.
- <b>[GettingStarted](apple-impl/GettingStarted.md) in the apple-impl.</b> Create the stubbed out implementation for the java interfaces.
- <b>[GettingStarted](apple-rest-client/GettingStarted.md) in the apple-rest-client.</b> Create the full implementation for the rest client.
- <b>[GettingStarted](apple-rest-server/GettingStarted.md) in the apple-rest-server.</b> Finish the implementation of the rest server.
- <b>[GettingStarted](apple-impl/GettingStarted.md) in the apple-impl.</b> Developing the implementation class.


