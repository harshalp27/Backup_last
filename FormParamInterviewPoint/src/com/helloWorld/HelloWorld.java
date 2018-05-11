package com.helloWorld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/welcome")
public class HelloWorld 
{

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response displayHelloMessage(){
		String output = "Welcome to RESTful Jersey example XML- ";
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response displayHelloMessageText(){
		String output = "Welcome to RESTful Jersey example Text- ";
		return Response.status(200).entity(output).build();
	}
	
}
