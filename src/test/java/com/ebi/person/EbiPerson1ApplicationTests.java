package com.ebi.person;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ebi.person.data.entity.Person;
import com.ebi.person.data.repository.PersonRepository;
import com.ebi.person.dto.PersonDto;
import com.ebi.person.service.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest
class EbiPerson1ApplicationTests {

	@Autowired
	private PersonService personService;

	@MockBean
	private PersonRepository personRepository;

	@Test
	public void getPersonsTest() {
		when(personRepository.findAll())
		.thenReturn(Stream.of(new Person(1L,"ahmed","mohsen","28","red")
				,new Person(2L,"Erick","john","29","yellow")).collect(Collectors.toList()));
		assertEquals(2, personService.retrievePersons().size());
	}
	
	@Test
	public void savePersonsTest() {
		PersonDto pDto = new PersonDto(1L,"ahmed","mohsen","28","red",null);
		Person p = new Person(1L,"ahmed","mohsen","28","red");
		when(personRepository.save(p)).thenReturn(p);
		assertEquals(p, personService.saveOrUpdatePerson(pDto));
	}
	
	@Test
	public void getPersonByFirstNameTest() {
		String firstName="ahmed";
		when(personRepository.findByFirstName(firstName))
		.thenReturn(Stream.of(new Person(1L,"ahmed","mohsen","28","red")).collect(Collectors.toList()));
		assertEquals(1, personService.getPersonByFirstName(firstName).size());
	}
	
}
