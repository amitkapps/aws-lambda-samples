# Overview
building APIs with springboot as lambda functions

# Scaffold
1. Init with sam
2. Init a springboot 3 app
3. Add dependency `aws-serverless-java-container-springboot3`
   1. ```xml
        <dependency>
            <groupId>com.amazonaws.serverless</groupId>
            <artifactId>aws-serverless-java-container-springboot3</artifactId>
            <version>2.0.0</version>
        </dependency>
      ```
4. Create a lambda handler
   1. `StreamLambdaHandler implements RequestStreamHandler`
   2. this should specify the proxy handler which is the spring boot application
5. Remove the spring boot maven plugin, instead use the maven shade plugin to create the fatjar
   1. The embedded tomcat libraries to be excluded, since we don't need any servlet impl
6. Add springboot's starter data jpa.
7. Configure hikari to fetch rds/mysql credentials from secrets manager. AWS Sdk ver 2.x
8. Change lambda function to vpc, add subnets.
9. Add appropriate security group for outgoing access to RDS, and internet access (nat gateway) for secrets manager access
10. Add lambda versioning/alias
11. Add snapstart

# build
1. `sam build`
2. Need an existing Database (schema name products) with mysql Aurora credentials stored in secrets manager
   3. table schema: `products_schema.sql`
   4. test data: `products_data.sql`
2. `sam deploy --guided --profile <aws profile>`
   1. Following parameters needed
   2. LambdaSubnetId
   3. LambdaSecurityGroupId
   4. RdsMysqlSecretArn
   5. RdsMysqlSecretId

# test
1. `curl <api-gateway-url>/Prod/products`
2. `curl -X POST -d '{"productSku":"1111", "productName":"Huggies diaper", "launchDate": "2024-01-30T00:00:00Z"}' <APIGatewayUrl>/Prod/products --header "Content-Type:application/json"`
3. `curl <api-gateway-url>/Prod/products/1111`
4. `curl -X DELETE <APIGatewayUrl>/Prod/products/1111 --header "Content-Type:application/json"`