# Sudoku

## Build project from terminal with Maven
### Build package
```
mvn clean package
```

### Run jar with dependencies
```
java -cp target/{JAR-WITH-DEPENDENCIES} {APP-MAIN-CLASS}
```
For example
```
java -cp target/core-1.0-SNAPSHOT-jar-with-dependencies.jar com.tei.Main
```

### *Database sql script has to be run for the application to work.