package org.spotter.benchmark.dummyjdbc.statement.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.spotter.benchmark.dummyjdbc.statement.PreparedStatementAdapter;

public class RESTPreparedStatement extends PreparedStatementAdapter {
	private final RESTStatement statement;
	private final String sql;

	/**
	 * Constructs a new {@link RESTPreparedStatement}.
	 * 
	 * @param sql
	 *            the SQL statement.
	 */
	public RESTPreparedStatement(String sql) {
		this.statement = new RESTStatement();
		this.sql = sql;
	}

	@Override
	public ResultSet executeQuery() throws SQLException {
		return statement.executeQuery(sql);
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		return statement.executeQuery(sql);
	}
}
