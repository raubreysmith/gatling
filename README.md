# Gatling Performance and Load Testing
Gatling is designed to treat your performance tests as production code. Maintenance and automation are made easier.

Before running tests, it would be wise to ensure your network/server host is aware and OK to support the testing as
these can draw a lot of resources and bandwidth. - you don't want to perform any unintended DoS!

##Dependencies
* Java SDK 8
* [Maven](https://maven.apache.org/install.html)
* Scala enabled IDE e.g. [IntelliJ](https://www.jetbrains.com/idea/)

## Quick start
From the IDE terminal or command line, ensure you are at the root dir of the project

**Run all simulations**
```
mvn clean gatling:test
```
**Run specific simulation**
```
mvn clean gatling:test -Dgatling.simulationClass=simulations.SampleSimulation1

```
**View results**

* Results are packaged as Highcharts reports: _target/gatling/{simulation}/index.html_
* Note that by using the _mvn 'clean'_ command, existing reports will be removed before each run

##Configuration

###Gatling-Maven-Plugin
Gatling is run through a maven plugin as detailed in _pom.xml_. 

* [Running multiple simulations](https://gatling.io/docs/current/extensions/maven_plugin/#including-excluding-simulations-when-running-multiple-simulations)
* [Maven build phases and execution blocks](https://gatling.io/docs/current/extensions/maven_plugin/#running-the-plugin)

###Gatling
The Gatling plugin has a most configurations setup however you can override these in _/resources/gatling.conf_
```
   directory {
      data = ./data                       # Folder where user's data (e.g. files used by Feeders) is located
      bodies = ./bodies                   # Folder where bodies are located
      simulations = ../scala/simulations  # Folder where the bundle's simulations are located
      #reportsOnly = ""                   # If set, name of report folder to look for in order to generate its report
      #binaries = ""                      # If set, name of the folder where compiles classes are located
      #results = results                  # Name of the folder where all reports folder are located
    }
```
Of particular note is the results folder if you want to maintain results outside of the default target directory

###Gatling recorder
Recorder config is detailed in _/resources/recorder.conf_ however many of these can also be set from the recorder GUI

## Creating new simulations
A quick way to generate new simulations is to use the bundled recorder _/recorder/Recorder_
* From within your IDE, Run the Recorder scala object, this will launch the GUI
* By default, this will output the recording as _'RecordedSimulation.scala'_ in the simulations folder
* See the [Gatling help docs](https://gatling.io/docs/current/quickstart/#using-the-recorder) for setting up your browser and using the recorder
* Once you have a recording you'll want to [separate out your scenario and simulation](https://gatling.io/docs/current/advanced_tutorial/#step-01-isolate-processes)

### Performance profiles
Gatling has a [number of options](https://gatling.io/docs/current/general/simulation_setup/#injection) for testing the desired performance requirements.
```
setUp(
  scn.inject(
    nothingFor(4 seconds),
    atOnceUsers(10),
    rampUsers(10) over (5 seconds)
    constantUsersPerSec(20) during (15 seconds),
    constantUsersPerSec(20) during (15 seconds) randomized,
    rampUsersPerSec(10) to 20 during (10 minutes),
    rampUsersPerSec(10) to 20 during (10 minutes) randomized,
    splitUsers(1000) into (rampUsers(10) over (10 seconds)) separatedBy (10 seconds), 
    splitUsers(1000) into (rampUsers(10) over (10 seconds)) separatedBy atOnceUsers(30),
    heavisideUsers(1000) over (20 seconds) 
  ).protocols(httpConf)
)
```
