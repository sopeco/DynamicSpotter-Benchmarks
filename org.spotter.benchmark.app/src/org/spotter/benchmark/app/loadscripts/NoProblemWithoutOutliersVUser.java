package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.NoProblemWithoutOutliers;

/**
 * A loadscript for no problem without outliers.
 * 
 * @author Denis Knoepfle
 * 
 */
public class NoProblemWithoutOutliersVUser extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(NoProblemWithoutOutliers.NAME);
	}

}
