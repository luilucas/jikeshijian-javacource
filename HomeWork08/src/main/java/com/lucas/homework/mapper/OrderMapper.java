package com.lucas.homework.mapper;

import com.lucas.homework.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("insert into t_order(customer_id, create_time) values(#{customerId}, #{createTime})")
    int Insert(Order order);

    @Select("select * from t_order")
    List<Order> select();
}
