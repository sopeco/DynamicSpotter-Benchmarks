package org.spotter.benchmark.app.loadscripts;

import java.util.Random;

import javax.ws.rs.core.MediaType;

import org.lpe.common.util.web.LpeWebUtils;
import org.spotter.benchmark.app.BenchmarkAppLauncher;
import org.spotter.ext.workload.simple.ISimpleVUser;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public abstract class AbstractVUser implements ISimpleVUser {

	private static final int THINK_TIME_MIN = 100;
	private static final int THINK_TIME_MAX = 200;
	private static final int TIMEOUT = 120 * 1000;
	private static final Random random = new Random(System.nanoTime());
	private final WebResource webResource;

	public AbstractVUser() {
		Client client = LpeWebUtils.getWebClient();
		client.setConnectTimeout(TIMEOUT);
		client.setReadTimeout(TIMEOUT);
		int port = BenchmarkAppLauncher.DEFAULT_PORT;
		this.webResource = client.resource("http://localhost:" + port + "/");
	}

	protected String testProblem(String problemName) {
		return getWebResource().path("benchmark").path("testProblem").path(problemName)
				.accept(MediaType.APPLICATION_JSON).get(String.class);
	}

	protected WebResource getWebResource() {
		return webResource;
	}

	protected void doExecuteIteration(String problemName) {
		try {
			testProblem(problemName);
			Thread.sleep(getNextThinkTime());
		} catch (Throwable e) {
			// ignoring exception
			e.printStackTrace();
		}
	}

	protected long getNextThinkTime() {
		int r = random.nextInt(THINK_TIME_MAX - THINK_TIME_MIN);
		return THINK_TIME_MIN + r;
	}

}
