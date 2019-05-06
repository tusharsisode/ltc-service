# LTC Serverless MicroService with AWS Lambda and AWS API Gateway

This is a serverless microservice built using Spring Boot and configured to run on AWS Lambda with the help of AWS SAM (Serverless Application Model) and AWS Cloudformation. The API reads the backend application data directly from resources/json/ltc.json file, however the architecture is easily modifiable to plug with backend MongoDB. For testing purposes, steps are also given to install and run this application locally, or as a container on docker.

# Deploy to AWS Lambda

Build the code and deploy with AWS SAM.
 
The deployment steps will require Maven and AWS CLI (https://aws.amazon.com/cli/) to be installed and configured. For configuring AWS CLI, please refer https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-configure.html#cli-quick-configuration link. 

### Refer document LTC_API_Deployed_To_AWS_Lambda_Screenshots.docx for steps with screenshots.

Follow these steps for deploying to AWS Lambda:

1. Clean and rebuild the code as a shaded jar, not as a Spring Boot jar.
	
	mvn clean package

2. Create an S3 bucket to hold the application code. This bucket name must be unique across S3, so adjust for your use in the next two steps.
	
	aws s3 mb s3://ltc-aws-lambda-service-0502
	
3. Copy the jar file to the S3 bucket and update the information into a SAM template.
	
	aws cloudformation package --template-file sam.yaml --output-template-file target/output-sam.yaml --s3-bucket ltc-aws-lambda-service-0502
 
4. Deploy a Cloudformation stack from the SAM template.
	
	aws cloudformation deploy --template-file target/output-sam.yaml --stack-name ltc-aws-lambda-service --capabilities CAPABILITY_IAM
	
5. Describe the stack, which will display the URL of the API in the outputs.
	
	aws cloudformation describe-stacks --stack-name ltc-aws-lambda-service
	
6. SAM provisions all the required resources for running a lambda function in AWS. Go to AWS Lambda Console and verify the created lambda application. Go to AWS API Gateway Console and verify the created API.

7. The API can be accessed with URL like below:
	
	https://d43p353ph8.execute-api.us-west-2.amazonaws.com/Prod/ltc?inputDate=05/09/2019	


# Run locally

This project is setup to build with Maven. To build and run from a packaged jar locally:

    mvn spring-boot:run

or 

    mvn clean package -Dboot
    java -jar target/LTCAWSLambdaService-1.0.0-SNAPSHOT.jar

# Deploy to Docker locally

Please install and run docker locally if not already done. To build the image, first build the application, then build the docker image

    mvn package -Dboot
    docker build -t ltc-aws-lambda-service .
    
## Run on docker

    docker run --name ltc-aws-lambda-service -p 9092:9092 -d ltc-aws-lambda-service
    
# Test

    curl http://localhost:9092/ltc?inputDate=05/09/2019
