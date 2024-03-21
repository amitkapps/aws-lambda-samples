import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as sqs from 'aws-cdk-lib/aws-sqs';


export class CdkCatalogProcessorStackStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    // The code that defines your stack goes here

    // example resource
    const queue = new sqs.Queue(this, 'GreetingsQueue', {
      visibilityTimeout: cdk.Duration.seconds(300)
    });

    // 👇 create an Output
    new cdk.CfnOutput(this, 'GreetingsQueueArn', {
      value: queue.queueArn,
      description: 'The Arn of the Greetings SQS Queue',
      exportName: this.stackName + '-GreetingsQueueArn',
    });

    new cdk.CfnOutput(this, 'GreetingsQueueUrl', {
      value: queue.queueUrl,
      description: 'The queueUrl of the Greetings Queue',
    });

    new cdk.CfnOutput(this, 'GreetingsQueueName', {
      value: queue.queueName,
      description: 'The queueName of the Greetings Queue',
    });

  }
}
