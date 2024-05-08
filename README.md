# Money Transfer System
This is a Spring Boot application that implements a basic money transfer system. Users can create accounts, transfer money between accounts, and view transaction history.

## Getting Started 
### 1.Prerequisites
Java 21  
Maven  
### 2.Clone the repository
`Bash`  
`git clone https://github.com/ufkcnkmc/MoneyTransferSystem`  
### 3.Build the application

`Bash`  
`mvn clean install`
### 4.Run the application
`Bash`  
`mvn spring-boot:run`

## Docker Compose
This project also includes a `docker-compose.yml` file that can be used to run the application with a MySQL database.
### 1.Build the Docker images
`Bash`  
`docker-compose build`
### 2.Run the application
`Bash`  
`docker-compose up`
## API Documentation
### Endpoints
##### .Customer API
`/api/customers`: Get all customers  
`/api/customers/{id}`: Get a customer by ID  
`/api/customers/create/customer`: Create a new customer  
`/api/customers/{id}`: Update a customer  
`/api/customers/{id}`: Delete a customer
#### .Account API  
`/api/accounts`: Create a new account for a customer (specifies customer in request body)  
`/api/accounts/account`: Create a new account for a customer by customer ID (uses AccountCreateRequest DTO)  
`/api/accounts/{id}`: Get an account by ID  
`/api/accounts/{id}`: Update an account balance  
#### .Transaction API  
`/api/transactions`: Transfer money between accounts (uses TransactionRequest DTO)  
`/api/transactions/{transactionId}`: Get a transaction by ID

## Database Configuration
The database connection details are configured in the `application.properties` file. By default, the application will connect to a MySQL database running on `localhost:3306` with the username `root` and password `password`.  
## License  
This project is licensed under the Apache License 2.0. See the LICENSE file for details.


