# How to run

Make sure you have the latest versions of the following installed:
	Java Development Kit https://www.oracle.com/
	Maven https://maven.apache.org/install.html

### Install Dependencies

```
mvn clean compile
```

#### Run tests
```
 mvn clean test -Denv=qa -DbrowserName=chrome
```

### Generate and open report
```
mvn allure:serve
```
