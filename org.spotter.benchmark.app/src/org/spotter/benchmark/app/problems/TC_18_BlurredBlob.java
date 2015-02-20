package org.spotter.benchmark.app.problems;

import java.util.Random;

import org.spotter.benchmark.app.problems.jms.BlurredBlob;

public class TC_18_BlurredBlob extends Problem {

	private static final Random RAND = new Random(System.nanoTime());

	private static TC_18_BlurredBlob instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_18_BlurredBlob getInstance() {
		if (instance == null) {
			instance = new TC_18_BlurredBlob();
		}
		return instance;
	}

	@Override
	public void test() {
		BlurredBlob.getInstance().sendMessage(4000);
	}
}
