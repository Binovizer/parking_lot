# Parking Lot Application

Parking Lot Application is a Java based application to automate the parking lots.

## Getting Started

Download the compressed file and extract the contents inside.

### Prerequisites

What things you need to install the software and how to install them
+ Java 8 or later
+ Maven

#### Install Java
+ [How do I Install Java?](https://www.java.com/en/download/help/download_options.xml)

To verify the installation and check the java version
```
$ java -version
```
Please make sure the java version is 8 or later.

#### Install Maven
+ [Install Maven on Windows](https://www.baeldung.com/install-maven-on-windows-linux-mac#installing-maven-on-windows)
+ [Install Maven on Linux](https://www.baeldung.com/install-maven-on-windows-linux-mac#installing-maven-on-linux)
+ [Install Maven on Mac OSX](https://www.baeldung.com/install-maven-on-windows-linux-mac#installing-maven-on-mac-os-x)

To verify the installation 
```
$ mvn --version
```

## Set Up

Once you have downloaded and extracted the jar file, you can go to the ```bin``` folder and run the ```setup``` executable to set up the project as well as to run the automated tests.

## Run
Please make sure you set up the project before running.

This application can run in two modes
+ Interactive Mode
+ File Input Mode

### Interactive Mode
1. Go to the ```bin``` folder and run the ```parking_lot``` by double clicking on it to run the project into interactive mode.
2. Use terminal to run the executable
```
$ ./parking_lot
```

### File Input Mode
1. Use terminal to run the executable file passing path of input file as an argument
```
$ ./parking_lot /some/dummy/path/file_input.txt
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [Github](https://github.com/) for versioning.

## Authors

* **Mohd Nadeem**
