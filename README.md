# Microservice Madness
## A DevOps game

# Introduction

Microservice Madness is a game to show the challenges of root cause analysis (RCA) in a modern microservices landscape.

# Setup

The game consists of different scenarios in which a microservices landscape is created (random or predefined). In this landscape, one or more microservices are malfunctioning by throwing errors, delays in processing, consuming excessive disk space, RAM or CPU. The player's goal is to identify the errant microservice(s) and possibly the type of error it exhibits.

# Requirements

In order to build and run MM, you'll need:

* Java JDK 8 or higher
* docker

# Implementation

The application consists of:

* mad-service, a simple REST service that exposes a single method (`invoke`). Once invoked, the service optionally invokes dependencies before sending a reply.
* config-service, a REST service that exposes configuration for each mad-service instance.
* load-generator-tsung, a load generator that exercises the microservice landscape, based on tsung.
* sample-scenario, a complete scenario running with `docker-compose up`.

# Goals

The MM game can be used for various purposes:

* internal use to simulate a complex microservice landscape
* training
* use at a convention or meetup

# To do

* implement a scenario microservice that defines a scenario of actions & configuration changes per mad service. This should make it possible to simulate the introduction of errors through deployments, possibly rollbacks or other landscape changes.
* make load generation microservice externally configurable.
