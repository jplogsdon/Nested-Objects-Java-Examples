
# nestedobjects.com

Example CI and SutProxy components for http://www.nestedobjects.com 
## Installation
1. In your Eclipse workspace:
>git clone https://github.com/jplogsdon/Nested-Objects-Java-Examples.git
2. Create a new Java Project. Select 'User default location', and select the newly created project which will auto-populate the project name.
3. On the new project, right-click and select 'configure Maven Project'. Your project should show no errors.

## Running the Spring Boot HTTP Server
These instructions use Java SE 1.8.

1. (optional) Change the server port in the src/test/resources/application.properties Default is 8084
2. Right click the file src/test/java/com/nestedobjects/sutproxy/RESTFulServer.java then run as an application

You are now able to execute tests from Nested Objects.

You can also create an executable war file by running the Maven package goal, and then in the target directory execute the war: 
1. Run => Maven package
2. java -jar -Dserver.port=8084 --add-modules java.xml.bind nestedobjects-0.0.1-SNAPSHOT.war

This project contains example example CI and SutProxy components that are to be used with the NestedObjects.

The SutProxy is a Spring Boot application consisting of an HTTP server. When you start
it, it will search in its component scan path to find the RESTFul controller or service proxy.

The CI component is a JUnit test case that is called from a continous integration server whenever code is checked in. 
For a high level description see the Architecture description on http://www.nestedobjects.com/index.html#architecture.

For a detailed description read through the code in both the CI and SutProxy subdirectories.

Introduction
The NestedObjects project contains Ci, SutProxy, and Dialog code. Under the src/main/com,nestedobjects.* directories
you will find the code that you can use in your project, while the code under the src/test/com.nestedobjects.* 
directories you will find example code that implements example CI, SutProxy, and Dialogs. 

This project contains the .project and .classpath files to you can start up the Spring server with minimal
by right-clicking the src/main/com.nestedobjects.sutproxy.RESTFulServer and selecting run.
