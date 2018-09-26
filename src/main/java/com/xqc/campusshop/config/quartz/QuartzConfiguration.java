package com.xqc.campusshop.config.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.xqc.campusshop.service.ProductSellDailyService;

@Configuration
public class QuartzConfiguration {
	
	@Autowired
	private ProductSellDailyService productSellDailyService;
	
	@Autowired
	private MethodInvokingJobDetailFactoryBean jobDetailFactory;
	
	@Autowired
	private CronTriggerFactoryBean productSellDailyTriggerFactory;
	
	/**
	 * 创建jobDetail并返回
	 * @return
	 */
	@Bean(name="jobDetailFactory")
	public MethodInvokingJobDetailFactoryBean crateJobDetail(){
	
		//new出jobDetailFactory对象，此工厂主要用来制作一个jobDetail,及制作一个任务
		//由于我们所做的定时任务根本上讲其实就是执行一个方法，所以这个工厂比较方便
		MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
		//设置jobDetail的名字
		jobDetailFactoryBean.setName("product_sell_daily_job");
		//设置jobDetail的组名
		jobDetailFactoryBean.setGroup("job_product_sell_daily_group");
		//对于相同的JobDetail，当指定多个Triggger时，很可能第一个job完成以前，第二个job就开始了
		//指定设为false，多个job则不会并发运行，第二个job不会再第一个job完成前开始
		jobDetailFactoryBean.setConcurrent(false);
		//指定运行任务的类
		jobDetailFactoryBean.setTargetObject(productSellDailyService);
		//指定运行任务的方法
		jobDetailFactoryBean.setTargetMethod("dailyCalculate");
		
		return jobDetailFactoryBean;	
	}
	
	/**
	 * 创建cronTriggerFactory并返回
	 * 
	 * @return
	 */
	@Bean("productSellDailyTriggerFactory")
	public CronTriggerFactoryBean createProductSellDailyTrigger(){
		//创建TriggerFactory实例，用来创建trigger
		CronTriggerFactoryBean triggerFactory = new CronTriggerFactoryBean();
		//设置triggerFactory的名字
		triggerFactory.setName("product_sell_daily_trigger");
		//设置组名
		triggerFactory.setGroup("job_product_sell_daily_group");
		//绑定jobDetail
		triggerFactory.setJobDetail(jobDetailFactory.getObject());
		//设置cron表达式
		triggerFactory.setCronExpression("? 0 0 * * ? *");
		
		return triggerFactory;
		
	}
	/**
	 * 创建调度工厂并返回
	 * @return
	 */
	@Bean("schedulerFactory")
	public SchedulerFactoryBean createSchedulerFactory(){
		
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setTriggers(productSellDailyTriggerFactory.getObject());
		return schedulerFactory;
		
	}
	

}
