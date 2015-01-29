package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_07_BlurredRamp;

/**
 * A loadscript for the Ramp.
 * 
 * @author Denis Knoepfle
 * 
 */
public class VU_07_BlurredRamp extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_07_BlurredRamp.class.getSimpleName());
	}

}
