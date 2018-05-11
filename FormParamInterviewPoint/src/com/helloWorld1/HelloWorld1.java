package com.helloWorld1;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/hello")
public class HelloWorld1 {

	@GET
	@Path("{username}")
	public Response welcome(@PathParam("username") String name){
		return Response.status(200).entity("Welcome to Hello World program " + name).build();
	}

	@GET
	@Path("name/{id}/{age}")
	public Response getUserDetails(
			@PathParam("id") int id,
			@PathParam("age") int age){
		return Response.status(200).entity("Welcome = " + " id = " + id + " age " + age).build();
	}

	@GET
	@Path("/query")
	public Response getQueryParameters(
			@QueryParam("parameter1")String parameter1,
			@QueryParam("parameter2")String parameter2)
	{
		String output = "Parameter1 :"+parameter1+" Parameter2 : "+parameter2;
		System.out.println(parameter1);
		System.out.println(parameter2);
		return Response.status(200).entity(output).build();
	}

	@GET
	public Response getQueryParameters(@Context UriInfo uri)
	{
		String parameter1 = uri.getQueryParameters().getFirst("parameter1");
		String parameter2   = uri.getQueryParameters().getFirst("parameter2");
		String output = "Parameter1 :"+parameter1+" Parameter2 : "+parameter2.toString();
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/matrixParam")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMatrixParameter(
			@MatrixParam("author") String author,
			@MatrixParam("year") int year,
			@MatrixParam("book") String book)
	{
		return "Author : "+author+" Book : "+book+" Year : "+year;
	}
}
