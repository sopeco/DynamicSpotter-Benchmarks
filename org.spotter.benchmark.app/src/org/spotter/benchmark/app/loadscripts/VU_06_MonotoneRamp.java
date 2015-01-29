package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_06_MonotoneRamp;

/**
 * A loadscript for the Ramp.
 * 
 * @author Denis Knoepfle
 * 
 */
public class VU_06_MonotoneRamp extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_06_MonotoneRamp.class.getSimpleName());
	}

}
