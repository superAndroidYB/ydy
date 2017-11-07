package com.ydy.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ydy.user.dao.UserJpaDao;
import com.ydy.user.model.User;

@Service
public class YdyTaskImpl implements IYdyTaskService {

	@Autowired
	UserJpaDao userDao;

	@Scheduled(cron = "0 0 0 * * ? ") // 第天凌晨12点生成推荐码
	//@Scheduled(cron = "0/10 * * * * ? ")
	@Override
	public void generateRecomCode() {
		System.out.println("=================开始生成推荐码============================");
		List<User> allUser = userDao.findAll();
		List<String> list = new ArrayList<String>();
		
		if(CollectionUtils.isEmpty(allUser)){
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

	@Scheduled(cron = "0 0 0 1 * ? ") // 第个月最后一天23:55执行
	@Override
	public void generateDividendData() {

	}
}
