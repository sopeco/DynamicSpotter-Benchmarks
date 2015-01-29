package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_03_ClearHiccup;

/**
 * A loadscript for the clear hiccups problem.
 * 
 * @author Denis Knoepfle
 * 
 */
public class VU_03_ClearHiccup extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_03_ClearHiccup.class.getSimpleName());
	}

}
