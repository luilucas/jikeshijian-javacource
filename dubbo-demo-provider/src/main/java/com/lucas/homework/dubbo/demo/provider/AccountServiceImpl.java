package com.lucas.homework.dubbo.demo.provider;

import com.lucas.homework.dubbo.demo.api.Account;
import com.lucas.homework.dubbo.demo.api.AccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@DubboService(version = "1.0.0")
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper mapper;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean pay(Account account) {
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean confirm(Account account){
        int result = mapper.update(account);
        System.out.println("confirm: "+ account.getName());
        return result > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean cancel(Account account){
        System.out.println("cancel: "+ account.getName());
        return true;
    }
}
