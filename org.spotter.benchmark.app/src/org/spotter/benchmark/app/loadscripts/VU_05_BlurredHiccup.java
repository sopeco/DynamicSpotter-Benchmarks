package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_05_BlurredHiccup;

/**
 * A loadscript for the GC hiccups problem.
 * 
 * @author Denis Knoepfle
 * 
 */
public class VU_05_BlurredHiccup extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_05_BlurredHiccup.NAME);
	}

}
