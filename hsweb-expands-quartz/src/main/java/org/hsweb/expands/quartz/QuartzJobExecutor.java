package org.hsweb.expands.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzJobExecutor {
    SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    Scheduler scheduler;

    public void setup() throws SchedulerException {
        scheduler = schedulerFactory.getScheduler();
    }
}
