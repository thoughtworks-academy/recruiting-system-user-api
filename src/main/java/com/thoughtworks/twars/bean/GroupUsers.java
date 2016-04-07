package com.thoughtworks.twars.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupUsers {
    private int groupId;
    private int userId;
//    private List<Group> groups;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

//    public List<Map> getResponseInfo(){
//
//        List<Map> groupsInfo = groups.stream()
//                .map(group -> {
//                    Map groupMap = new HashMap();
//                    groupMap.put("id", group.getId());
//                    groupMap.put("name", group.getName());
//                    groupMap.put("avatar", group.getAvatar());
//                    return groupMap;
//                }).collect(Collectors.toList());
//        return groupsInfo;
//    }
}
