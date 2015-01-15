package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.OLBProblem;

/**
 * A loadscript for the OLB.
 * 
 * @author Denis Knoepfle
 * 
 */
public class OLBVUser extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(OLBProblem.NAME);
	}

}
