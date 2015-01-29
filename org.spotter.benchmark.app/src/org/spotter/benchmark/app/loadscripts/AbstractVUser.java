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
package org.spotter.benchmark.app.loadscripts;

import java.util.Random;

import javax.ws.rs.core.MediaType;

import org.lpe.common.util.web.LpeWebUtils;
import org.spotter.benchmark.app.BenchmarkAppLauncher;
import org.spotter.ext.workload.simple.ISimpleVUser;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * Abstract class for a simple VUser script.
 * 
 * @author Denis Knoepfle
 * 
 */
public abstract class AbstractVUser implements ISimpleVUser {

	private static final int THINK_TIME_MIN = 100;
	private static final int THINK_TIME_MAX = 200;
	private static final int TIMEOUT = 120 * 1000;
	private static final Random random = new Random(System.nanoTime());
	private final WebResource webResource;

	/**
	 * The constructor.
	 */
	public AbstractVUser() {
		Client client = LpeWebUtils.getWebClient();
		client.setConnectTimeout(TIMEOUT);
		client.setReadTimeout(TIMEOUT);
		int port = BenchmarkAppLauncher.DEFAULT_PORT;
		this.webResource = client.resource("http://localhost:" + port + "/");
	}

	/**
	 * Tests the given problem.
	 * 
	 * @param problemName
	 *            the name of the problem
	 * @return the answer
	 */
	protected String testProblem(String problemName) {
		return getWebResource().path("benchmark").path("testProblem").path(problemName)
				.accept(MediaType.APPLICATION_JSON).get(String.class);
	}

	/**
	 * Returns the web resource.
	 * 
	 * @return the web resource
	 */
	protected WebResource getWebResource() {
		return webResource;
	}

	/**
	 * Executes an iteration for the given problem.
	 * 
	 * @param problemName
	 *            the name of the problem
	 */
	protected void doExecuteIteration(String problemName) {
		try {
			testProblem(problemName);
			Thread.sleep(getNextThinkTime());
		} catch (Throwable e) {
			// ignoring exception
			e.printStackTrace();
		}
	}

	/**
	 * Returns the calculated next think time.
	 * 
	 * @return the next think time
	 */
	protected long getNextThinkTime() {
		int r = random.nextInt(THINK_TIME_MAX - THINK_TIME_MIN);
		return THINK_TIME_MIN + r;
	}

}
