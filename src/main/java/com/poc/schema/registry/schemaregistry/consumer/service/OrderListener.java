package com.poc.schema.registry.schemaregistry.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.SchemaOrder;
import com.poc.schema.registry.schemaregistry.common.entity.Order;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderListener {

  private final ObjectMapper objectMapper;

  public OrderListener(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-id}")
  public void listen(ConsumerRecord<String, byte[]> consumerRecord) throws IOException {
    SchemaOrder schemaOrder = decodeSchema(consumerRecord);
    System.out.println(schemaOrder);
  }

  private SchemaOrder decodeSchema(ConsumerRecord<String,byte[]> consumerRecord)
      throws IOException {
    return SchemaOrder.fromByteBuffer(ByteBuffer.wrap(consumerRecord.value()));
  }
}
