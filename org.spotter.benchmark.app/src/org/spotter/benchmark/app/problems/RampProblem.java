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

	
	private static final long START_SLEEP = 80;
	private static final long SLEEP_DEVIATION = 50;
	private static final long SLEEP_TIME_INCREASE = 6;
	private static final long MIN_DEVIATION = 1;
	private static final long MAX_DEVIATION = 5;
	private static final Random RAND = new Random(System.nanoTime());

	private volatile long sleepTime;
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
		this.sleepTime = START_SLEEP;
	}

	@Override
	public void test() {
		try {
			Thread.sleep(sleepTime + (long)(2.0*(getRand().nextDouble()-0.5))*SLEEP_DEVIATION);
			System.out.println("Slept for " + sleepTime + " ms");
			if (getRand().nextDouble() < 0.05) {
				increaseSleepTime();
			}

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void increaseSleepTime() {
		long diff = MAX_DEVIATION - MIN_DEVIATION;
		long randomDeviation = (long) (getRand().nextDouble() * diff) + MIN_DEVIATION;
		this.sleepTime += SLEEP_TIME_INCREASE + randomDeviation;
		System.out.println("new sleep time will be " + this.sleepTime + " ms");
	}
	
	private synchronized Random getRand(){
		return RAND;
	}

}
