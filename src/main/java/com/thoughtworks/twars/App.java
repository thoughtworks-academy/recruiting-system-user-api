package com.thoughtworks.twars;

import com.thoughtworks.twars.bean.LoginDetail;
import com.thoughtworks.twars.mapper.LoginDetailMapper;
import com.thoughtworks.twars.mapper.UserMapper;
import com.thoughtworks.twars.util.DBUtil;
import org.apache.ibatis.session.SqlSessionManager;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("resource")
public class App extends ResourceConfig {
    public App(){
        SqlSessionManager session = (SqlSessionManager) DBUtil.getSession();
        final UserMapper userMapper = session.getMapper(UserMapper.class);
        final LoginDetailMapper loginDetailMapper = session.getMapper(LoginDetailMapper.class);
        packages("com.thoughtworks.twars.resource")
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(userMapper).to(UserMapper.class);
                        bind(loginDetailMapper).to(LoginDetailMapper.class);
                        bind(session).to(SqlSessionManager.class);

                    }
                    //将上面创建的userMapper赋给所有用UserMapper类创建的对象，就是resource文件夹中private UserMapper userMapper；自动使用了此处的usermapper
                });
    }

}
