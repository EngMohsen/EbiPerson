package com.ebi.person.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebi.person.converter.PersonDtoToPerson;
import com.ebi.person.converter.PersonToPersonDto;
import com.ebi.person.data.entity.Person;
import com.ebi.person.data.repository.PersonRepository;
import com.ebi.person.dto.PersonDto;
import com.ebi.person.exception.ResourceNotFoundException;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonDtoToPerson personDtoToPerson;
	
	@Autowired
	private PersonToPersonDto personToPersonDto;

	public List<Person> retrievePersons() {
		return personRepository.findAll();
	}

	public PersonDto getPersonById(Long id) throws ResourceNotFoundException {
		Person p = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person not fround for id ::" + id));
		PersonDto pDto = new PersonDto();
		pDto = personToPersonDto.convert(p);
		return pDto;

	}

	public Person saveOrUpdatePerson(PersonDto p) {
		Person per = personDtoToPerson.convert(p);
		return personRepository.save(per);
	}

	public List<Person> getPersonByFirstName(String firstName) {
		return personRepository.findByFirstName(firstName);
	}

	public void deletePerson(String firstName) {
		Person p = personRepository.findByFirstName(firstName).stream().findFirst().get();
		personRepository.delete(p);
	}

	public void deletePerson(long id) throws ResourceNotFoundException {
		Person p = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person not fround for id ::" + id));
		;
		personRepository.delete(p);
	}

}
