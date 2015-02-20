package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_17_ClearBlob;

public class VU_17_ClearBlob extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_17_ClearBlob.class.getSimpleName());
	}

}
