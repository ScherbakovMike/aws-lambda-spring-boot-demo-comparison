package com.mikescherbakov.awslambdaspringbootdemocomparison.dynamodb;

import static software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.primaryPartitionKey;

import com.mikescherbakov.awslambdaspringbootdemocomparison.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticTableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

@RequiredArgsConstructor
@Configuration
public class DynamoDbConfig {

  @Value("${database.table}")
  public String userTableName;

  @Bean
  public DynamoDbAsyncClient dynamoDbAsyncClient() {
    var region = Region.US_EAST_1;
    return DynamoDbAsyncClient.builder()
        .region(region)
        .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
        .build();
  }

  @Bean
  public DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient() {
    return DynamoDbEnhancedAsyncClient.builder()
        .dynamoDbClient(dynamoDbAsyncClient())
        .build();
  }

  @Bean
  public DynamoDbAsyncTable<User> userTable(DynamoDbEnhancedAsyncClient dynamoDbClient) {
    return dynamoDbClient.table(userTableName, TABLE_SCHEMA);
  }

  private static final TableSchema<User> TABLE_SCHEMA =
      StaticTableSchema.builder(User.class)
          .newItemSupplier(User::new)
          .addAttribute(String.class, a -> a.name("id")
              .getter(User::getId)
              .setter(User::setId)
              .tags(primaryPartitionKey()))
          .build();
}
