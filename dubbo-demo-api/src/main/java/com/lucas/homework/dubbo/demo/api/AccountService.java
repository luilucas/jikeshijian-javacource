package com.lucas.homework.dubbo.demo.api;

import org.dromara.hmily.annotation.Hmily;

public interface AccountService {
    @Hmily
    boolean pay(Account account);
}
