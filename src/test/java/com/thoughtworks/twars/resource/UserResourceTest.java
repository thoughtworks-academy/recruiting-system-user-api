package com.thoughtworks.twars.resource;

import com.thoughtworks.twars.bean.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceTest extends TestBase {

    User user = mock(User.class);

    String basePath = "/users";

    @Test
    public void should_return_user() {
        when(userMapper.getUserById(1)).thenReturn(user);

        when(user.getId()).thenReturn(1);
        when(user.getEmail()).thenReturn("111@222.com");
        when(user.getMobilePhone()).thenReturn("13111111111");

        Response response = target(basePath + "/1").request().get();

        assertThat(response.getStatus(), is(200));

        Map result = response.readEntity(Map.class);

        assertThat((Integer) result.get("id"), is(1));
        assertThat((String) result.get("email"), is("111@222.com"));
        assertThat((String) result.get("mobilePhone"), is("13111111111"));
    }

    @Test
    public void should_return_404_when_get_user() {
        when(userMapper.getUserById(90)).thenReturn(null);

        Response response = target(basePath + "/90").request().get();
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_return_user_by_field() {
        when(userMapper.getUserByEmail(anyString())).thenReturn(user);
        when(user.getId()).thenReturn(10);

        Response response = target(basePath)
                .queryParam("field", "email")
                .queryParam("value", "abc@test.com")
                .request().get();

        assertThat(response.getStatus(), is(200));

        Map result = response.readEntity(Map.class);

        assertThat((String) result.get("uri"), is("users/10"));

    }

    @Test
    public void should_200_when_get_user_by_email() {
        when(userMapper.getUserByEmail(anyString())).thenReturn(null);

        Response response = target(basePath)
                .queryParam("field", "email")
                .queryParam("value", "abc@test.com")
                .request().get();

        Map result = response.readEntity(Map.class);

        assertThat(response.getStatus(), is(200));
        assertEquals(result.get("uri"), null);
    }

    @Test
    public void should_200_when_get_user_by_mobile_phone() {
        when(userMapper.getUserByMobilePhone(anyString())).thenReturn(null);

        Response response = target(basePath)
                .queryParam("field", "mobilePhone")
                .queryParam("value", "4585295152")
                .request().get();

        Map result = response.readEntity(Map.class);

        assertThat(response.getStatus(), is(200));
        assertEquals(result.get("uri"), null);
    }


    @Test
    public void should_change_user_password() throws Exception {
        Map userMap = new HashMap<String, String>();

        userMap.put("oldPassword", "25d55ad283aa400af464c76d713c07ad");
        userMap.put("password", "123");

        when(userMapper.updatePassword(1, "25d55ad283aa400af464c76d713c07ad", "123")).thenReturn(1);

        Entity entity = Entity.entity(userMap, MediaType.APPLICATION_JSON);

        Response response = target(basePath + "/1/password").request().put(entity);

        assertThat(response.getStatus(), is(200));
    }
}