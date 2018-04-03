package com.demo.SpringDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.SpringDemo.dao.IPersonDAO;
import com.demo.SpringDemo.entity.Person;



@Service
public class PersonService implements IPersonService {
	@Autowired
	private IPersonDAO personDAO;
	
	public Person getPersonById(int pid) {
		Person obj = personDAO.getPersonById(pid);
		return obj;
	}	
	
	public List<Person> getAllPersons(){
		return personDAO.getAllPersons();
	}
	
	public synchronized boolean addPerson(Person person){
       if (personDAO.personExists(person.getName(), person.getLocation())) {
    	   return false;
       } else {
    	   personDAO.addPerson(person);
    	   return true;
       }
	}
	
	public void updatePerson(Person person) {
		personDAO.updatePerson(person);
	}
	
	public void deletePerson(int pid) {
		personDAO.deletePerson(pid);
	}
}
