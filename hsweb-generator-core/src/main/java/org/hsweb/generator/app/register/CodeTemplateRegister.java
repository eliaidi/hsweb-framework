package org.hsweb.generator.app.register;

import org.hsweb.generator.CodeTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浩 on 2016-03-20 0020.
 */
public class CodeTemplateRegister extends AbstractRegister<List<CodeTemplate>> {

    @Override
    public List<CodeTemplate> getMergedData() {
        List<CodeTemplate> tmp = new ArrayList<>();
        getDataList().forEach(tmp::addAll);
        return tmp;
    }
}
