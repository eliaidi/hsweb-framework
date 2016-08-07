package org.hsweb.web.service.form;

import org.hsweb.ezorm.meta.TableMetaData;
import org.hsweb.web.bean.po.form.Form;

import javax.xml.bind.Marshaller;

public interface FormParser {
    TableMetaData parse(Form form);

    String parseHtml(Form form);

    interface Listener {
        void afterParse(TableMetaData tableMetaData);
    }
}
