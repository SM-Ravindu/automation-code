# nimi-kash-automation
Nimi Kash automation is a multi-module project to maintain test automation scripts for API, Web &amp; Mobile tests. This project is built using Selenium, Res-Assured and Appium frameworks using Java programming language. 

**Project Structure:**
```
├───.github
│   └───workflows - Keep github workflow configs
├───api-automation
│   ├───src
│   │   ├───main - Keep all the data files, functions & pages
│   │   └───test - Keep all the test scripts
│   ├───xml files - keep all the test suite files related to API module
│   └───pom.xml - Keep all the dependencies related to the API module
│   
├───jar
│   └───lib
│
└───web-automation
│    ├───src
│    │   ├───main - Keep all the data files, functions & pages.
│    │   ├───test - Keep all the test scripts
│    ├───xml files - keep all the test suite files related to API module
│    └───pom.xml - Keep all the dependencies related to the API module
│
└───mobile-automation - This module is yet to be developed
│
└───pom.xml - Keep all the common dependencies related to all the sub modules
```

**IntelliJ Setup & Run Tests:**
- Clone the [nimi-kash-automation](https://github.com/Nimi-NimiX/nimi-kash-automation) repository to your local machine.
- Download and Install IntelliJ community Edition by following the steps in this link https://www.jetbrains.com/idea/download/#section=windows
- Set JAVA_HOME system environment variable to point to correct jdk version e.g: C:\Program Files\Java\jdk1.8.0_361
- Set M2_HOME variable to point to maven location e.g: C:\apache-maven-3.8.3-bin\apache-maven-3.8.3
- Open IntelliJ IDE and click on new -> project from existing sources and provide the path to the downloaded project from the GitHub. Then, navigate through the Interface to open the cloned project.
- Check the IntelliJ configs for Java and maven
    - Open IntelliJ and go to File -> Settings -> Build, Execution, Deployment -> Build Tools -> Maven and make sure the Maven Home Path is set as the M2_HOME path
    - Go to File -> Project Structure -> Project and make sure your SDK version is set as in the JAVA_HOME
- Open the Terminal and build using `mvn install -DskipTests=true`
- Once the build is successful run the tests using `mvn test`
- Or you can run it from IntelliJ by right-clicking and running the .xml suite files
- Once tests are complete open the report - This is yet to be integrated
- To run a specific .xml file from the terminal, run using the command -> `mvn clean test -DtestSuiteFile="{xml suite file path}"`

**Notes:** 
- This project is using api-automation project dependencies in other modules by the api-automation-1.0.0.jar fle. Whenever making code changes in the api-automation module for which JAR is already created, you need to make the JAR again in order to reflect the new changes in the module where JAR is being consumed.
To do that, go to your api-automation module and install the dependencies as below:
  - `cd api-automation`
  - `mvn clean install -DskipTest=true`
- When you run this project locally, you have to build the [Test Framework](https://github.com/Nimi-NimiX/TestFramework) project first in your local machine in order to download the dependencies.
