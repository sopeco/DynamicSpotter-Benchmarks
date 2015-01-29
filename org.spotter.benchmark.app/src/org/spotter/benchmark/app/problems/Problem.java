package org.spotter.benchmark.app.problems;

/**
 * Interface for a problem that should be tested.
 * 
 * @author Denis Knoepfle
 * 
 */
public abstract class Problem {

	/**
	 * Tests this problem.
	 */
	abstract public void test();
	
	public String getName(){
		return this.getClass().getSimpleName();
	}
}
