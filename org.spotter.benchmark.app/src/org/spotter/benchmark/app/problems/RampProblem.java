package org.spotter.benchmark.app.problems;

import java.util.Random;

/**
 * The Ramp problem.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class RampProblem implements Problem {

	public static final String NAME = "ramp";

	private static final long SLEEP_TIME_INCREASE = 6;
	private static final long MIN_DEVIATION = 1;
	private static final long MAX_DEVIATION = 5;
	private static final Random RAND = new Random(System.nanoTime());

	private long sleepTime;
	private static RampProblem instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized RampProblem getInstance() {
		if (instance == null) {
			instance = new RampProblem();
		}
		return instance;
	}

	private RampProblem() {
		this.sleepTime = 0;
	}

	@Override
	public void test() {
		try {
			Thread.sleep(sleepTime);
			System.out.println("Slept for " + sleepTime + " ms");
			increaseSleepTime();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void increaseSleepTime() {
		long diff = MAX_DEVIATION - MIN_DEVIATION;
		long randomDeviation = (RAND.nextLong() % (2 * diff)) - diff + MIN_DEVIATION;
		this.sleepTime = this.sleepTime + SLEEP_TIME_INCREASE + randomDeviation;
		System.out.println("new sleep time will be " + this.sleepTime + " ms");
	}

}
