package org.hsweb.generator.app.register;

import org.hsweb.generator.CodeMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浩 on 2016-03-20 0020.
 */
public class MetaRegister extends AbstractRegister<List<CodeMeta>> {

    @Override
    public List<CodeMeta> getMergedData() {
        List<CodeMeta> tmp = new ArrayList<>();
        for (List<CodeMeta> codeMetas : getDataList()) {
            tmp.addAll(codeMetas);
        }
        return tmp;
    }
}
