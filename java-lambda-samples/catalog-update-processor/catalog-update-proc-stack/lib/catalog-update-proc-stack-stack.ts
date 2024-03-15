import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as events from 'aws-cdk-lib/aws-events';
import {Duration} from "aws-cdk-lib";


export class CatalogUpdateProcStackStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const productCatalogBus = new events.EventBus(this, 'bus', {
      eventBusName: 'ProductCatalogEventBus'
    });

    const archive =
        productCatalogBus.archive("ProductCatalogEventsArchive", {
          archiveName: "ProductCatalogEventsArchive",
          eventPattern: {},
          retention: Duration.days(1)
        });


    // 👇 create an Output
    new cdk.CfnOutput(this, 'ProductCatalogEventBusArn', {
      value: productCatalogBus.eventBusArn,
      description: 'The Arn the event bus',
      exportName: 'ProductCatalogEventBusArn',
    });

  }
}
