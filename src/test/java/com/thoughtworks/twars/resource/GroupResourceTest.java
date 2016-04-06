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
        insertGroup.setAnnouncePublished(false);

        Entity<Group> entityGroup = Entity.entity(insertGroup,
                MediaType.APPLICATION_JSON_TYPE);
        Response response = target(basePath).request().post(entityGroup);

        assertThat(response.getStatus(), is(201));

        Map result = response.readEntity(Map.class);
        assertThat(result.get("uri"), is("/groups/1"));
    }
}
