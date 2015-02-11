package org.spotter.benchmark.app.problems;

import java.util.Random;

/**
 * The Ramp problem.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class TC_06_MonotoneRamp extends Problem {

	private static final long START_SLEEP = 80;
	private static final long SLEEP_TIME_INCREASE = 6;
	private static final long MIN_DEVIATION = 1;
	private static final long MAX_DEVIATION = 5;
	private static final Random RAND = new Random(System.nanoTime());

	private volatile long sleepTime;
	private static TC_06_MonotoneRamp instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_06_MonotoneRamp getInstance() {
		if (instance == null) {
			instance = new TC_06_MonotoneRamp();
		}
		return instance;
	}

	private TC_06_MonotoneRamp() {
		this.sleepTime = START_SLEEP;
	}

	@Override
	public void test() {
		try {
			Thread.sleep(sleepTime);
			System.out.println("Slept for " + sleepTime + " ms");
			if (nextDouble() < 0.01) {
				increaseSleepTime();
			}

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void increaseSleepTime() {
		long diff = MAX_DEVIATION - MIN_DEVIATION;
		long randomDeviation = (long) (nextDouble() * diff) + MIN_DEVIATION;
		this.sleepTime += SLEEP_TIME_INCREASE + randomDeviation;
		System.out.println("new sleep time will be " + this.sleepTime + " ms");
	}

	private synchronized double nextDouble() {
		return RAND.nextDouble();
	}

}
