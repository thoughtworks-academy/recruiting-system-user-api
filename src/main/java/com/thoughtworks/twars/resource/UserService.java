package com.thoughtworks.twars.resource;

import com.thoughtworks.twars.bean.UserDetail;
import com.thoughtworks.twars.mapper.UserMapper;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.twars.bean.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Path("user")
public class UserService {
    @Inject
    private UserMapper userMapper;

    @GET
    @Path("/{param}")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "get one user successfully"),
            @ApiResponse(code = 404,message = "get one user by id failed")})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(
            @ApiParam(name = "id",value = "int",required = true)
            @PathParam("param") int id){
        User user = userMapper.getUserById(id);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("email", user.getEmail());
        map.put("mobilePhone", user.getMobilePhone());
        map.put("role", user.getRole());

        return Response.status(Response.Status.OK).entity(map).build();
    }


    @GET
    @Path("/mobilePhone/{param}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "get one user failed")})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByMobilePhone(
            @ApiParam(name = "mobilePhone", value = "String", required = true)
            @PathParam("param") String mobilePhone) {

        User user = userMapper.getUserByMobilePhone(mobilePhone);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("mobilePhone", user.getMobilePhone());
        map.put("email", user.getEmail());
        map.put("password", user.getPassword());
        map.put("role", user.getRole());

        return Response.status(Response.Status.OK).entity(map).build();
    }

    @GET
    @Path("/email/{param}")
    @ApiResponses(value = {@ApiResponse(code=200, message = "get one user successful"),
            @ApiResponse(code = 404, message = "get one user failed")})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByEmail(
        @ApiParam(name = "email", value = "String" ,required = true)
        @PathParam("param") String email){
            User user = userMapper.getUserByEmail(email);


        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Map<String,Object> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("mobilePhone", user.getMobilePhone());
        map.put("email", user.getEmail());
        map.put("password", user.getPassword());
        map.put("role", user.getRole());

        return Response.status(Response.Status.OK).entity(map).build();
    }

    @GET
    @ApiResponses(value = {@ApiResponse(code = 200, message = "get one userDetail successful"),
            @ApiResponse(code = 404, message = "get one userDetail user failed")})
    @Path("/{param}/detail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDetail(
            @io.swagger.annotations.ApiParam(name = "userId", value = "int", required = true)
            @PathParam("param") int userId) {

        UserDetail detail = userMapper.getUserDetailById(userId);
        User user = userMapper.getUserById(userId);

        if (null == user || null == detail) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("userId", detail.getUserId());
        map.put("school", detail.getSchool());
        map.put("major", detail.getMajor());
        map.put("degree", detail.getDegree());
        map.put("name", detail.getName());
        map.put("gender", detail.getGender());
        map.put("email", user.getEmail());
        map.put("mobilePhone", user.getMobilePhone());
        map.put("schoolProvince",detail.getSchoolProvince());
        map.put("schoolCity", detail.getSchoolCity());
        map.put("entranceYear", detail.getEntranceYear());

        return Response.status(Response.Status.OK).entity(map).build();
    }

    @PUT
    @Path("/{param}/detail")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "update one userDetail successful")})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserDetail(
            @ApiParam(name = "userId", value = "int", required = true)
            @PathParam("param") int userId,
            UserDetail userDetail
    ) {
        userMapper.updateUserDetail(userDetail);

        Map<String, Object> map = new HashMap<>();
        map.put("uri", "userDetail/" + userDetail.getUserId());

        return Response.status(Response.Status.NO_CONTENT).entity(map).build();
    }

    @PUT
    @Path("/{param}/password")
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "userId", value = "int", required = true),
            @ApiImplicitParam(name = "userPasswordMap",
                    value = "include all info when update user password", required = true)})
    @ApiResponses(value = {@ApiResponse(code = 204, message = "update user password successful"),
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

            return Response.status(Response.Status.NO_CONTENT).entity(map).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
