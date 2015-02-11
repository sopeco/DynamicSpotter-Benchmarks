package org.spotter.benchmark.app.problems;

import java.util.Random;

import org.lpe.common.util.system.LpeSystemUtils;

/**
 * The hiccup problem with clearly defined hiccups.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class TC_03_ClearHiccup extends Problem {

	private static final long NORMAL_SLEEP_TIME = 80; // [ms]
	private static final long HICCUP_PHASE_SLEEP_TIME = 1400; // [ms]
	private static final double SLEEP_DEVIATION = 0.4;

	private static final long HICCUP_DURATION = 5000; // [ms]
	private static final long MIN_TO_NEXT_HICCUP = 10000; // [ms]
	private static final double HICCUP_DURATION_DEVIATION = 0.9;
	private static final double HICCUP_BEGIN_PROB = 0.002;

	private static final Random RAND = new Random(System.nanoTime());
	private static final double HALF = 0.5;

	private volatile boolean isHiccupPhase;
	private volatile boolean canHiccup;
	private static TC_03_ClearHiccup instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_03_ClearHiccup getInstance() {
		if (instance == null) {
			instance = new TC_03_ClearHiccup();
		}
		return instance;
	}

	private TC_03_ClearHiccup() {
		this.isHiccupPhase = false;
		this.canHiccup = true;
	}

	@Override
	public void test() {
		try {
			long sleepTime = getSleepTime();
			Thread.sleep(sleepTime);
			System.out.println("Slept for " + sleepTime + " ms");
			if (!isHiccupPhase && canHiccup && nextDouble() < HICCUP_BEGIN_PROB) {
				beginHiccup();
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @return the next sleep time
	 */
	private long getSleepTime() {
		long baseSleepTime = isHiccupPhase ? HICCUP_PHASE_SLEEP_TIME : NORMAL_SLEEP_TIME;
		return baseSleepTime + (long) (((2.0 * (nextDouble() - HALF)) * SLEEP_DEVIATION) * (double) baseSleepTime);
	}

	private long calcHiccupDuration() {
		return HICCUP_DURATION
				+ (long) (((2.0 * (nextDouble() - HALF)) * HICCUP_DURATION_DEVIATION) * (double) HICCUP_DURATION);
	}

	private synchronized void beginHiccup() {
		if (isHiccupPhase) {
			return;
		}
		isHiccupPhase = true;
		canHiccup = false;
		final long hiccupDuration = calcHiccupDuration();
		System.out.println("Started a hiccup phase for " + hiccupDuration + " ms");

		LpeSystemUtils.submitTask(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(hiccupDuration);
				} catch (InterruptedException e) {
					throw new RuntimeException();
				}
				endHiccup();
				try {
					Thread.sleep(MIN_TO_NEXT_HICCUP + (long)(60000.0*RAND.nextDouble()));
				} catch (InterruptedException e) {
					throw new RuntimeException();
				}
				canHiccup = true;
				System.out.println("Ready for next hiccup again.");
			}
		});
	}

	private synchronized void endHiccup() {
		isHiccupPhase = false;
	}

	private synchronized double nextDouble() {
		return RAND.nextDouble();
	}

}
