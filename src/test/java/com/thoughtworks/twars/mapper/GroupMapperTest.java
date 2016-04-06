package com.thoughtworks.twars.mapper;

import com.thoughtworks.twars.bean.Group;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GroupMapperTest extends TestBase{
    private GroupMapper groupMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        groupMapper = session.getMapper(GroupMapper.class);
    }
    @Test
    public void should_add_group() throws Exception {
        Group group = new Group();

        group.setAdminId(1);
        group.setName("js学习小组");
        group.setAvatar("头像头像头像");
        group.setAnnouncement("公告公告公告");
        group.setIsAnnouncePublished(true);

        groupMapper.insertGroup(group);
        assertThat(group.getId(), is(6));
    }
}
