package com.thoughtworks.twars.users.bean;

public class Group {
    private int id;
    private String name;
    private int adminId;
    private String avatar;
    private String announcement;
    private boolean isAnnouncePublished;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public boolean getIsAnnouncePublished() {
        return isAnnouncePublished;
    }

    public void setIsAnnouncePublished(boolean isAnnouncePublished) {
        isAnnouncePublished = isAnnouncePublished;
    }
}
