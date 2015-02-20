package org.spotter.benchmark.app.loadscripts;

import org.spotter.benchmark.app.problems.TC_16_JMSFileTransfer;

public class VU_16_JMSFileTransfer  extends AbstractVUser {

	@Override
	public void executeIteration() {
		doExecuteIteration(TC_16_JMSFileTransfer.class.getSimpleName());
	}
}
