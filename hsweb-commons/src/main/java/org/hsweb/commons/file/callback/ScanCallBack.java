package org.hsweb.commons.file.callback;

import java.io.File;

public interface ScanCallBack extends CanExitCallBack {

	void accept(int deep, File file);

	default void error(int deep, File file, Throwable e) {
		e.printStackTrace();
	}
}
