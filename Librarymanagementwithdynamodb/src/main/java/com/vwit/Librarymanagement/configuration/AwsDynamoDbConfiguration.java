package com.vwit.Librarymanagement.configuration;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.vwit.Librarymanagement")
public class AwsDynamoDbConfiguration {

	@Value("${amazon.aws.accesskey}")
	private String amazonAwsAccessKey;

	@Value("${amazon.aws.secretkey}")
	private String amazonAwsSecretKey;

	@Bean
	public AmazonDynamoDB amazonDynamoDB(AWSCredentialsProvider awsCredentialsProvider) {
		AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withCredentials(awsCredentialsProvider)
				.withRegion(Regions.AP_SOUTH_1).build();
		return amazonDynamoDB;
	}

	@Bean
	public AWSCredentialsProvider awsCredentialsProvider() {
		return new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonAwsAccessKey, amazonAwsSecretKey));
	}
}
