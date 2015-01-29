package org.spotter.benchmark.app.problems;

import java.util.Random;

import org.lpe.common.util.system.LpeSystemUtils;

/**
 * The hiccup problem caused by random and excessive garbage collection.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class TC_05_BlurredHiccup extends Problem {

	private static final long NORMAL_SLEEP_TIME = 80; // [ms]
	private static final long HICCUP_PHASE_SLEEP_TIME = 1800; // [ms]
	private static final double SLEEP_DEVIATION = 0.4;
	private static final double HICCUP_OUTLIER_PERCENTAGE = 0.15;

	private static final long HICCUP_DURATION = 2500; // [ms]
	private static final long MIN_TO_NEXT_HICCUP = 4000; // [ms]
	private static final double HICCUP_DURATION_DEVIATION = 0.5;
	private static final double HICCUP_BEGIN_PROB = 0.002;

	private static final Random RAND = new Random(System.nanoTime());
	private static final double HALF = 0.5;

	private volatile boolean isHiccupPhase;
	private volatile boolean canHiccup;
	private static TC_05_BlurredHiccup instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_05_BlurredHiccup getInstance() {
		if (instance == null) {
			instance = new TC_05_BlurredHiccup();
		}
		return instance;
	}

	private TC_05_BlurredHiccup() {
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
		boolean inHiccup = isHiccupPhase;
		boolean isOutlier = inHiccup && nextDouble() < HICCUP_OUTLIER_PERCENTAGE;
		long baseSleepTime = inHiccup ? HICCUP_PHASE_SLEEP_TIME : NORMAL_SLEEP_TIME;
		long sleepTime = baseSleepTime
				+ (long) (((2.0 * (nextDouble() - HALF)) * SLEEP_DEVIATION) * (double) baseSleepTime);
		if (isOutlier) {
			sleepTime -= (long) ((HALF + nextDouble() * SLEEP_DEVIATION) * sleepTime);
			System.out.println("Generated hiccup outlier with a sleeptime of " + sleepTime);
		}
		return sleepTime;
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
					Thread.sleep(MIN_TO_NEXT_HICCUP);
				} catch (InterruptedException e) {
					throw new RuntimeException();
				}
				canHiccup = true;
				System.out.println("Ready for next hiccup again.");
			}
		});
	}

	private synchronized void endHiccup() {
		this.isHiccupPhase = false;
	}

	private synchronized double nextDouble() {
		return RAND.nextDouble();
	}

}
