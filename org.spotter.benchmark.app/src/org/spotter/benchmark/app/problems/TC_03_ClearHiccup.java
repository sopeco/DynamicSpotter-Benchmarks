package org.spotter.benchmark.app.problems;

/**
 * The hiccup problem with clearly defined hiccups.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class TC_03_ClearHiccup extends Problem {


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
	}

	@Override
	public void test() {
		// not yet implemented
	}

}
