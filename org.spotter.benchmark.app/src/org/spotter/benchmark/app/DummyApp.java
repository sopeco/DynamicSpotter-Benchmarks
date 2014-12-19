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

import org.spotter.benchmark.app.problems.OLBProblem;
import org.spotter.benchmark.app.problems.Problem;
import org.spotter.benchmark.app.problems.RampProblem;

/**
 * Dummy Application.
 * 
 * @author Denis Knoepfle
 * 
 */
@Path("benchmark")
public class DummyApp {

	private final Map<String, Problem> problems = new HashMap<>();

	public DummyApp() {
		populateProblems();
	}

	private void populateProblems() {
		problems.put(RampProblem.NAME, RampProblem.getInstance());
		problems.put(OLBProblem.NAME, OLBProblem.getInstance());
	}

	/**
	 * 
	 * @return hello string
	 */
	@GET
	@Path("testProblem" + "/{problemName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String testProblem(@PathParam("problemName") String problemName) {
		if (problems.containsKey(problemName)) {
			problems.get(problemName).test();
			return "Hello from " + problemName + " Test Method!";
		} else {
			return "Problem does not exist!";
		}
	}

}
