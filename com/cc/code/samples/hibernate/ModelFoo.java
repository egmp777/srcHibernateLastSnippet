package com.cc.code.samples.hibernate;

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

public class ModelFoo {

	public ModelFoo() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Foo> getFoos(long minId, long maxId){
	

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
			Criteria criteriaCount = sess.createCriteria(Foo.class, "FOO");
			criteria.add(Restrictions.between("id",minId, maxId));
			criteria.addOrder(Order.asc("id"));
			criteriaCount.add(Restrictions.between("id",minId, maxId));
			criteriaCount.addOrder(Order.asc("id"));
			criteriaCount.setProjection(Projections.rowCount());
			Long count= (Long)criteriaCount.uniqueResult();
			while (pageNumber < count.intValue()){
			   criteria.setFirstResult(pageNumber - 1);
			   criteria.setMaxResults(pageSize);
			   fooList.addAll(criteria.list());
			   pageNumber += pageSize;
			}
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
