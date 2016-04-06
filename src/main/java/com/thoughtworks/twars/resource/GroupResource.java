package com.thoughtworks.twars.resource;

import com.thoughtworks.twars.bean.Group;
import com.thoughtworks.twars.mapper.*;
import io.swagger.annotations.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.*;

@Path("/groups")
@Api
public class GroupResource extends Resource{
    @Inject
    private GroupMapper groupMapper;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGroup(Group group)
    {
        groupMapper.insertGroup(group);
        Map<String, String> map = new HashMap<>();
        map.put("uri", "/groups/" + group.getId());

        return Response.status(Response.Status.CREATED).entity(map).build();
    }
}



