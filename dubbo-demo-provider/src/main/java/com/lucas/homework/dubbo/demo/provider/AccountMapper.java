package com.lucas.homework.dubbo.demo.provider;

import com.lucas.homework.dubbo.demo.api.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper {
    @Update("update t_account set dollar = #{dollar}, rmb = #{dollar} where name = #{name}")
    int update(Account account);
}
