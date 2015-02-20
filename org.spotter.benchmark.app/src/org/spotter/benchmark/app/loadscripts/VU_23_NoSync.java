package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_23_NoSync;

public class VU_23_NoSync extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_23_NoSync.class.getSimpleName());
	}
}
