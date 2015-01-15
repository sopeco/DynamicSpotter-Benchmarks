package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.RampProblem;

/**
 * A loadscript for the Ramp.
 * 
 * @author Denis Knoepfle
 * 
 */
public class RampVUser extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(RampProblem.NAME);
	}

}
