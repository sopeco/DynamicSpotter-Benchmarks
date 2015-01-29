package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_11_ManyDiffDBCalls;

public class TC_11_ManyDiffDBCallsVUser extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_11_ManyDiffDBCalls.NAME);
	}

}
