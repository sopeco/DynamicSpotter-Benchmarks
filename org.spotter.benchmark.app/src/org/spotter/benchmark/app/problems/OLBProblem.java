package org.spotter.benchmark.app.problems;

public class OLBProblem implements Problem {

	public static final String NAME = "olb";

	private static final int FIB_NUMBER = 25;
	private static OLBProblem instance;

	public static synchronized OLBProblem getInstance() {
		if (instance == null) {
			instance = new OLBProblem();
		}
		return instance;
	}

	private OLBProblem() {
	}

	@Override
	public void test() {
		syncFibonacci();
	}

	public synchronized void syncFibonacci() {
		fibonacci(FIB_NUMBER);
	}

	private int fibonacci(int n) {
		if (n <= 1) {
			return 1;
		} else {
			return fibonacci(n - 2) + fibonacci(n - 1);
		}
	}

}
