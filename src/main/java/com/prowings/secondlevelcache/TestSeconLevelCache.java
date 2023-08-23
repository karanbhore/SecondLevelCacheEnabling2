package com.prowings.secondlevelcache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.prowings.entity.Employee;

public class TestSeconLevelCache {

	public static void main(String[] args) {

		Employee emp1 = new Employee("Ram", "Pune", "HR");
		Employee emp2 = new Employee("Sham", "Mumbai", "IT");

		Configuration conf = new Configuration();
		conf.configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session1 = sf.openSession();
		Transaction txn1 = session1.beginTransaction();

		Employee e1 = session1.get(Employee.class, 3);
		System.out.println("emp1 from session 1 first time : " + e1);

		Employee e2 = session1.get(Employee.class, 3);
		System.out.println("emp1 from session 1 second time : " + e2);

//		session1.save(emp1);
//		session1.save(emp2);
		txn1.commit();
		session1.close();

		System.out.println(">>>>>>>>>>>>starting second session<<<<<<<<<<<<<");

		Session session2 = sf.openSession();
		Transaction txn2 = session2.beginTransaction();

		Employee se1 = session2.get(Employee.class, 3);
		System.out.println("emp1 from session 2 first time : " + se1);

		txn2.commit();
		session2.close();

	}
}