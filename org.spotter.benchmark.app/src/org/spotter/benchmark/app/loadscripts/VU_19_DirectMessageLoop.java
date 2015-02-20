package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_19_DirectMessageLoop;

public class VU_19_DirectMessageLoop extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_19_DirectMessageLoop.class.getSimpleName());
	}

}
