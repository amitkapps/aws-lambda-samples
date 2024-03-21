# Overview
Developing lambda apps with sam and deploying additional infrastruce with cdk

# Project setup
1. sam init to scaffold the lambda function and the build setup
2. cdk init to scaffold an empty app
3. update cdk stack to add necessary resources - e.g. SQS
   4. Can also watch and deploy as changes are detected-
   5. `npx cdk deploy --watch --profile <aws profile>`
4. Export the resources that will be referenced in the target sam stack. Names are unique per account/region. Can't be referenced across accounts/regions.
5. import the resource name exported previously
   1. `Queue: !ImportValue <cdk stack name>-GreetingsQueueArn`
6. Deploy the sam stack
7. test:
   1. `aws sqs send-message --profile <aws-profile> --queue-url <queue url from cdk deploy output> --output yaml --message-body "hello"`
