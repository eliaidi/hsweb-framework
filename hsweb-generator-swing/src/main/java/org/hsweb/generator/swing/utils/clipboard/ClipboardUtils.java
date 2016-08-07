package org.hsweb.generator.swing.utils.clipboard;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Properties;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public class ClipboardUtils {
    /**
     * 获取粘贴板内容，获取内容后，将数据传给回调
     *
     * @param clipboardCallBack
     */
    public static void getClipboard(ClipboardCallBack clipboardCallBack) {
        try {
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();//获取系统剪贴板
            Transferable clipT = clip.getContents(null);
            if (clipT != null) {
                // 检查内容是否是文本类型
                if (clipT.isDataFlavorSupported(DataFlavor.stringFlavor))
                    clipboardCallBack.isText((String) clipT.getTransferData(DataFlavor.stringFlavor));
                if (clipT.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
                    clipboardCallBack.isFileList((java.util.List) clipT.getTransferData(DataFlavor.javaFileListFlavor));
                if (clipT.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    clipboardCallBack.isImage((Image) clipT.getTransferData(DataFlavor.imageFlavor));
                }
            }
        } catch (Exception e) {
            clipboardCallBack.error(e);
        }
    }

    /**
     * 读取剪贴板的内容为文本
     *
     * @return 剪贴板内容
     */
    public static String getClipboardText() {
        final StringBuilder builder = new StringBuilder();
        getClipboard(new ClipboardCallBack() {
            @Override
            public void isText(String text) {
                builder.append(text);
            }
        });
        return builder.toString();
    }

    /**
     * 读取剪贴板的内容为Properties配置<br/>
     * 如果是文本则转为Properties，如果是多个文件，则合并。key如果有重复的将被覆盖
     *
     * @return Properties对象
     */
    public static Properties getClipboardAsProperties() {
        final Properties properties = new Properties();
        getClipboard(new ClipboardCallBack() {
            @Override
            public void isText(String text) {
                Properties temp = new Properties();
                try {
                    temp.load(new StringReader(text));
                    properties.putAll(temp);
                } catch (IOException e) {
                }
            }

            @Override
            public void isFileList(List<File> list) {
                for (File file : list) {
                    if (file.getName().endsWith(".properties")) {
                        Properties temp = new Properties();
                        try {
                            temp.load(new FileInputStream(file));
                            properties.putAll(temp);
                        } catch (IOException e) {
                        }
                    }
                }
            }
        });
        return properties;
    }

    /**
     * 读取剪贴板的内容为一个二维数组模拟的表格数组
     * 获取方式为：按\n切割为行，再通过\t切割为列
     *
     * @return 二维数组
     */
    public static String[][] getClipboardAsTableData() {
        String text = getClipboardText();
        String[] row = text.split("[\n]");
        String[][] table = new String[row.length][];
        for (int i = 0; i < row.length; i++) {
            table[i] = row[i].split("[\t]");
        }
        return table;
    }

}
