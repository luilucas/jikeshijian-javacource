package com.lucas.homework.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Order {
    private Long orderId;
    private Long customerId;
    private Date createdTime;

    public Order(Long customerId, Date createdTime) {
        this.customerId = customerId;
        this.createdTime = createdTime;
    }
}
