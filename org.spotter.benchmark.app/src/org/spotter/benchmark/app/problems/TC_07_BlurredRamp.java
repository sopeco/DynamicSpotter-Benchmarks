package org.spotter.benchmark.app.problems;

import java.util.Random;

/**
 * The Ramp problem.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class TC_07_BlurredRamp extends Problem {

	
	private static final long START_SLEEP = 800;
	private static final double SLEEP_DEVIATION = 0.25;
	private static final long SLEEP_TIME_INCREASE = 4;
	private static final long MIN_DEVIATION = 1;
	private static final long MAX_DEVIATION = 2;
	private static final long MAX_ABSOLUTE_DEVIATION = 200;
	private static final Random RAND = new Random(System.nanoTime());

	private volatile long sleepTime;
	private static TC_07_BlurredRamp instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_07_BlurredRamp getInstance() {
		if (instance == null) {
			instance = new TC_07_BlurredRamp();
		}
		return instance;
	}

	private TC_07_BlurredRamp() {
		this.sleepTime = START_SLEEP;
	}

	@Override
	public void test() {
		try {
			long calSleepTime = sleepTime + (long) (((2.0 * (nextDouble() - 0.5)) * SLEEP_DEVIATION) * (double)sleepTime);
			if(calSleepTime > sleepTime){
				calSleepTime = Math.min(calSleepTime, sleepTime + MAX_ABSOLUTE_DEVIATION);
			}else{
				calSleepTime = Math.max(calSleepTime, sleepTime - MAX_ABSOLUTE_DEVIATION);
			}
		
			Thread.sleep(calSleepTime);
			System.out.println("Slept for " + sleepTime + " ms");
			if (nextDouble() < 0.001) {
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
