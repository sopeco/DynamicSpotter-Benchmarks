package org.spotter.benchmark.app.problems;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spotter.shared.configuration.ConfigKeys;

/**
 * This class simulates the case where there is no problem but with outliers.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class TC_02_NoProblemWithOutliers extends Problem {

	
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	private static final Logger LOGGER = LoggerFactory.getLogger(TC_02_NoProblemWithOutliers.class);
	private static final double _HUNDRED = 100.0;

	private static final int PERFORMANCE_REQU_THRESHOLD = ConfigKeys.DEFAULT_PERFORMANCE_REQUIREMENT_THRESHOLD;
	private static final double OUTLIER_PERCENTAGE = 0.006;
	private static final double SLEEP_TIME_PERCENTAGE = 0.5;
	private static final double MIN_EXCEEDING_PERCENTAGE = 0.01;
	private static final double MAX_EXCEEDING_PERCENTAGE = 0.5;

	private static TC_02_NoProblemWithOutliers instance;

	private final int normalSleepTime;
	private int iterationsCount;
	private int outliersCount;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_02_NoProblemWithOutliers getInstance() {
		if (instance == null) {
			instance = new TC_02_NoProblemWithOutliers();
		}
		return instance;
	}

	private TC_02_NoProblemWithOutliers() {
		this.normalSleepTime = (int) (SLEEP_TIME_PERCENTAGE * PERFORMANCE_REQU_THRESHOLD);
		this.iterationsCount = 0;
		this.outliersCount = 0;
	}

	@Override
	public void test() {
		try {
			iterationsCount++;
			int sleepTime = normalSleepTime;
			if (RANDOM.nextDouble() < OUTLIER_PERCENTAGE) {
				outliersCount++;
				double increaseFactor = 1.0 + RANDOM.nextDouble() * MAX_EXCEEDING_PERCENTAGE + MIN_EXCEEDING_PERCENTAGE;
				sleepTime = (int) (increaseFactor * PERFORMANCE_REQU_THRESHOLD);
				String outlierPercentage = String.format("%.2f", _HUNDRED * outliersCount / iterationsCount);
				LOGGER.debug(String.format("%d outliers of %d so far (%s %%), sleeping %d sec", outliersCount,
						iterationsCount, outlierPercentage, sleepTime));
			}
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
