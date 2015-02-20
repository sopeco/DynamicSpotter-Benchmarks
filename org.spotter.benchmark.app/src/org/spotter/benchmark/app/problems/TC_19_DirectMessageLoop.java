package org.spotter.benchmark.app.problems;

import java.util.Random;

import org.spotter.benchmark.app.problems.jms.ESTSender;

public class TC_19_DirectMessageLoop extends Problem {

	private static final int NUM_MESSAGES = 30;
	private static final int NUM_MESSAGES_DEVIATION = 20;
	private static final Random RAND = new Random(System.nanoTime());

	private static TC_19_DirectMessageLoop instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_19_DirectMessageLoop getInstance() {
		if (instance == null) {
			instance = new TC_19_DirectMessageLoop();
		}
		return instance;
	}

	@Override
	public void test() {
		messageLoop();
	}
	
	
	private void messageLoop(){
		for(int i = 0; i< NUM_MESSAGES+RAND.nextInt(NUM_MESSAGES_DEVIATION);i++){
			ESTSender.getInstance().sendMessage(10,1);
		}
	}
}
