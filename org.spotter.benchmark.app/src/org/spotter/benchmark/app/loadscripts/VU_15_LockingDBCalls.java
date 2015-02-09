package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_15_LockingDBCalls;

public class VU_15_LockingDBCalls extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_15_LockingDBCalls.class.getSimpleName());
	}

}
