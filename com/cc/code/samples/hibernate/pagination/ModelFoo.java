package com.cc.code.samples.hibernate.pagination;

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

import com.cc.example.hibernate.Foo;

public class ModelFoo {

	public ModelFoo() {
	    super();
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
			Criteria criteriaCount = sess.createCriteria(Foo.class, "FOO");
			criteriaCount.add(Restrictions.between("id",minId, maxId));
			criteriaCount.setProjection(Projections.rowCount());
			Long count= (Long)criteriaCount.uniqueResult();
			Criteria criteria = sess.createCriteria(Foo.class, "FOO");
			criteria.add(Restrictions.between("id",minId, maxId));
			criteria.addOrder(Order.asc("id"));
			int totalEntities = 0;
			while (totalEntities < count.intValue()){
			   criteria.setFirstResult((pageNumber - 1)*pageSize);
			   criteria.setMaxResults(pageSize);
			   fooList.addAll(criteria.list());
			   totalEntities = fooList.size();
			   pageNumber++;
			   
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
