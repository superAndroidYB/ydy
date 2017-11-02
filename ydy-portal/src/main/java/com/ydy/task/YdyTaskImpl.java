package com.ydy.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class YdyTaskImpl implements IYdyTaskService {

	@Scheduled(cron="0 0 0 * * ? ")   //第天凌晨12点生成推荐码
	@Override
	public void generateRecomCode() {
		
	}

	@Scheduled(cron="0 55 23 L * ?")   //第个月最后一天23:55执行
	@Override
	public void generateDividendData() {
		
	}
}
