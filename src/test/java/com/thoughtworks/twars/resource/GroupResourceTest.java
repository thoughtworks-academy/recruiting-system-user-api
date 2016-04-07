package com.thoughtworks.twars.resource;

import com.thoughtworks.twars.bean.Group;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupResourceTest  extends TestBase{
    @Mock
    Group group;

    String basePath = "/groups";

    @Test
    public void should_insert_group() throws Exception{
        Group insertGroup = new Group();

        insertGroup.setId(1);
        insertGroup.setAdminId(1);
        insertGroup.setAvatar("avatar");
        insertGroup.setAnnouncement("announce");
        insertGroup.setIsAnnouncePublished(false);

        Entity<Group> entityGroup = Entity.entity(insertGroup,
                MediaType.APPLICATION_JSON_TYPE);
        Response response = target(basePath).request().post(entityGroup);

        assertThat(response.getStatus(), is(201));

        Map result = response.readEntity(Map.class);
        assertThat(result.get("uri"), is("/groups/1"));
    }

    @Test
    public void should_return_group_by_id() throws Exception{
        when(groupMapper.getGroupById(1)).thenReturn(group);

        when(group.getId()).thenReturn(1);
        when(group.getName()).thenReturn("html学习小组");
        when(group.getAdminId()).thenReturn(8);
        when(group.getAvatar()).thenReturn("我有一个好看的头像");
        when(group.getAnnouncement()).thenReturn("公告说些什么");
        when(group.getIsAnnouncePublished()).thenReturn(false);

        Response response = target(basePath + "/1").request().get();

        assertThat(response.getStatus(), is(200));

        Map result = response.readEntity(Map.class);

        assertThat(result.get("id"), is(1));
        assertThat(result.get("name"), is("html学习小组"));
        assertThat(result.get("adminId"), is(8));
        assertThat(result.get("avatar"), is("我有一个好看的头像"));
        assertThat(result.get("announcement"), is("公告说些什么"));
        assertThat(result.get("isAnnouncePublished"), is(false));
    }

    @Test
    public void should_return_404_when_get_no_group() throws Exception{
        when(groupMapper.getGroupById(99)).thenReturn(null);

        Response response = target(basePath + "/99").request().get();

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_update_group() throws Exception {
        Group updateGroup = new Group();

        updateGroup.setId(1);
        updateGroup.setAvatar("头像哦吼吼");
        updateGroup.setAnnouncement("公公告告");
        updateGroup.setIsAnnouncePublished(true);

        Entity<Group> entityGroup = Entity.entity(updateGroup,
                MediaType.APPLICATION_JSON_TYPE);

        Response response = target(basePath + "/1").request().put(entityGroup);

        assertThat(response.getStatus(), is(200));

        Map result = response.readEntity(Map.class);
        assertThat(result.get("uri"), is("/groups/1"));
    }
}
