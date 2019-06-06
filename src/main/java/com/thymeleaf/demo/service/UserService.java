package com.thymeleaf.demo.service;

import com.thymeleaf.demo.dao.UserDao;
import com.thymeleaf.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User login(User user) {
		User u = userDao.findByNameAndPasswd(user.getName(), user.getPasswd());
		return u;
	}
}
