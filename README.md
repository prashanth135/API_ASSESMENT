# Project Structure
- Programming Language: JAVA 8
- IDE: Eclipse
- Create a Maven project to build the program;
- Add all dependences in pom.xml regarding some frameworks that I used such as Cucumber to manage BDD, Rest-assured to call the web-service REST and added a json-schema-validator to validate JSON response format;

## BDD (Feature file / Step definition)
BDD requires a feature file to invoke the step definitions:
- Create the scenarios in feature file as per the requirements, so each step in feature file has to match a step definition in class file;
- Following the BDD practices for coding;
- Using the special annotation like "@BeforeClass" which is the first method to run for each scenario. Moreover, this is the right place to set up the URI (endpoint) which will be called by HTTP request;



## Verify JSON GET Request

Testing a simple response containing some JSON data.


#### Check the status code, so the expected status is passed by parameter in feature file
- then().statusCode(Integer.parseInt(statusCode));

#### Check body response - In this case, the required format is JSON
- then().assertThat().contentType(ContentType.JSON).and().body(matchesJsonSchemaInClasspath("schema-json.json"));

##How to run the project


#MVN INSTALL

##TestRunner.java

