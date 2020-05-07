package com.care.trans_dao;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.care.trans_dto.transDTO;

public class transDAO {
	private JdbcTemplate template;
	private TransactionTemplate transactionTemplate;
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public void deposit(int money) {
		String my_sql="update myaccount set money=money+? where num=1";
		String sys_sql="update sysaccount set money=money+? where num=1";
		
		template.update(my_sql,ps->{
			ps.setInt(1, money);
		});
		template.update(sys_sql,ps->{
			ps.setInt(1, money);
		});	
		
	}
	public transDTO balance() {
		String my_sql="select money from myaccount where num=1";
		return template.queryForObject(my_sql,new BeanPropertyRowMapper<transDTO>(transDTO.class));
		
	}
	public void sending(int mon) {
		String my_sql="update myaccount set money=money-? where num=1";
		String sys_sql="update sysaccount set money=money-? where num=1";
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					// TODO Auto-generated method stub
					template.update(my_sql,ps->{
						ps.setInt(1, mon);
					});
					template.update(sys_sql,ps->{
						ps.setInt(1, mon);
					});	
					
					
				}
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	

}
