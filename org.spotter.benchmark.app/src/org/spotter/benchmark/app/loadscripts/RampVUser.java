package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.RampProblem;

public class RampVUser extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(RampProblem.NAME);
	}

}
