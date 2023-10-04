package com.poc.schema.registry.schemaregistry.consumer.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConsumerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${kafka.topic}")
  private String kafkaTopic;

  @Value("${kafka.group-id}")
  private String groupId;

}

