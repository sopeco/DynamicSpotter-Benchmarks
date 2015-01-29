package org.spotter.benchmark.dummyjdbc.statement.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.core.MediaType;

import org.lpe.common.util.web.LpeWebUtils;
import org.spotter.benchmark.app.BenchmarkAppLauncher;
import org.spotter.benchmark.dummyjdbc.resultset.DummyResultSet;
import org.spotter.benchmark.dummyjdbc.statement.StatementAdapter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class RESTStatement extends StatementAdapter {
	private static WebResource webResource;
	private static Client client;
	static {
		client = LpeWebUtils.getWebClient();
		client.setConnectTimeout(1000 * 60 * 60);
		client.setReadTimeout(1000 * 60 * 60);
		int port = BenchmarkAppLauncher.DUMMY_DB_PORT;
		webResource = client.resource("http://" + BenchmarkAppLauncher.DUMMY_DB_HOST + ":" + port + "/");
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		webResource.path("dummyDB").path("call").path(sql).accept(MediaType.APPLICATION_JSON).get(String.class);
		return new DummyResultSet();
	}

}
