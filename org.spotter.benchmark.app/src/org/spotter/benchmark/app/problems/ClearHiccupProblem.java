package org.spotter.benchmark.app.problems;

/**
 * The hiccup problem with clearly defined hiccups.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class ClearHiccupProblem implements Problem {

	public static final String NAME = "clear hiccup";

	private static ClearHiccupProblem instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized ClearHiccupProblem getInstance() {
		if (instance == null) {
			instance = new ClearHiccupProblem();
		}
		return instance;
	}

	private ClearHiccupProblem() {
	}

	@Override
	public void test() {
		// not yet implemented
	}

}
