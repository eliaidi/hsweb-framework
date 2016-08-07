package org.hsweb.commons.file.callback;

public interface CanExitCallBack {

	default void exit() {
	}

	default boolean isExit() {
		return false;
	}
}
