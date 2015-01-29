package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_11_ManyDiffDBCalls;

public class VU_11_ManyDiffDBCalls extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_11_ManyDiffDBCalls.class.getSimpleName());
	}

}
