# Blockchain-SpringBoot

This project is a simple and naive implementation of a blockchain using Spring Boot and Swagger. The main purpose of this project is to understand the basic principles of blockchain and to gain skills in the Test Driven Development (TDD) method.

## Starting the project

1. Clone the repository:
   ```
   git clone https://github.com/antoinegues/blockchain.git
   ```
2. Go to the project directory:
   ```
   cd blockchain
   ```
3. Compile the project with Maven:
   ```
   mvn clean install
   ```
4. Run the Spring Boot application:
   ```
   mvn spring-boot:run
   ```

## Accessing Swagger

Once the application is started, you can access the Swagger documentation by opening your browser and going to the following address:

```
http://localhost:8080/swagger-ui.html
```

You will find the complete documentation of the API and will be able to test the different endpoints.

## Tests

The unit and integration tests were written following the TDD method. You can run the tests with the following command:

```
mvn test
```

## Notes

This implementation of the blockchain is very simple and does not respect all the real principles of a blockchain. The purpose is mainly educational, to understand the basic concepts and to become familiar with TDD development and Spring Boot.

Do not hesitate to improve the project and submit your changes via pull requests!