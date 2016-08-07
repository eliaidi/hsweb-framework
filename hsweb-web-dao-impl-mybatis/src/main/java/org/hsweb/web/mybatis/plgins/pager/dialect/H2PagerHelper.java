package org.hsweb.web.mybatis.plgins.pager.dialect;

import org.springframework.stereotype.Component;

@Component
public class H2PagerHelper extends MysqlPagerHelper {

    @Override
    public String getDialect() {
        return "h2";
    }
}
