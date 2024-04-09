package com.mikescherbakov.awslambdaspringbootdemocomparison.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

  private String id;
  private String firstName;
  private String lastName;

  @DynamoDbPartitionKey
  public String getId() {
    return id;
  }
}
