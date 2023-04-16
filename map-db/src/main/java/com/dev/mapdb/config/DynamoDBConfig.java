package com.dev.mapdb.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.dev.mapdb.repository")
public class DynamoDBConfig {

    @Value("${aws.endpoint}")
    private String awsEndpoint;

    @Value("${aws.accessKey}")
    private String awsAccesskey;

    @Value("${aws.secretAccessKey}")
    private String awsSecretAccessKey;

    @Value("${aws.region}")
    private String awsRegion;

    // @Bean
    // public AmazonDynamoDB amazonDynamoDB() {
    // return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
    // new AwsClientBuilder.EndpointConfiguration("http://localhost:8000",
    // "ap-northeast-1")
    // ).build();
    // }

    // @Bean
    // public AmazonDynamoDB amazonDynamoDB() {
    // AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
    // .withEndpointConfiguration(new
    // AwsClientBuilder.EndpointConfiguration(awsEndpoint, awsRegion)).build();

    // return amazonDynamoDB;
    // }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonCredentialsProvider())
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsEndpoint, awsRegion)).build();
    }

    @Bean
    public AWSCredentialsProvider amazonCredentialsProvider() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccesskey, awsSecretAccessKey));
    }
}
