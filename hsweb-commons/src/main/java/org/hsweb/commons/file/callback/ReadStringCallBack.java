package org.hsweb.commons.file.callback;

public class ReadStringCallBack implements ReadCallBack {

	protected StringBuilder builder = new StringBuilder();

	@Override
	public String toString() {
		return builder.toString();
	}

	@Override
	public void readLine(int lineNumber, String line) {
		builder.append(line).append("\n");
	}
}
