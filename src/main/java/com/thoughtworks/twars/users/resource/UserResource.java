package com.thoughtworks.twars.users.resource;

import com.thoughtworks.twars.users.bean.Group;
import com.thoughtworks.twars.users.bean.User;
import com.thoughtworks.twars.users.mapper.GroupMapper;
import com.thoughtworks.twars.users.mapper.UserMapper;
import io.swagger.annotations.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

@Path("/users")
@Api
public class UserResource extends Resource {

    @Inject
    private UserMapper userMapper;
    @Inject
    private GroupMapper groupMapper;

    @GET
    @Path("/{param}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "get one user successful"),
            @ApiResponse(code = 404, message = "get one user failed")})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(
            @ApiParam(name = "userId", value = "int", required = true)
            @PathParam("param") int userId) {

        User user = userMapper.getUserById(userId);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("email", user.getEmail());
        map.put("mobilePhone", user.getMobilePhone());

        return Response.status(Response.Status.OK).entity(map).build();
    }

    @GET
    @Path("/{param}/groups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsByUserId(
            @PathParam("param") int userId) {
        User user = userMapper.getUserById(userId);
        List<Group> groupList = new ArrayList<>();
        if (user.getRole().equals("1")) {
            groupList = groupMapper.getGroupsByUserId(userId);
        }
        List<Integer> groupIds = userMapper.findUserGroupsByUserId(userId);
        List<Group> groups = userMapper.findGroupsByGroupId(groupIds);
        if (groupList.size() != 0) {
            groups.addAll(groupList);
        }

        List<Map> groupResult = groups.stream()
                .map(group -> {
                    Map groupMap = new HashMap();
                    groupMap.put("id", group.getId());
                    groupMap.put("name", group.getName());
                    groupMap.put("avatar", group.getAvatar());
                    return groupMap;
                }).collect(Collectors.toList());

        return Response.status(Response.Status.OK).entity(groupResult).build();
    }

    @GET
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "field", value = "field name", required = true),
            @ApiImplicitParam(name = "value", value = "field value", required = true)})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "get one user successful")})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByField(
            @QueryParam("field") String field,
            @QueryParam("value") String value
    ) {
        User user = null;

        if ("email".equals(field)) {
            user = userMapper.getUserByEmail(value);
        } else if ("mobilePhone".equals(field)) {
            user = userMapper.getUserByMobilePhone(value);
        }

        Map<String, String> map = new HashMap<>();

        if (null != user) {

            map.put("uri", "users/" + user.getId());

            return Response.status(Response.Status.OK).entity(map).build();
        }

        map.put("uri", null);

        return Response.status(Response.Status.OK).entity(map).build();
    }

    @PUT
    @Path("/{param}/password")
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "userId", value = "int", required = true),
            @ApiImplicitParam(name = "userPasswordMap",
                    value = "include all info when update user password", required = true)})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "update user password successful"),
            @ApiResponse(code = 400, message = "update user password failed")})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserPassword(
            @PathParam("param") int userId,
            Map userPasswordMap
    ) {
        String oldPassword = (String) userPasswordMap.get("oldPassword");
        String password = (String) userPasswordMap.get("password");

        int result = userMapper
                .updatePassword(userId, oldPassword, password);

        if (1 == result) {
            Map<String, Object> map = new HashMap<>();
            map.put("uri", "users/" + userId);

            return Response.status(Response.Status.OK).entity(map).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
