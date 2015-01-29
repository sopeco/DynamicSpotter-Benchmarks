/**
 * Copyright 2014 SAP AG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.spotter.benchmark.app.problems;

import java.util.Random;

import org.lpe.common.util.system.LpeSystemUtils;

/**
 * The hiccup problem with slowly rising response times.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class TC_04_RisingHiccup extends Problem {

	private static final long NORMAL_SLEEP_TIME = 80; // [ms]
	private static final long HICCUP_PEEK_SLEEP_TIME = 1800; // [ms]
	private static final double SLEEP_DEVIATION = 0.25;

	private static final long HICCUP_DURATION = 6000; // [ms]
	private static final long MIN_TO_NEXT_HICCUP = 4000; // [ms]
	private static final double HICCUP_DURATION_DEVIATION = 0.5;
	private static final double HICCUP_BEGIN_PROB = 0.002;

	private static final Random RAND = new Random(System.nanoTime());
	private static final double HALF = 0.5;

	private volatile boolean isHiccupPhase;
	private volatile boolean canHiccup;
	private volatile long hiccupDuration;
	private volatile long hiccupPeekSleepTime;
	private volatile long hiccupStartTime;
	private static TC_04_RisingHiccup instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_04_RisingHiccup getInstance() {
		if (instance == null) {
			instance = new TC_04_RisingHiccup();
		}
		return instance;
	}

	private TC_04_RisingHiccup() {
		this.isHiccupPhase = false;
		this.canHiccup = true;
	}

	@Override
	public void test() {
		try {
			long sleepTime = getSleepTime();
			Thread.sleep(sleepTime);
			System.out.println("Slept for " + sleepTime + " ms");
			if (canHiccup && nextDouble() < HICCUP_BEGIN_PROB) {
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
		long baseSleepTime = NORMAL_SLEEP_TIME;
		long duration = hiccupDuration;
		long passedTime = System.currentTimeMillis() - hiccupStartTime;
		double frac = (double) passedTime / duration;

		if (isHiccupPhase && duration > 0) {
			if (frac > HALF) {
				baseSleepTime = (long) (hiccupPeekSleepTime - (frac - HALF) * (hiccupPeekSleepTime - NORMAL_SLEEP_TIME));
			} else {
				baseSleepTime = (long) (NORMAL_SLEEP_TIME + frac * (hiccupPeekSleepTime - NORMAL_SLEEP_TIME));
			}
		}

		return baseSleepTime + (long) (((2.0 * (nextDouble() - HALF)) * SLEEP_DEVIATION) * (double) baseSleepTime);
	}

	private long calcHiccupDuration() {
		return HICCUP_DURATION
				+ (long) (((2.0 * (nextDouble() - HALF)) * HICCUP_DURATION_DEVIATION) * (double) HICCUP_DURATION);
	}

	private long calcHiccupPeekSleepTime() {
		return HICCUP_PEEK_SLEEP_TIME
				+ (long) (((2.0 * (nextDouble() - HALF)) * SLEEP_DEVIATION) * (double) HICCUP_PEEK_SLEEP_TIME);
	}

	private synchronized void beginHiccup() {
		if (isHiccupPhase) {
			return;
		}
		canHiccup = false;
		isHiccupPhase = true;
		hiccupStartTime = System.currentTimeMillis();
		hiccupDuration = calcHiccupDuration();
		hiccupPeekSleepTime = calcHiccupPeekSleepTime();
		System.out.println("Started a hiccup phase for " + hiccupDuration + " ms with a peek of " + hiccupPeekSleepTime
				+ " ms");

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
				hiccupDuration = 0;
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
