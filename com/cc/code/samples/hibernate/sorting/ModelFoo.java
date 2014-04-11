package com.cc.code.samples.hibernate.sorting;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;

import org.hibernate.Query;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.cc.example.hibernate.*;;

public class ModelFoo {

	public ModelFoo() {
		
	}
	
	public List<Foo> getSortedFoos(){
	

		Session sess = null;
		SessionFactory sf = new Configuration().
				configure().buildSessionFactory();
		sess = sf.openSession();
		int pageNumber = 1;
		int pageSize = 10;
		List<Foo> fooList = new ArrayList();  
		try{
			sess.beginTransaction();
			Criteria criteria = sess.createCriteria(Foo.class, "FOO");
			criteria.addOrder(Order.asc("id"));		
			fooList = criteria.list();
			for(Foo foo: fooList){
				System.out.println("Total Results:" + fooList.size());

				System.out.println(
						"Id: " + foo.getId()
						+ ", FirstName: " + foo.getName()

						);
				
			}

			sess.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(sess != null){
				sess.close();
			}
		}
		return fooList;
	}

}
