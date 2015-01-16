package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.NoProblemWithOutliers;

/**
 * A loadscript for no problem with outliers.
 * 
 * @author Denis Knoepfle
 * 
 */
public class NoProblemWithOutliersVUser extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(NoProblemWithOutliers.NAME);
	}

}
