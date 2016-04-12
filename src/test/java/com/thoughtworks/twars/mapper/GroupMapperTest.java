package com.thoughtworks.twars.mapper;

import com.thoughtworks.twars.bean.Group;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void should_return_group_by_id() throws Exception{
        Group group = groupMapper.getGroupById(1);
        assertThat(group.getName(), is("HTML小组"));
    }

    @Test
    public void should_update_group() throws Exception {
        Group group = new Group();

        group.setId(1);
        group.setAvatar("头像头像");
        group.setAnnouncement("好长好长的公告");
        group.setIsAnnouncePublished(true);
        group.setMemberNumber(9);

        groupMapper.updateGroup(group);

        assertThat(group.getAnnouncement(), is("好长好长的公告"));
        assertThat(group.getMemberNumber(), is(9));
    }

    @Test
    public void should_get_paper_id_by_group() throws Exception{
        List<Integer> group = groupMapper.getPaperIdByGroup(1);

        assertThat(group.size(), is(2));
    }
}
