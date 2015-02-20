package org.spotter.benchmark.dummyjdbc.server.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("dummyDB")
public class DummyDBRest {
	/**
	 * Tests the given problem.
	 * 
	 * @param problemName
	 *            the name of the problem to test
	 * @return hello string
	 */
	@GET
	@Path("call" + "/{command}")
	@Produces(MediaType.APPLICATION_JSON)
	public String call(@PathParam("command") String command) {
		return DummyDB.getInstance().call(command);
	}

	@GET
	@Path("getStatistics")
	@Produces(MediaType.APPLICATION_JSON)
	public String getStatistics() {
		return DummyDB.getInstance().getStatistics();
	}

	@GET
	@Path("testConnection")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean testConnection() {
		return true;
	}

}
