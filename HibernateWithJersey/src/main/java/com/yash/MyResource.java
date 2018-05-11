package com.yash;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.yash.dao.User;
import com.yash.dao.UserDao;

@Path("/userResource")
public class MyResource {

	@GET
	@Path("userCache/{id}")

	public Response getUserWithEtagSupport(@PathParam("id") int id,@Context Request request){
		User user = new User();
		EntityTag tag = new EntityTag(Integer.toString(user.hashCode()));
		System.out.println(tag);
		CacheControl cc = null;	  
		ResponseBuilder builder = request.evaluatePreconditions(tag);

		if(builder!=null){
			
			System.out.println("Same Request is called ");
			cc = new CacheControl();
			cc.setMaxAge(1000000);
			builder.cacheControl(cc);
			// return builder.build();
		}       
		else   {    	 
			System.out.println("New Request is called ");
			

			user.setId(id);
			user.setName("Tom");
			user.setAge(23);
			//tag = new EntityTag(Integer.toString(user.hashCode()));
			tag = new EntityTag(Integer.toString(user.hashCode()));

			cc = new CacheControl();

			cc.setSMaxAge(1200000);
			cc.setNoCache(true);
			cc.setNoStore(true);
			cc.setPrivate(false);
			cc.setProxyRevalidate(false);
			cc.setMustRevalidate(true);       
			builder = Response.ok(user,MediaType.APPLICATION_XML);
			builder.tag(tag);       	   }      
			return builder.build();   } 

//	public Response getMsg() {
// 		String output = "Jersey GET say : hiii ";
// 		System.out.println("Create");
// 		return Response.status(200).entity(output).build();
// 	}

@GET
@Path("/getUser")
@Produces("application/json")
public List<User> getUser() {
	UserDao dao = new UserDao();
	List users = dao.getUser();

	return users;
}


@POST
@Path("/create")
@Consumes(MediaType.APPLICATION_JSON)
public Response addEmployee(User user){
	user.setName(user.getName());
	user.setAge(user.getAge());
	System.out.println("Create");
	UserDao dao = new UserDao();
	dao.addUser(user);
	System.out.println("Create");
	return Response.ok().build();
}

@PUT
@Path("/update/{id}")
@Consumes("application/json")
public Response updateUser(@PathParam("id") int id, User user){
	UserDao dao = new UserDao();
	int count = dao.updateUser(id, user);
	if(count==0){
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	return Response.ok().build();
}

@DELETE
@Path("/delete/{id}")
@Consumes("application/json")
public Response deleteUser(@PathParam("id") int id){
	UserDao dao = new UserDao();
	int count = dao.deleteUser(id);
	if(count==0){
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	return Response.ok().build();
}
}
