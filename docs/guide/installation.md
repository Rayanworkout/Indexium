---
outline: deep
---

# Installation

## Getting Java


First you need to ensure that Java 17 or higher is installed on your machine. To check that, run the following command:

```bash
java -version
```

Once you have verified that Java is installed, you can proceed to download and run Indexium.

### Downloading pre-built JAR

The most straightforward way to get started with Indexium is to download the JAR file from [github](https://github.com/Rayanworkout/Indexium/releases/download/v0.1.0/Indexium-0.0.1-SNAPSHOT.jar) and run it as a standalone application.


```bash
java -jar Indexium-0.0.1-SNAPSHOT.jar
```

The Spring Boot application will start, and you can access the API at `http://localhost:8080`.

### Building from source


If you prefer to build the project from source, you can clone the repository and build the JAR file using the following commands:

```bash
git clone https://github.com/Rayanworkout/Indexium.git
cd Indexium
mvn clean install

java -jar target/Indexium-0.0.1-SNAPSHOT.jar
```