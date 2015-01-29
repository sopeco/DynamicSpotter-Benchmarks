package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_02_NoProblemWithOutliers;

/**
 * A loadscript for no problem with outliers.
 * 
 * @author Denis Knoepfle
 * 
 */
public class VU_02_NoProblemWithOutliers extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_02_NoProblemWithOutliers.class.getSimpleName());
	}

}
