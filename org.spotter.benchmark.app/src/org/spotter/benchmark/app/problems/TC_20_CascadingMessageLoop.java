package org.spotter.benchmark.app.problems;

import java.util.Random;

import org.spotter.benchmark.app.problems.jms.ESTSender;

public class TC_20_CascadingMessageLoop extends Problem {
	private static final int NUM_MESSAGES = 30;
	private static final int NUM_MESSAGES_DEVIATION = 20;

	private static final Random RAND = new Random(System.nanoTime());

	private static TC_20_CascadingMessageLoop instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_20_CascadingMessageLoop getInstance() {
		if (instance == null) {
			instance = new TC_20_CascadingMessageLoop();
		}
		return instance;
	}

	@Override
	public void test() {
		method1();
	}

	private void method1() {
		method2();
		for (int i = 0; i < 5; i++) {
			method3();
		}
	}

	private void method2() {
		for (int i = 0; i < (NUM_MESSAGES + RAND.nextInt(NUM_MESSAGES_DEVIATION)) / 5; i++) {
			method4();
		}
	}

	private void method3() {
		method5();
		method7();
	}

	private void method4() {
		for (int i = 0; i < 4; i++) {
			method6();
		}
	}

	private void method5() {
		method10();
	}

	private void method6() {
		ESTSender.getInstance().sendMessage(80, 5);
	}

	private void method7() {
		method8();
	}

	private void method8() {
		method9();
	}

	private void method9() {
		method6();
	}

	private void method10() {
		method8();
	}
}
