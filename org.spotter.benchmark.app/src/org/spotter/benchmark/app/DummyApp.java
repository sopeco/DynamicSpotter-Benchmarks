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
package org.spotter.benchmark.app;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spotter.benchmark.app.problems.Problem;
import org.spotter.benchmark.app.problems.TC_01_NoProblemWithoutOutliers;
import org.spotter.benchmark.app.problems.TC_02_NoProblemWithOutliers;
import org.spotter.benchmark.app.problems.TC_03_ClearHiccup;
import org.spotter.benchmark.app.problems.TC_04_RisingHiccup;
import org.spotter.benchmark.app.problems.TC_05_BlurredHiccup;
import org.spotter.benchmark.app.problems.TC_07_BlurredRamp;
import org.spotter.benchmark.app.problems.TC_08_ConstantDelay;
import org.spotter.benchmark.app.problems.TC_09_BlurredDelay;
import org.spotter.benchmark.app.problems.TC_10_CPUIntensiveApp;
import org.spotter.benchmark.app.problems.TC_11_ManyDiffDBCalls;
import org.spotter.benchmark.app.problems.TC_12_ManyEqualDBCalls;
import org.spotter.benchmark.app.problems.TC_13_ManySimilarDBCalls;
import org.spotter.benchmark.app.problems.TC_14_CPUIntenDBCalls;
import org.spotter.benchmark.app.problems.TC_15_LockingDBCalls;
import org.spotter.benchmark.app.problems.TC_21_ClearSync;

/**
 * Dummy Application.
 * 
 * @author Denis Knoepfle
 * 
 */
@Path("benchmark")
public class DummyApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(DummyApp.class);

	private final Map<String, Problem> problems = new HashMap<>();

	/**
	 * Constructor.
	 */
	public DummyApp() {
		populateProblems();
	}

	private void populateProblems() {
		problems.put(TC_01_NoProblemWithoutOutliers.class.getSimpleName(), TC_01_NoProblemWithoutOutliers.getInstance());
		problems.put(TC_02_NoProblemWithOutliers.class.getSimpleName(), TC_02_NoProblemWithOutliers.getInstance());
		problems.put(TC_03_ClearHiccup.class.getSimpleName(), TC_03_ClearHiccup.getInstance());
		problems.put(TC_04_RisingHiccup.class.getSimpleName(), TC_04_RisingHiccup.getInstance());
		problems.put(TC_05_BlurredHiccup.class.getSimpleName(), TC_05_BlurredHiccup.getInstance());
		problems.put(TC_07_BlurredRamp.class.getSimpleName(), TC_07_BlurredRamp.getInstance());
		problems.put(TC_08_ConstantDelay.class.getSimpleName(), TC_08_ConstantDelay.getInstance());
		problems.put(TC_09_BlurredDelay.class.getSimpleName(), TC_09_BlurredDelay.getInstance());
		problems.put(TC_10_CPUIntensiveApp.class.getSimpleName(), TC_10_CPUIntensiveApp.getInstance());
		problems.put(TC_11_ManyDiffDBCalls.class.getSimpleName(), TC_11_ManyDiffDBCalls.getInstance());
		problems.put(TC_12_ManyEqualDBCalls.class.getSimpleName(), TC_12_ManyEqualDBCalls.getInstance());
		problems.put(TC_13_ManySimilarDBCalls.class.getSimpleName(), TC_13_ManySimilarDBCalls.getInstance());
		problems.put(TC_14_CPUIntenDBCalls.class.getSimpleName(), TC_14_CPUIntenDBCalls.getInstance());
		problems.put(TC_15_LockingDBCalls.class.getSimpleName(), TC_15_LockingDBCalls.getInstance());
		problems.put(TC_21_ClearSync.class.getSimpleName(), TC_21_ClearSync.getInstance());
	}

	/**
	 * Tests the given problem.
	 * 
	 * @param problemName
	 *            the name of the problem to test
	 * @return hello string
	 */
	@GET
	@Path("testProblem" + "/{problemName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String testProblem(@PathParam("problemName") String problemName) {
		String response;
		if (problems.containsKey(problemName)) {
			problems.get(problemName).test();
			response = "Hello from " + problemName + " Test Method!";
		} else {
			response = "Problem does not exist!";
			LOGGER.debug(response);
		}
		return response;
	}

}
