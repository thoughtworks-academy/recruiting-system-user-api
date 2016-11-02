package com.thoughtworks.twars.resource;

import com.thoughtworks.twars.mapper.LoginDetailMapper;
import com.thoughtworks.twars.mapper.UserMapper;
import org.apache.ibatis.session.SqlSessionManager;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import javax.ws.rs.core.Application;

public class TestBase extends JerseyTest {
    protected SqlSessionManager sqlSessionManager = mock(SqlSessionManager.class);


    @Mock
    protected UserMapper userMapper;

    @Mock
    protected LoginDetailMapper loginDetailMapper;

    @Override    //覆盖父类中的方法
    protected Application configure() {
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig().register(new AbstractBinder(){
            @Override
            protected void configure() {
                bind(userMapper).to(UserMapper.class);
                bind(loginDetailMapper).to(LoginDetailMapper.class);
                bind(sqlSessionManager).to(SqlSessionManager.class);
            }
        }).packages("com.thoughtworks.twars.resource");
    }
}
