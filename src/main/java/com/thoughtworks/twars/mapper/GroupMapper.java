package com.thoughtworks.twars.mapper;

import com.thoughtworks.twars.bean.Group;

import java.util.List;

public interface GroupMapper {
    int insertGroup(Group group);

    Group getGroupById(int id);

    List<Group> getGroupsByUserId(int userId);

    int updateGroup(Group group);
    
    List<Integer> getPaperIdByGroup(int groupId);

    int findUserNumberByGroup(int groupId);
}
