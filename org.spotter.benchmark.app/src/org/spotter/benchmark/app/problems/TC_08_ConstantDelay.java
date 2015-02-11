package org.spotter.benchmark.app.problems;

import java.util.Random;

/**
 * The continuous violation problem.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class TC_08_ConstantDelay extends Problem {

	private static final long SLEEP_TIME = 1200;
	private static final double SLEEP_DEVIATION = 0.5;
	private static final Random RAND = new Random(System.nanoTime());

	private static TC_08_ConstantDelay instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_08_ConstantDelay getInstance() {
		if (instance == null) {
			instance = new TC_08_ConstantDelay();
		}
		return instance;
	}

	private TC_08_ConstantDelay() {
	}

	@Override
	public void test() {
		try {
			long sleepTime = SLEEP_TIME
					+ (long) (((2.0 * (nextDouble() - 0.5)) * SLEEP_DEVIATION) * (double) SLEEP_TIME);
			Thread.sleep(sleepTime);
			System.out.println("Slept for " + sleepTime + " ms");
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private synchronized double nextDouble() {
		return RAND.nextDouble();
	}

}
