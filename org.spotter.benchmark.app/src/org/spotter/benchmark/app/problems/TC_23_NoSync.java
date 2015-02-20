package org.spotter.benchmark.app.problems;

import java.util.Random;

public class TC_23_NoSync extends Problem {

	private static final int FIB_NUMBER = 23;
	private static final int FIB_NUMBER_DEV = 7;
	private static final Random RAND = new Random(System.nanoTime());
	private static TC_23_NoSync instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_23_NoSync getInstance() {
		if (instance == null) {
			instance = new TC_23_NoSync();
		}
		return instance;
	}

	private TC_23_NoSync() {
	}

	@Override
	public void test() {
		utilizeCPU();
//		for (int i = 0; i < 30 + RAND.nextInt(30); i++) {
//			fibonacci(FIB_NUMBER + RAND.nextInt(5), 1 + RAND.nextInt(50));
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}

	}

	private int fibonacci(int n, int k) {
		if (n <= 1) {
			return k;
		} else {
			return fibonacci(n - 2, k) + fibonacci(n - 1, k);
		}
	}

	private void utilizeCPU() {
		for (int i = 0; i < 500; i++) {
			for (int j = 0; j < 700; j++) {
				int op = RAND.nextInt(3);
				double d = 0.0;
				switch (op) {
				case 0:
					d = RAND.nextDouble() + RAND.nextDouble();
					break;
				case 1:
					d = RAND.nextDouble() * RAND.nextDouble();
					break;
				case 2:
					d = RAND.nextDouble() / RAND.nextDouble();
					break;

				default:
					d = RAND.nextDouble() + RAND.nextDouble();
					break;
				}
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
