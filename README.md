## Small Wallet

• Java 17
• Spring Boot 3.0.0
• Spring Data JDBC
• MySQL 8
• Maven
• JUnit
• REST endpoints with JSON



Run MySql: $ docker-compose up


### Endpoints:

> create account

wget --no-check-certificate --quiet
--method PUT
--header 'Content-Type: application/json'
--body-data '{
"email": "1@gmail.com",
"password": "abc"
}'
'http://localhost:8080/account'


> deposit

wget --no-check-certificate --quiet
--method PUT
--header 'Content-Type: application/json'
--body-data '{
"accountId":"1",
"amount":10
}'
'http://localhost:8080/wallet/deposit'


> withdraw

wget --no-check-certificate --quiet
--method PUT
--header 'Content-Type: application/json'
--body-data '{
"accountId":"1",
"amount":10
}'
'http://localhost:8080/wallet/withdraw'
