package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_10_CPUIntensiveApp;

public class VU_10_CPUintensiveApp extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_10_CPUIntensiveApp.class.getSimpleName());
	}
}
