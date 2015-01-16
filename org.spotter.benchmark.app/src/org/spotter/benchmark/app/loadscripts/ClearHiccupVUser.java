package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.ClearHiccupProblem;

/**
 * A loadscript for the clear hiccups problem.
 * 
 * @author Denis Knoepfle
 * 
 */
public class ClearHiccupVUser extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(ClearHiccupProblem.NAME);
	}

}
