package org.hsweb.commons.file.callback;

public interface ReadCallBack extends CanExitCallBack {

	void readLine(int lineNumber, String line);

	default void error(Throwable e) {
		e.printStackTrace();
	}

	default void done(int total) {
	}
}
