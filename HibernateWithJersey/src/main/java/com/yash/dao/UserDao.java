package com.yash.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDao {

	public void addUser(User user){
		Session session = SessionUtil.getSession();        
		Transaction tx = session.beginTransaction();
		addUser(session,user); 
		System.out.println("Create");
		tx.commit();
		session.close();

	}

	private void addUser(Session session, User user){
		User usr = new User();

		usr.setName(user.getName());
		usr.setAge(user.getAge());

		session.save(usr);
	}

	public List<User> getUser(){
		Session session = SessionUtil.getSession();    
		Query query = session.createQuery("from User");
		List<User> users =  query.list();
		session.close();
		return users;
	}

	public int deleteUser(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete from User where id = :id");
		query.setInteger("id",id);
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}

	public int updateUser(int id, User user){
		if(id <=0)  
			return 0;  
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "update User set name = :name, age=:age where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id",id);
		query.setString("name",user.getName());
		query.setInteger("age",user.getAge());
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}
}
