package br.com.ticketballws.resouces;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.ticketballws.controller.UserController;
import br.com.ticketballws.model.Users;

@Path("/services")
@Produces(MediaType.APPLICATION_XML)
public class TicketBallResouces {
	
	@GET
	@Path("/getLogin")
	@Produces("application/xml")
	public Users buscaLogin(@QueryParam("login") String login){
		Users user = null;
		try {
			user = UserController.buscaLogin(login);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@GET
	@Path("/getUser")
	@Produces("application/xml")
	public Users buscaUser(@QueryParam("idUser") int idUser){
		Users user = null;
		try {
			user = UserController.buscaUser(idUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@POST
	@Path("/sendUser")
	@Consumes("application/xml")
	public Response insertUser(Users user){
		int retorno = -1;
		try {
			retorno = UserController.insertUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(String.valueOf(retorno)).build();
	}
	
}
