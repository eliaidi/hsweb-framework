package org.hsweb.generator.swing.utils.clipboard;

import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public abstract class ClipboardCallBack {
    public void isText(String text) {
    }

    public void isFileList(List<File> list) {
    }

    public void isImage(Image image) {
    }

    public void error(Throwable e) {
        e.printStackTrace();
    }
}
