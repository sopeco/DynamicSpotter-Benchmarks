package org.spotter.benchmark.dummyjdbc.connection.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.spotter.benchmark.dummyjdbc.connection.ConnectionAdapter;
import org.spotter.benchmark.dummyjdbc.statement.impl.RESTPreparedStatement;
import org.spotter.benchmark.dummyjdbc.statement.impl.RESTStatement;

public class RESTConnection extends ConnectionAdapter {
	@Override
	public Statement createStatement() throws SQLException {
		return new RESTStatement();
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return new RESTPreparedStatement(sql);
	};
}
