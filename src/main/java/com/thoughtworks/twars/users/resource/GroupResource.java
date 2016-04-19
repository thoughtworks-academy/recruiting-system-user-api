package com.thoughtworks.twars.users.resource;

import com.thoughtworks.twars.users.bean.Group;
import com.thoughtworks.twars.users.bean.GroupUsers;
import com.thoughtworks.twars.users.mapper.GroupMapper;
import io.swagger.annotations.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.*;

@Path("/groups")
@Api
public class GroupResource extends Resource {
    @Inject
    private GroupMapper groupMapper;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGroup(Group group) {
        GroupUsers groupUsers = new GroupUsers();

        groupMapper.insertGroup(group);
        groupUsers.setGroupId(group.getId());
        groupUsers.setUserId(group.getAdminId());
        groupMapper.insertGroupUsers(groupUsers);

        Map<String, String> map = new HashMap<>();
        map.put("uri", "/groups/" + group.getId());

        return Response.status(Response.Status.CREATED).entity(map).build();
    }

    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroup(
            @PathParam("param") int groupId) {
        Group group = groupMapper.getGroupById(groupId);

        if (group == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        int userNumber = groupMapper.findUserNumberByGroup(groupId);

        Map map = new HashMap<>();
        map.put("memberNumber", userNumber);
        map.put("id", group.getId());
        map.put("name", group.getName());
        map.put("avatar", group.getAvatar());
        map.put("adminId", group.getAdminId());
        map.put("announcement", group.getAnnouncement());
        map.put("isAnnouncePublished", group.getIsAnnouncePublished());


        return Response.status(Response.Status.OK).entity(map).build();
    }

    @PUT
    @Path("/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGroup(
            @PathParam("param") int groupId,
            Group group
    ) {
        group.setId(groupId);
        groupMapper.updateGroup(group);

        Map<String,Object> map = new HashMap<>();
        map.put("uri", "groups/" + groupId);

        return Response.status(Response.Status.OK).entity(map).build();
    }

    @GET
    @Path("{param}/papers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaper(
            @PathParam("param") int groupId
    ) {
        List<Integer> paper = groupMapper.getPaperIdByGroup(groupId);

        return Response.status(Response.Status.OK).entity(paper).build();
    }
}



