Table of Contents
=================

* [Country Service](#country-service)
    * [Minimum Requirements](#minimum-requirements)
* [Getting Started](#getting-started)
    * [Major Libraries / Tools](#major-libraries--tools)
    * [Checkout the Code](#checkout-the-code)
* [Setting up Prerequisites](#setting-up-prerequisites)
    * [Maven setup](#maven-setup)
    * [Run service](#run-service)
* [Running Quality Gates and Build Commands](#running-quality-gates-and-build-commands)
    * [Static Code Analysis](#static-code-analysis)
    * [Unit Tests](#unit-tests)
    * [Code Coverage](#code-coverage)
    * [Build Project](#build-project)
    * [Published Code Coverage](#published-code-coverage)
* [CI/CD GitActions only to handle CI no CD](#cicd-gitactions-only-to-handle-ci-no-cd)
* [API Documentation using Swagger UI](#api-documentation-using-swagger-ui)
* [Trade-off](#trade-off)


# Country Service
Country service is a simplified RestFul API with endpoints that performs retrieving all countries (Country name, flag) and also retrieves a country details by country name.

## Minimum Requirements
- Install git (if you don't hav it yet) https://www.atlassian.com/git/tutorials/install-git
- Java version 17 (used for this project is v17) https://www.oracle.com/java/technologies/downloads/

# Getting Started
This project (Maven) was bootstrapped with [Spring initializr](https://start.spring.io/).

- If you are not already familiar with building for Spring-Boot you may start with this tutorial :
  You can learn more in the [Spring-Boot documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/).

## Major Libraries / Tools

| Category                    | Library/Tool   	         | Link                                                       	 |
|-----------------------------|--------------------------|--------------------------------------|
| Maven (wrapper)             | MVN                      | https://maven.apache.org/
| Spring Web                  | Spring Boot Starter      | https://spring.io/           	       |
| Spring Data JPA             | Java Persistence API     | https://spring.io/projects/spring-data-jpa |
| Database                    | H2 in memory DB          | https://www.mysql.com/products/connector/ |
| Annotation processors       | Lombok                   | https://projectlombok.org/           	 |
| Integration Testing         | Mockito         	        | Integrated with Spring Boot Framework                                	 |
| Unit Testing              	 | Spring Boot Starter Test | Test integrated with Spring Boot Starter                    	 |
| Code Coverage            	  | JaCoCo                   | https://www.jacoco.org/                    	 |
| Static Code Check           | CheckStyle               | https://checkstyle.sourceforge.io/                    	 |
| Codecov                     | Code Coverage Report     | https://about.codecov.io/                    	             |

## Checkout the Code

```bash
git clone git@github.com:bonganim911/country-service.git
cd country-service
```

# Setting up Prerequisites

## Maven setup

Install the following dependencies

- **MVN**: https://maven.apache.org/. OR preferable use the wrapper that comes with spring init.

## Run Service
### `./mvnw clean spring-boot:run`

Runs the api in the development mode.<br />
Invoke [http://localhost:8081](http://localhost:8081) using [Postman](https://www.postman.com/downloads/) or CURL.
- Endpoint for fetching all countries (GET Method) - /api/countries 
- Endpoint for get a country details (GET Method)  /api/countries/{countryName}

## Static Code Analysis
### `./mvnw checkstyle:check`

## Unit Tests
### `./mvnw test`

## Code Coverage
### `./mvnw clean verify`

## Build Project
### `./mvnw clean package`
Build the project and run the all the tests.

## Published Code Coverage
Codecov Below Results and [report is available here](https://app.codecov.io/github/bonganim911/country-service)

![Code Coverage Report](https://raw.githubusercontent.com/bonganim911/country-service/main/codecov-image.png)


# CI/CD GitActions only to handle CI no CD
- The script runs functions on push to main or branch.
    - Builds
    - Static code analysis
    - Run all test,
    - Test coverage and publish the results tp Codecov.
    - Build docker image and publish to docker hub.

# API Documentation using Swagger UI
- When running the application go to [http://localhost:8081/swagger-ui/index.html#/](http://localhost:8081/swagger-ui/index.html#/).
- API docs [http://localhost:8081/v3/api-docs](http://localhost:8081/v3/api-docs).

![Swagger-UI](https://raw.githubusercontent.com/bonganim911/country-service/main/Swagger-UI.png)

# Trade-Off
- Build this application using Service-Centric having plain DTO's that don't hold behaviour, and doesn't hold complex domains or rich models.
- Countries Models
    - Integrated lombok, doesn't seem to get injected for example using @Data, which takes care of getters and setter amongst many doesnt work.
- Data Loader
    - Decided that would be better to fetch and load all countries during Spring Boot startup rather than doing it on-demand in the service.
    - Advantages 
      - Improves Performance: Fetching data once at startup avoids repeated API calls during runtime,
      - Reduce latency for end-users.
      - Consistency: Data is loaded into the database once, ensuring consistency across the application.
      - Offline Support: Once the data is loaded into the database, the application can function offline or with limited internet access.
      - Reduced External API Dependency: The application is less dependent on the external API during runtime, which is especially useful if the API has rate limits or downtime.
    - Disadvantages:
      - Maybe we can argue that we will be off sync with updated data between our db and external data.
        - Handle Data freshness: We can employ strategy to refresh the data periodically with the use of scheduled task to periodically refresh the data.
          - E.g To keep the data fresh, create a @Component class to use Spring's @Scheduled annotation to periodically refresh the data from the external API. 
- Use of H2 in-memory db
    - For testing purposes, but it has memory usage and it consumes RAM. Working with large datasets, this could ne a bottleneck.
- Jcoco
  - Exclude config files, some could have written a test DataLoader.
- SL4j Logger
  - Didn't integrate the logger  



