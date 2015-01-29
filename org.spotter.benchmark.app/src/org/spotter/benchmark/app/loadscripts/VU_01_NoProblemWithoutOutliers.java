package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_01_NoProblemWithoutOutliers;

/**
 * A loadscript for no problem without outliers.
 * 
 * @author Denis Knoepfle
 * 
 */
public class VU_01_NoProblemWithoutOutliers extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_01_NoProblemWithoutOutliers.class.getSimpleName());
	}

}
