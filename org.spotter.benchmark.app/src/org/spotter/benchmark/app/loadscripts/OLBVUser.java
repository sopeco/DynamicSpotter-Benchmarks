package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.OLBProblem;

public class OLBVUser extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(OLBProblem.NAME);
	}

}
