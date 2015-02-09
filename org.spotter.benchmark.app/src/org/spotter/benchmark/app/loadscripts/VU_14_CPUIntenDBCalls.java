package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_14_CPUIntenDBCalls;

public class VU_14_CPUIntenDBCalls extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_14_CPUIntenDBCalls.class.getSimpleName());
	}

}
