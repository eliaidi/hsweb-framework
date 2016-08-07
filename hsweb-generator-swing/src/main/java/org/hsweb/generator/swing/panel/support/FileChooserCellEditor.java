package org.hsweb.generator.swing.panel.support;

import org.hsweb.commons.StringUtils;
import org.hsweb.generator.swing.SwingGeneratorApplication;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * 文件选择单元格
 * Created by 浩 on 2016-03-18 0018.
 */
public class FileChooserCellEditor extends DefaultCellEditor {
    private FileFilter fileFilter = new FileFilter() {
        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) return true;
            return false;
        }

        @Override
        public String getDescription() {
            return "选择文件";
        }
    };

    private boolean fileOnly = false;

    public FileChooserCellEditor(final JTextField jTextField) {
        super(jTextField);
        jTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (!StringUtils.isNullOrEmpty(jTextField.getText()))
                    chooser.setCurrentDirectory(new File(jTextField.getText()).getParentFile());
                chooser.setFileSelectionMode(isFileOnly() ? JFileChooser.FILES_ONLY : JFileChooser.DIRECTORIES_ONLY);
                chooser.setFileFilter(getFileFilter());
                chooser.setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                if (f == null) return;
                if (isFileOnly() && !f.isFile()) return;
                jTextField.setText(f.getAbsolutePath());
            }
        });

    }

    public FileFilter getFileFilter() {
        return fileFilter;
    }

    public FileChooserCellEditor setFileFilter(FileFilter fileFilter) {
        this.fileFilter = fileFilter;
        return this;
    }

    public boolean isFileOnly() {
        return fileOnly;
    }

    public void setFileOnly(boolean fileOnly) {
        this.fileOnly = fileOnly;
    }
}
