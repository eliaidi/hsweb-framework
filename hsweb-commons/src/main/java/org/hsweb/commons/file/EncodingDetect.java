package org.hsweb.commons.file;

import java.io.File;

public class EncodingDetect {
	/**
	 * 自动获取文件的编码
	 *
	 * @param filePath
	 *            文件路径
	 * @return 文件的编码
	 */
	public static String getJavaEncode(String filePath) {
		BytesEncodingDetect s = new BytesEncodingDetect();
		return BytesEncodingDetect.javaname[s.detectEncoding(new File(filePath))];
	}
}
