package org.spotter.benchmark.app.problems;

import org.spotter.shared.configuration.ConfigKeys;

/**
 * This class simulates the case where there is no problem without outliers.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class NoProblemWithoutOutliers implements Problem {

	public static final String NAME = "no problem without outliers";

	private static final int PERFORMANCE_REQU_THRESHOLD = ConfigKeys.DEFAULT_PERFORMANCE_REQUIREMENT_THRESHOLD;
	private static final double SLEEP_TIME_PERCENTAGE = 0.5;

	private static NoProblemWithoutOutliers instance;

	private final int sleepTime;

	/**
	 * @return the singleton instance
	 */
	public static synchronized NoProblemWithoutOutliers getInstance() {
		if (instance == null) {
			instance = new NoProblemWithoutOutliers();
		}
		return instance;
	}

	private NoProblemWithoutOutliers() {
		this.sleepTime = (int) (SLEEP_TIME_PERCENTAGE * PERFORMANCE_REQU_THRESHOLD);
	}

	@Override
	public void test() {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
