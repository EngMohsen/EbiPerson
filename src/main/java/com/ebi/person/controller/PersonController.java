package com.ebi.person.controller;

import java.net.URI;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ebi.person.data.entity.Person;
import com.ebi.person.dto.PersonDto;
import com.ebi.person.exception.ResourceNotFoundException;
import com.ebi.person.service.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api("This is a person api")
@RestController
@RequestMapping("/")
@Slf4j
public class PersonController {

	@Autowired
	private PersonService personService;

	/*
	 * getPerson for retrieving persons for database
	 */
	@ApiOperation(value = "Retrieve persons from database ")
	@GetMapping(name = "/persons", produces = { "application/json" }, path = "/persons")
	public List<Person> getPerson() {
		return personService.retrievePersons();
	}

	/*
	 * savePerson for save or update persons in database
	 */
	@ApiOperation(value = "save new person in the database")
	@PostMapping(path = "/persons", consumes = { "application/json" })
	public ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto person) {
		Person p = personService.saveOrUpdatePerson(person);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getId()).toUri();
		person.setLinks(new String[] { uri.toString() });
		person.setId(p.getId());
		return new ResponseEntity(person, HttpStatus.OK);
	}

	/*
	 * updatePerson for update persons in database
	 */
	@ApiOperation(value = "update persons in database")
	@PutMapping(path = "/persons/{id}", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody PersonDto person) {
		if (id != null && id > 0) {
			person.setId(id);
		}
		Person p = personService.saveOrUpdatePerson(person);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getId()).toUri();
		person.setLinks(new String[] { uri.toString() });
		return new ResponseEntity(person, HttpStatus.OK);
	}

	/*
	 * getPerson for retrieving person from database
	 */
	@ApiOperation(value = "Retrieve person by Id")
	@GetMapping(path = "/persons/{id}", produces = { "application/json" })
	public PersonDto getPerson(@PathVariable Long id) throws ResourceNotFoundException {
		PersonDto p = personService.getPersonById(id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		String deleteUrl = uri.toString()+"/delete";
		p.setLinks(new String[] { deleteUrl });
		return p;
	}
	
	/*
	 * deletePerson for deleting person from database
	 */
	@ApiOperation(value = "Delete person by Id")
	@DeleteMapping(path = "/persons/{id}/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePerson(@PathVariable Long id) {
		try {
			personService.deletePerson(id);
		} catch (ResourceNotFoundException e) {
			log.error("Person not found for id {} , error {}", id, e);
		}
	}

}
