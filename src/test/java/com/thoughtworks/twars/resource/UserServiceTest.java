package com.thoughtworks.twars.resource;

import com.thoughtworks.twars.bean.User;
import com.thoughtworks.twars.bean.UserDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends TestBase {
    String basePath = "/user";

    User user = mock(User.class);

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        when(userMapper.getUserByEmail("test2@qq.com")).thenReturn(user);
        when(userMapper.getUserByMobilePhone("18087839393")).thenReturn(user);
        when(user.getEmail()).thenReturn("test2@qq.com");
        when(user.getId()).thenReturn(2);
        when(user.getMobilePhone()).thenReturn("18087839393");

    }

    @Test
    public void should_return_404_when_not_found_user_by_mobilePhone() {
        when(userMapper.getUserByMobilePhone("18829290571")).thenReturn(null);
        Response response = target(basePath + "/mobilePhone/18829290571").request().get();
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_return_404_when_not_found_user_by_email() {
        when(userMapper.getUserByEmail("565678159@qq.com")).thenReturn(null);
        Response response = target(basePath + "/email/565678159@qq.com").request().get();
        assertThat(response.getStatus(), is(404));
    }


    @Test
    public void should_return_one_user_by_mobilePhone() {
        Response response = target(basePath + "/mobilePhone/18087839393").request().get();

        assertThat(response.getStatus(), is(200));
        Map result = response.readEntity(Map.class);  //打印出从数据库找到的user
        assertThat(result.get("email"), is("test2@qq.com"));
        assertThat(result.get("mobilePhone"), is(user.getMobilePhone()));
        assertThat(result.get("id"), is(user.getId()));
    }

    @Test
    public void should_return_one_user_by_email() {
        Response response = target(basePath + "/email/test2@qq.com").request().get();
        assertThat(response.getStatus(), is(200));
        Map result = response.readEntity(Map.class);  //打印出从数据库找到的user
        assertThat( result.get("email"), is("test2@qq.com"));
        assertThat( result.get("mobilePhone"), is("18087839393"));
        assertThat( result.get("id"), is(2));
    }

    @Test
    public void should_insert_user_detail() throws Exception {
        UserDetail insertUserDetail = new UserDetail();

        insertUserDetail.setUserId(18);
        insertUserDetail.setDegree("benke");
        insertUserDetail.setGender("F");
        insertUserDetail.setMajor("cs");
        insertUserDetail.setName("purple");
        insertUserDetail.setSchool("shannxi");
        insertUserDetail.setSchoolProvince("陕西");
        insertUserDetail.setSchoolCity("西安");
        insertUserDetail.setEntranceYear("2016");

        Entity<UserDetail> entityUserDetail = Entity.entity(insertUserDetail,
                MediaType.APPLICATION_JSON_TYPE);
        Response response = target(basePath + "/18/detail").request().put
                (entityUserDetail);

        assertThat(response.getStatus(), is(200));

        Map result = response.readEntity(Map.class);
        assertThat(result.get("uri"), is("userDetail/18"));
    }

    @Test
    public void should_return_404_when_get_no_detail() throws Exception {

        when(userMapper.getUserDetailById(anyInt())).thenReturn(null);

        Response response = target(basePath + "/99/detail").request().get();

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_return_user_detail_by_user_id() throws Exception {

        UserDetail theDetail = mock(UserDetail.class);

        when(userMapper.getUserDetailById(1)).thenReturn(theDetail);
        when(userMapper.getUserById(1)).thenReturn(user);
        when(user.getMobilePhone()).thenReturn("123456");
        when(user.getEmail()).thenReturn("11@qq.com");

        when(theDetail.getUserId()).thenReturn(1);
        when(theDetail.getSchool()).thenReturn("哈佛");
        when(theDetail.getMajor()).thenReturn("宗教");
        when(theDetail.getDegree()).thenReturn("博士");
        when(theDetail.getName()).thenReturn("狗剩");
        when(theDetail.getGender()).thenReturn("男");
        when(theDetail.getSchoolProvince()).thenReturn("陕西");
        when(theDetail.getSchoolCity()).thenReturn("西安");
        when(theDetail.getEntranceYear()).thenReturn("2016");

        Response response = target(basePath + "/1/detail").request().get();

        assertThat(response.getStatus(), is(200));

        Map result = response.readEntity(Map.class);

        assertThat(result.get("userId"), is(1));
        assertThat(result.get("school"), is("哈佛"));
        assertThat(result.get("major"), is("宗教"));
        assertThat(result.get("degree"), is("博士"));
        assertThat(result.get("name"), is("狗剩"));
        assertThat(result.get("gender"), is("男"));
        assertThat(result.get("mobilePhone"), is("123456"));
        assertThat(result.get("email"), is("11@qq.com"));
        assertThat(result.get("schoolProvince"), is("陕西"));
        assertThat(result.get("schoolCity"), is("西安"));
        assertThat(result.get("entranceYear"), is("2016"));
    }

    @Test
    public void should_update_user_detail() throws Exception {
        UserDetail updateUserDetail = new UserDetail();

        updateUserDetail.setUserId(2);

        Entity<UserDetail> entityUserDetail = Entity.entity(updateUserDetail,
                MediaType.APPLICATION_JSON_TYPE);
        Response response = target(basePath + "/2/detail").request().put
                (entityUserDetail);

        assertThat(response.getStatus(), is(200));

        Map result = response.readEntity(Map.class);
        assertThat(result.get("uri"), is("userDetail/2"));

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
