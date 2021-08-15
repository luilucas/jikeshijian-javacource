package com.lucas.homework.transaction;

import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.transaction.annotation.Transactional;

public class transactionDemo {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @ShardingTransactionType(TransactionType.XA)  // 支持TransactionType.LOCAL, TransactionType.XA, TransactionType.BASE
    public void insert() {
        jdbcTemplate.execute("insert into t_order (customer_id, create_time) VALUES (?, ?)", (PreparedStatementCallback<Object>) preparedStatement -> {
            preparedStatement.setObject(1, "lucas");
            preparedStatement.setObject(2, "2021-08-15");
            preparedStatement.executeUpdate();
        });
}}
