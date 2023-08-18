# PowerLedger API hosted in AWS Lambda
A basic battery management application written with the [Spring Boot 3 framework](https://projects.spring.io/spring-boot/).

The application can be deployed in an AWS account using the [Serverless Application Model](https://github.com/awslabs/serverless-application-model).
The `template.yml` file in the root folder contains the application definition.

The `StreamLambdaHandler` object is the main entry point for Lambda.

## Pre-requisites
* [AWS CLI](https://aws.amazon.com/cli/)
* [SAM CLI](https://github.com/awslabs/aws-sam-cli)
* [Gradle](https://gradle.org/)
* [PostgreSQL](https://www.postgresql.org/)

## Local Deployment
1. Build the application
```shell
./gradlew build
```
2. Configure database access

Add your local PostgreSQL database credentials to `resources/application.properties`
3. Run the application
```shell
./gradlew bootRun
```

## AWS Deployment (Prod, UAT & Sandbox)
1. Have a PostgreSQL RDS instance running

You can do this from the AWS console or through a CloudFormation template.

Once that is done, update the `resources/application.properties` with your AWS credentials

2. Update the AWS SAM template

Update the `template.yml` file in the root folder with your RDS instance's subnets and whitelisted security group.

3. Build the application

In a shell, navigate to the sample's folder and use the SAM CLI to build a deployable package
```shell
sam build
```

This command compiles the application and prepares a deployment package in the `.aws-sam` sub-directory.

4. Deploy the AWS

To deploy the application in your AWS account, you can use the SAM CLI's guided deployment process and follow the instructions on the screen

```shell
sam deploy --guided
```

Once the deployment is completed, the SAM CLI will print out the stack's outputs, including the new application URL. You can use `curl` or a web browser to make a call to the URL

```
...
---------------------------------------------------------------------------------------------------------
OutputKey-Description                        OutputValue
---------------------------------------------------------------------------------------------------------
PowerLedgerApi - URL for application            https://xxxxxxxxxx.execute-api.us-west-2.amazonaws.com/batteries
---------------------------------------------------------------------------------------------------------

$ curl https://xxxxxxxxxx.execute-api.us-west-2.amazonaws.com/batteries
```