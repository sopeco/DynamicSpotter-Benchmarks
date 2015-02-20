package org.spotter.benchmark.app.problems;

import java.util.Random;

import org.spotter.benchmark.app.problems.jms.Blob;

public class TC_17_ClearBlob extends Problem {

	private static final Random RAND = new Random(System.nanoTime());

	private static TC_17_ClearBlob instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_17_ClearBlob getInstance() {
		if (instance == null) {
			instance = new TC_17_ClearBlob();
		}
		return instance;
	}

	@Override
	public void test() {
		Blob.getInstance().sendMessage(5000);
	}
}
