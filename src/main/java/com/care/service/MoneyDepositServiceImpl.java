package com.care.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.care.trans_dao.transDAO;
import com.care.trans_dto.transDTO;

public class MoneyDepositServiceImpl implements MoneyService {
	private transDAO dao;
	public MoneyDepositServiceImpl() {
		String config="classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx=
				new GenericXmlApplicationContext(config);
		dao=ctx.getBean("dao",transDAO.class);
	}
	
	@Override
	public void execute(Model model) {
	Map<String ,Object> map = model.asMap();
	transDTO dto = (transDTO)map.get("money");
	dao.deposit(dto.getMoney());	
		
	}

}
