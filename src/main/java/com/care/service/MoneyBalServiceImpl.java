package com.care.service;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.care.trans_dao.transDAO;

public class MoneyBalServiceImpl implements MoneyService {
	private transDAO dao;
	public MoneyBalServiceImpl() {
		String config="classpath:applicationJDBC.xml";
		ApplicationContext ctx=
				new GenericXmlApplicationContext(config);
		dao=ctx.getBean("dao",transDAO.class);
	}
	@Override
	public void execute(Model model) {
		
		model.addAttribute("mon",dao.balance());
	}

}
