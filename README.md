Admin API Project:

Software Pre-Requisites:

Java - Java 11 and above, can be downloaded here https://www.java.com/en/download/ or https://developer.ibm.com/languages/java/semeru-runtimes/downloads/ and installed (how to - https://www.java.com/en/download/help/download_options.html)

Maven - Can use the latest version and can be downloaded here https://maven.apache.org/download.cgi and installed (how to - https://www.baeldung.com/install-maven-on-windows-linux-mac)

Java Technology used: Spring Boot

Download the code from the project https://github.com/nikhilkowri/admin-api and unzip it. Go to admin-api folder of the project.

How to Build and Run the project

1. Open a command prompt or terminal and navigate to the root directory of your Spring Boot project (admin-api folder of the project).
2. Use the following command to build a jar file:
mvn clean package
This will build the project, run the tests and package it into a jar file in the "target" directory.

3.To run the Spring Boot application, use the following command:
java -jar target/admin-api-0.0.1-SNAPSHOT.jar
or
mvn spring-boot:run

The embedded tomcat server will start the application listening at port 8080.
The admin api documentation is available at http://localhost:8080/swagger-ui/.
The admin api can be accessed using http://localhost:8080/admin/users-with-posts in any browser. The user information with their related posts data is displayed in json format.

4. Application can be stopped using 
mvn spring-boot:stop or ctrl + z command.


Note:- 
If port 8080 is already in use then u will get an error when u start the spring boot app, then change the port following the below steps 
1. open admin-api/src/main/resources/application.properties in a text editor.
2. add server.port=9090 or any other port number in applications.properties and save the file.
3. Go back to - How to Build and Run the project and redo the 3 steps.
