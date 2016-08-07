package org.hsweb.commons.file.callback;

public abstract class AbstractScanCallBack implements ScanCallBack {
	private boolean exit = false;

	@Override
	public void exit() {
		exit = true;
	}

	@Override
	public boolean isExit() {
		return exit;
	}
}
