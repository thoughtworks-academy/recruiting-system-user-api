package com.thoughtworks.twars.users.mapper;

import com.thoughtworks.twars.users.bean.Group;
import com.thoughtworks.twars.users.bean.User;
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

    List<Integer> findUserGroupsByUserId(int userId);

    List<Group> findGroupsByGroupId(List<Integer> groupIds);
}