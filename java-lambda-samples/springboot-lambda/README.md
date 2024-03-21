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

# build
1. `sam build`
2. `sam deploy --guided --profile <aws profile>`

# test
1. `curl <api-gateway-url>/products`