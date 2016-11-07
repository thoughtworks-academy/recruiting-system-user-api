package com.thoughtworks.twars.mapper;

import com.thoughtworks.twars.bean.User;
import com.thoughtworks.twars.bean.UserDetail;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class UserMapperTest extends TestBase{
    private UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        userMapper = session.getMapper(UserMapper.class);
    }

    @Test
    public void should_return_user_by_id() throws Exception {
        User user = userMapper.getUserById(1);
        assertThat(user.getMobilePhone(), is("18798037893"));
    }

    @Test
    public void should_return_one_user_by_given_user_mobilePhone() throws Exception{
        User user = userMapper.getUserByMobilePhone("18798037893");
        assertThat(user.getEmail(),is("test@163.com"));
        assertThat(user.getId(),is(1));
        assertThat(user.getMobilePhone(),is("18798037893"));
        assertThat(user.getPassword(),is("550e1bafe077ff0b0b67f4e32f29d751"));
    }

    @Test
    public void should_return_one_user_by_given_user_email() throws Exception{
        User user = userMapper.getUserByEmail("test@163.com");
        assertThat(user.getEmail(),is("test@163.com"));
        assertThat(user.getId(),is(1));
        assertThat(user.getMobilePhone(),is("18798037893"));
        assertThat(user.getPassword(),is("550e1bafe077ff0b0b67f4e32f29d751"));
    }

    @Test
    public void should_insert_an_user() throws Exception{
        User user = new User();
        user.setEmail("547080843@qq.com");
        user.setMobilePhone("18392571954");
        user.setPassword("1234567");
        userMapper.insertUser(user);

        assertThat(user.getId(), is(7));
    }

    @Test
    public void should_return_user_detail_by_id() throws Exception {
        UserDetail detail = userMapper.getUserDetailById(1);

        assertThat(detail.getUserId(), is(1));
        assertThat(detail.getSchool(), is("思沃学院"));
        assertThat(detail.getName(), is("测试一"));
        assertThat(detail.getMajor(), is("计算机"));
        assertThat(detail.getDegree(), is("本科"));
        assertThat(detail.getGender(), is("F"));
        assertThat(detail.getSchoolProvince(), is("陕西"));
        assertThat(detail.getSchoolCity(), is("西安"));
        assertThat(detail.getEntranceYear(),is("2016"));
    }

    @Test
    public void should_insert_user_detail() throws Exception {
        UserDetail userDetail = new UserDetail();

        userDetail.setGender("F");
        userDetail.setDegree("benke");
        userDetail.setSchool("shannxi");
        userDetail.setUserId(5);
        userDetail.setMajor("sc");
        userDetail.setName("purple");
        userDetail.setSchoolProvince("陕西");
        userDetail.setSchoolCity("西安");
        userDetail.setEntranceYear("2015");

        userMapper.updateUserDetail(userDetail);

        assertThat(userDetail.getUserId(),is(5));
        assertThat(userDetail.getSchoolProvince(), is("陕西"));
        assertThat(userDetail.getEntranceYear(), is("2015"));
    }

    @Test
    public void should_update_password() throws Exception {

        int id = 1;
        String oldPassword = "12345678";
        String password = "123";

        int result = userMapper.updatePassword(id, oldPassword, password);

        User resultUser = userMapper.getUserById(1);

        assertThat(result, is(1));
        System.out.println(resultUser.getPassword());
        assertThat(resultUser.getPassword(), is("202cb962ac59075b964b07152d234b70"));
    }

}
