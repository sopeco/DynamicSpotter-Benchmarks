package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_21_ClearSync;

/**
 * A loadscript for the OLB.
 * 
 * @author Denis Knoepfle
 * 
 */
public class VU_21_ClearSync extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_21_ClearSync.class.getSimpleName());
	}

}
