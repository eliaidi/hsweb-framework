package org.hsweb.web.core.logger;

import org.hsweb.web.bean.po.logger.LoggerInfo;

public interface AccessLoggerPersisting {

    void save(LoggerInfo loggerInfo);
}
