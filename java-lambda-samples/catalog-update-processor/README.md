# Overview
1. sample application that receives an event payload when a new product is added to the catalog
2. the application then sends out promotion(s) that might be available for the product from its sponsors

# Infra setup
1. Account should be CDK bootstrapped
2. Stand up the infra for the CatalogUpdateProcessor
3. Infra mainly contains of the event bridge
4. `cd catalog-update-proc-stack`
5. `npx cdk deploy --profile <aws profile>`
6. should output the EventBus' Arn

## Test infra
1. post an event to the event bridge
2. `cd sam`
2. `aws events put-events --entries file://events/event-bridge.json --profile <aws profile>`

# App setup
## Build and test application locally
1. `cd sam`
2. `sam build`
3. `sam local invoke CatalogUpdateProcessorFunction -e ./events/event-bridge.json`
4. This should get the event-bridge event to the lambda function

## Deploy app to the cloud and remote invoke the function
1. `sam deploy --guided --profile <aws profile>`
2. Remote invoke the function
3. `sam remote invoke --stack-name catalog-update-processor CatalogUpdateProcessorFunction --event-file ./event/event-bridge.json --profile <aws profile>`
4. Keep the code in sync while developing 
5. `sam sync --watch --profile <aws profile>`

## Test via publishing events to the event bridge
1. `cd CatalogUpdateProcessor`
2. `aws events put-events --entries file://src/test/cli/EventBridgeTest.json --profile <aws profile>`
3. note the event bus name and the source in the json file
4. Watch the application logs
5. `sam logs --stack-name catalog-update-processor --tail  --profile <aws profile>`

# Frameworks and libraries in use
1. Lambda powertools for Logging, Serialization utilities

# Observability
1. Using Lambda powertools Logging to push attributes
   2. Check logs for a particular sku processing
   3. Cloudwatch logs insights | lambda
   3. ```typescript
       fields @timestamp, @message, @logStream, @log
       | filter `sku`='9999'
       | sort @timestamp desc
       | limit 1000
       ```