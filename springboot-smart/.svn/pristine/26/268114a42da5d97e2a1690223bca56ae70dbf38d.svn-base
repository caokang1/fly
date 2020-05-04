package com.cignacmb.smart.base.utils.jobs;

import com.cignacmb.smart.base.config.Commons;
import com.cignacmb.smart.base.utils.exception.BusinessException;
import com.cignacmb.smart.entity.base.QuartzjobEntity;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("quartzJobManager")
public class QuartzJobManager {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 增加一个cycle job
     * @param job
     * @return
     * @throws Exception
     */
    public boolean addCycleJob(QuartzjobEntity job) throws Exception{
        if(existsJob(job)) {
            throw new BusinessException("JOB已经存在schedule中");
        }

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(job.getClassname()))
                .withIdentity(job.getJobid(), job.getJobgroup())
                .build();

        jobDetail.getJobDataMap().put(Commons.QUARTZ_JOB_ENTITY, job);

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronexpression());

		/*withMisfireHandlingInstructionDoNothing
        ——不触发立即执行
        ——等待下次Cron触发频率到达时刻开始按照Cron频率依次执行

        withMisfireHandlingInstructionIgnoreMisfires
        ——以错过的第一个频率时间立刻开始执行
        ——重做错过的所有频率周期后
        ——当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行

        withMisfireHandlingInstructionFireAndProceed
        ——以当前时间为触发频率立刻触发一次执行
        ——然后按照Cron频率依次执行*/

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(job.getJobid(), job.getJobgroup())
                .withSchedule(cronScheduleBuilder.withMisfireHandlingInstructionDoNothing())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        return true;
    }

    /**
     * 增加一个执行一次的job
     * @param job
     * @return
     * @throws Exception
     */
    public boolean addOnceJob(QuartzjobEntity job) throws Exception{
        if(existsJob(job)) {
            throw new BusinessException("JOB已经存在schedule中");
        }

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(job.getClassname()))
                .withIdentity(job.getJobid(), job.getJobgroup())
                .build();

        jobDetail.getJobDataMap().put(Commons.QUARTZ_JOB_ENTITY, job);

        SimpleTrigger trigger = TriggerBuilder.newTrigger()

                .withIdentity(job.getJobid(), job.getJobgroup())
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//													.withIntervalInSeconds(2)
                        //重复执行的次数，因为加入任务的时候马上执行了，所以不需要重复，否则会多一次。
                        .withRepeatCount(0))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        return true;
    }

    /**
     * 删除一个job
     * @param job
     * @return
     * @throws Exception
     */
    public boolean removeJob(QuartzjobEntity job) throws Exception{
        if(!existsJob(job)) {
            return true;
        }

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(job.getJobid(), job.getJobgroup());
        scheduler.deleteJob(jobKey);

        return true;
    }

    /**
     * 更新一个job
     * @param job
     * @return
     * @throws Exception
     */
    public boolean updateJob(QuartzjobEntity job) throws Exception{
        if(!removeJob(job)){
            throw new BusinessException("从schedule中移除job失败");
        }

        boolean flag = true;

        if (SmartJobStatus.JOB_GROUP_CYCLE.equalsIgnoreCase(job.getJobgroup())){
            flag = addCycleJob(job);
        }
        else if (SmartJobStatus.JOB_GROUP_ONCE.equalsIgnoreCase(job.getJobgroup())){
            flag = addOnceJob(job);
        }

        return flag;
    }

    /**
     * 删除所有的job
     * @return
     * @throws Exception
     */
    public boolean removeAll() throws Exception{
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        scheduler.clear();

        return true;
    }

    /**
     * 重载所有job
     * @return
     * @throws Exception
     */
    public boolean reloadAll(List<QuartzjobEntity> list) throws Exception{
        removeAll();

        if (list != null && list.size() > 0){
            for (QuartzjobEntity job : list){
                addCycleJob(job);
            }
        }

        return true;
    }

    /**
     * 判断是否存在job
     * @param job
     * @return
     */
    public boolean existsJob(QuartzjobEntity job) {

        boolean flag = false;

        JobKey jobKey = JobKey.jobKey(job.getJobid(), job.getJobgroup());

        try {
            flag = schedulerFactoryBean.getScheduler().checkExists(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return flag;

    }

    /**
     * 返回定时器的状态，TRUE-启动，FALSE-关闭
     * @return
     * @throws Exception
     */
    public boolean getServerStatus() throws Exception{
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        return scheduler.isStarted();
    }
}
