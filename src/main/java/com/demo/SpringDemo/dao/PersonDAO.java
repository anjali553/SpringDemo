package com.demo.SpringDemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.demo.SpringDemo.entity.Person;



@Transactional
@Repository
public class PersonDAO implements IPersonDAO {
	
	@Autowired
	private HibernateTemplate  hibernateTemplate;
	
	public Person getPersonById(int pid) {
		return hibernateTemplate.get(Person.class, pid);
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getAllPersons() {
		String hql = "FROM Person as p ORDER BY p.pid";
		return (List<Person>) hibernateTemplate.find(hql);
	}	
	
	public void addPerson(Person person) {
		hibernateTemplate.save(person);
	}
	
	public void updatePerson(Person person) {
		Person p = getPersonById(person.getPid());
		p.setName(person.getName());
		p.setLocation(person.getLocation());
		hibernateTemplate.update(p);
	}
	
	public void deletePerson(int pid) {
		hibernateTemplate.delete(getPersonById(pid));
	}
	
	@SuppressWarnings("unchecked")
	public boolean personExists(String name, String location) {
		String hql = "FROM Person as p WHERE p.name = ? and p.location = ?";
		List<Person> persons = (List<Person>) hibernateTemplate.find(hql, name, location);
		return persons.size() > 0 ? true : false;
	}
}