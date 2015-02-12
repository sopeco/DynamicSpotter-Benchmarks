package org.spotter.benchmark.app.problems;

import java.util.Random;

/**
 * The OLB problem with some outliers which is using a synchronized call to a
 * fibonacci calculation.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class TC_22_BlurredSync extends Problem {

	private static final int FIB_NUMBER = 30;
	private static final int FIB_NUMBER_DEV = 7;
	private static final double OUTLIER_PERCENTAGE = 0.2;
	private static final Random RAND = new Random(System.nanoTime());
	private static TC_22_BlurredSync instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_22_BlurredSync getInstance() {
		if (instance == null) {
			instance = new TC_22_BlurredSync();
		}
		return instance;
	}

	private TC_22_BlurredSync() {
	}

	@Override
	public void test() {
		blurredFibonacci();
	}

	/**
	 * Calculates a fibonacci number.
	 */
	public void blurredFibonacci() {
		long start = System.currentTimeMillis();
		if (nextDouble() < OUTLIER_PERCENTAGE) {
			fibonacci(FIB_NUMBER + RAND.nextInt(FIB_NUMBER_DEV));
		} else {
			syncFibonacci(FIB_NUMBER + RAND.nextInt(FIB_NUMBER_DEV));
		}
		System.out.println((System.currentTimeMillis() - start) + " ms");
	}

	private synchronized int syncFibonacci(int n) {
		return fibonacci(n);
	}

	private int fibonacci(int n) {
		if (n <= 1) {
			return 1;
		} else {
			return fibonacci(n - 2) + fibonacci(n - 1);
		}
	}

	private double nextDouble() {
		return RAND.nextDouble();
	}

}
