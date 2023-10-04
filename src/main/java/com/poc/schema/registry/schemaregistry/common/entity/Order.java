package com.poc.schema.registry.schemaregistry.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order {

  private String id;

  private String itemName;

  private Integer price;

  private Integer quantity;

}