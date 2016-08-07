package org.hsweb.expands.office.excel;


import org.hsweb.expands.office.excel.config.ExcelWriterConfig;

import java.io.OutputStream;

public interface ExcelWriter {

    void write(OutputStream outputStream, ExcelWriterConfig config, ExcelWriterConfig... moreSheet) throws Exception;
}
