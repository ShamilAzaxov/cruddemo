package com.luv2code.springboot.dao;

import com.luv2code.springboot.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO{

    //define field for EntityManager
    @PersistenceUnit
    private EntityManager entityManager;

    //set up setter injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        //create a query
        Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);

        //execute the query anf get result list
        List<Employee> employees = theQuery.getResultList();

        //return the list
        return employees;
    }
}
