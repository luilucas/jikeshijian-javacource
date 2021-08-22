package com.lucas.com.homework.dubbo.demo.consumer;

import com.lucas.homework.dubbo.demo.api.Account;
import com.lucas.homework.dubbo.demo.api.AccountService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DubboClientApplication{

    @DubboReference(version = "1.0.0")
    private AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(DubboClientApplication.class).close();
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            transaction();
        };
    }

    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void transaction(){
        transactionA();
        transactionB();
    }

    private void transactionA(){
        Account account = new Account();
        account.setName("A");
        account.setDollar(-1);
        account.setRmb(7);
        accountService.pay(account);
    }

    private void transactionB(){
        Account account = new Account();
        account.setName("B");
        account.setDollar(1);
        account.setRmb(-7);
        accountService.pay(account);
    }

    public void confirm(){
        System.out.println("confirm");
    }

    public void cancel(){
        System.out.println("cancel");
    }
}
