package com.thoughtworks.twars;

import com.thoughtworks.twars.filter.CloseSessionResponseFilter;
import com.thoughtworks.twars.filter.OpenSessionRequestFilter;
import com.thoughtworks.twars.mapper.*;
import com.thoughtworks.twars.util.DBUtil;
import org.apache.ibatis.session.SqlSessionManager;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("resources")
public class App extends ResourceConfig {

    public App() {

        SqlSessionManager session = DBUtil.getSession();

        final UserMapper userMapper = session
                .getMapper(UserMapper.class);

        register(OpenSessionRequestFilter.class);
        register(CloseSessionResponseFilter.class);

        packages("com.thoughtworks.twars.resource")
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(userMapper).to(UserMapper.class);
                        bind(session).to(SqlSessionManager.class);
                    }
                });
    }
}
