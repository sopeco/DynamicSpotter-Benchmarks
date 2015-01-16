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

import java.util.ArrayList;
import java.util.List;

import org.lpe.common.util.web.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A benchmark App for DynamicSpotter.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class BenchmarkAppLauncher {

	public static final int DEFAULT_PORT = 8081;

	private static final int MIN_NUM_WORKER_THREADS = 10;
	private static final int MAX_NUM_WORKER_THREADS = 500;

	private static final Logger LOGGER = LoggerFactory.getLogger(BenchmarkAppLauncher.class);

	/**
	 * Private constructor due to singleton class.
	 */
	private BenchmarkAppLauncher() {

	}

	/**
	 * Opens up a server on the localhost IP address and the default port 8081
	 * of the underlying system.
	 * 
	 * @param args
	 *            should contain at least one parameter indicating whether to
	 *            start or stop
	 */
	public static void main(String[] args) {

		if (args != null) {

			if (args.length < 1) {
				printHelpAndExit();
			} else {
				if (args[0].equalsIgnoreCase("start")) {
					List<String> servicePackages = new ArrayList<>();
					servicePackages.add("org.spotter.benchmark.app");
					WebServer.getInstance().start(DEFAULT_PORT, "", servicePackages, MIN_NUM_WORKER_THREADS,
							MAX_NUM_WORKER_THREADS);

				} else if (args[0].equalsIgnoreCase("shutdown")) {
					WebServer.triggerServerShutdown(DEFAULT_PORT, "");
				} else {
					LOGGER.error("Invalid value for 1st argument! Valid values are: start / shutdown");
				}

			}

		} else {
			printHelpAndExit();
		}

	}

	private static void printHelpAndExit() {
		LOGGER.info("Benchmark App Launcher requires at least one argument:");
		LOGGER.info("Usage: java -jar <BENCHMARK_JAR> {start | shutdown}");
		System.exit(0);
	}

}
