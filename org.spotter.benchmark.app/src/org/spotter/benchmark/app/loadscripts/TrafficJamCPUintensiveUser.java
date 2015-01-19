package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TrafficJamCPUIntensiveProblem;

public class TrafficJamCPUintensiveUser extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TrafficJamCPUIntensiveProblem.NAME);
	}
}
