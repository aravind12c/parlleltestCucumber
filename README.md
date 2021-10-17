# Flink Test Framework 


## Tech stack Used
Java, Cucumber, Selenium Webdriver, TestNG

## Prerequisites 
- Use right Chromedriver and Firefox as per your chrome/firefox version (src/main/resources -> driver -> chromedriver.exe/geckodriver.exe)
- Java 8 and above 
- Any IDE 
- For Cucumber's actual flavour, install a plugin for cucumber in IDE and convert project into cucumber project
    
## Project Setup 
- Driver setup and capabilities : \src\main\java\com.flink.driverInit\
- Page Obejcts 					: \src\main\java\com.flink.pageObjects\
- Web Utilities 				: \src\main\java\com.flink.webUtilities\
- Chrome/Firefox driver 		: \src\main\resources\driver\
- Web Configs 					: \src\main\resources\driver\WebConfig
- Test Runner  					: \src\test\java\com.flink.testfactory\
- Step Definitions 				: \src\test\java\com.flink.testfactory\
- Feature files 				: \src\test\java\com.flink.testfactory\

## Pre-Steps
- If you want to update URL and Payment details, please open config.properties from Web Config and update it.

## Steps to run
- Download and Extract this project to your local or git clone https://github.com/aravind12c/happeotestframework.git
- Import as a Maven project in any IDE
- Open tesNG.xm file, from "TestNGXML" folder
- Now run as TestNG Suite

## Approach 
Below are the points considered while creating the framework
- Design pattern - Page object model
- Framework - Cucumber(For Test approach), Selenium Driver(For UI Automation), TestNG(For Parallel Execution)
- Language - Java
