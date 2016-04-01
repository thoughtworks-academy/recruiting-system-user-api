package com.thoughtworks.twars.mapper;

import com.thoughtworks.twars.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int insertUser(User user);

    User getUserById(int id);

    User getUserByEmail(String email);

    User getUserByMobilePhone(String mobilePhone);

    User getUserByEmailAndPassWord(User user);

    User getUserByMobilePhoneAndPassWord(User user);

    int updatePassword(
            @Param("id") int id,
            @Param("oldPassword") String oldPassword,
            @Param("password") String password);

    int resetPassword(User user);

    List<User> findUsersByUserIds(List<Integer> userIds);
}
