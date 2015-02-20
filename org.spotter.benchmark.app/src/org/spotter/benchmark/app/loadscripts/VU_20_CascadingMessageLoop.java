package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_20_CascadingMessageLoop;

public class VU_20_CascadingMessageLoop extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_20_CascadingMessageLoop.class.getSimpleName());
	}

}
