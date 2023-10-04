package com.poc.schema.registry.schemaregistry.producer.contoller;

import com.poc.SchemaOrder;
import com.poc.schema.registry.schemaregistry.common.entity.Order;
import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class ProducerController {

  @Autowired
  private KafkaTemplate<String, byte[]> kafkaTemplate;

  @Value("${kafka.topic}")
  private String kafkaTopic;

  @PostMapping
  public ResponseEntity<String> createOrder(@RequestBody Order order) throws IOException {
    String id = String.valueOf(UUID.randomUUID());
    order.setId(id);
    SchemaOrder schemaOrder = buildSchema(order);
    byte[] inputArray = schemaOrder.toByteBuffer().array();
    //kafkaTemplate.send(kafkaTopic, id, order);
    kafkaTemplate.send(kafkaTopic, id, inputArray);
    return ResponseEntity.ok("Order created and sent to Kafka");
  }

  private SchemaOrder buildSchema(Order order) {
    return SchemaOrder.newBuilder()
        .setId(order.getId())
        .setItemName(order.getItemName())
        .setPrice(order.getPrice())
        .setQuantity(order.getQuantity()).
        build();
  }

/*  @GetMapping("/get")
  public ResponseEntity<String> createOrderUsingGet() {
    String id = String.valueOf(UUID.randomUUID());
    Order order = new Order();
    order.setId(id);
    order.setPrice("100");
    order.setQuantity("100");
    order.setItemName("testt");
    kafkaTemplate.send(kafkaTopic, id, order);

    return ResponseEntity.ok("Order created and sent to Kafka");
  }*/
}
