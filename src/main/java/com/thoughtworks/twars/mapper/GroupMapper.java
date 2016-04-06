package com.thoughtworks.twars.mapper;

import com.thoughtworks.twars.bean.Group;

public interface GroupMapper {
    int insertGroup(Group group);
    Group getGroupById(int id);
    int updateGroup(Group group);
}
