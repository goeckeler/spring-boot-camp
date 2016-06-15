# Spring Boot Camp

## Objective 

Introduction to Spring Boot using Spring of course. Starting with a simple application we will demonstrate how to

+  run a self-contained application
+  offer a REST interface
+  wire stuff together
+  access databases
+  use aspects
+  write a unit test with dependencies
+  write an integration test
+  monitor the application

This is supposed to demonstrate some of the highlights of Spring and how easy it is to get going with Spring Boot, and what you get on top. If you want get going yourself, visit [http://start.spring.io/](http://start.spring.io/) to generate your initial build script.

## Core Spring

What is Spring all about? Well, the core is **dependency injection**, that is how you wire stuff together minimizing dependencies. Add to this the ease and elegance of accessing **databases** using **JDBC, Hibernate, JPA** or other libraries. Then top that with the support for **aspects** to centralize cross-cutting concerns. Power that with the excellent support for **unit and integration tests**.

## More Spring

Spring offers lot of modules supporting you if you have simple MVC applications, need to configure your system per environment, access cloud services, and many other stuff including providing **REST** services even supporting **HATEOAS**, but we won't concentrate on that here. There is excellent documentation out there, start with [http://spring.io](http://spring.io).

## Spring Boot

Spring Boot offers us a fast start into Spring, with starter modules that handle all the complicated dependency setup and blends in the set of ibraries that match each other.

On top it lets us run our application in a container, thus is the top choice for micro services, and adds the spice that make you happy once the application is deployed and goes live. This spice are health indicators, inside details, cpu usuage and more. By the way, you can still create a war file if you want.