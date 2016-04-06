package com.thoughtworks.twars.resource;

import com.thoughtworks.twars.mapper.*;
import org.apache.ibatis.session.SqlSessionManager;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

import javax.ws.rs.core.Application;

import static org.mockito.Mockito.mock;

public class TestBase extends JerseyTest {

    protected SqlSessionManager sqlSessionManager = mock(SqlSessionManager.class);
    protected UserMapper userMapper = mock(UserMapper.class);
    protected GroupMapper groupMapper = mock(GroupMapper.class);

    @Override
    protected Application configure() {

        enable(TestProperties.DUMP_ENTITY);

        return new ResourceConfig().register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(userMapper).to(UserMapper.class);
                bind(groupMapper).to(GroupMapper.class);
                bind(sqlSessionManager).to(SqlSessionManager.class);
            }
        }).packages("com.thoughtworks.twars.resource");
    }

}
