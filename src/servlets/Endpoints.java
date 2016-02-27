package servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

import core.SessionManager;

@Path("/")
public class Endpoints {

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public Response getInt(@Context HttpServletRequest request,
	    @Context HttpServletResponse response) {
		request.setAttribute("id",
		    SessionManager.getInstance().generateSessionId());
		Viewable view = new Viewable("/mancala.jsp");
		return Response.ok().entity(view).build();
	}

	@POST
	@Path("/start")
	@Produces(MediaType.TEXT_HTML)
	public Response startGame(@Context HttpServletRequest request,
	    @Context HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		request.setAttribute("started", true);
		Viewable view = new Viewable("/mancala.jsp");
		return Response.ok().entity(view).build();
	}
}
