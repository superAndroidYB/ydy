package com.ydy.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ydy.order.services.IDividendService;
import com.ydy.user.dao.UserJpaDao;
import com.ydy.user.model.User;

@Service
public class YdyTaskImpl implements IYdyTaskService {

	@Autowired
	UserJpaDao userDao;

	@Resource(name = IDividendService.BEAN_ID)
	IDividendService dividendService;

	@Scheduled(cron = "0 0 0 * * ? ") // 第天凌晨12点生成推荐码
	// @Scheduled(cron = "1 * * * * ? ")
	@Override
	public void generateRecomCode() {
		System.out.println("=================开始生成推荐码============================");
		List<User> allUser = userDao.findAll();
		List<String> list = new ArrayList<String>();

		if (CollectionUtils.isEmpty(allUser)) {
			return;
		}
		Random random = new Random();
		while (list.size() < allUser.size()) {
			String s = Integer.toString((int) random.nextInt(1000000));
			if (!list.contains(s)) {
				list.add(s);
			}
		}

		for (int i = 0; i < list.size(); i++) {
			allUser.get(i).setRecomCode(list.get(i));
		}

		userDao.save(allUser);

		System.out.println("=================生成推荐码成功============================");
	}

	@Scheduled(cron = "0 0 0 1 * ? ") // 每个月第一天生成上个月的分红数据
	//@Scheduled(cron = "1 * * * * ? ")
	@Override
	public void generateDividendData() {
		System.out.println("=================开始生成分红数据============================");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); 
		String month = DateFormatUtils.format(calendar.getTime(), "yyyy-MM");
		dividendService.calcMonthSaleAmt(month);
		dividendService.calcMonthSaleRelaAmt(month);
		System.out.println("=================开始生成分红成功============================");
	}
}
