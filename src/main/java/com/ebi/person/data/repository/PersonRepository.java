package com.ebi.person.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ebi.person.data.entity.Person;


@RepositoryRestResource(collectionResourceRel = "profile",path = "profile")
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	List<Person> findByLastName(@Param("name") String name);
	List<Person> findByFirstName(@Param("name") String name);

}
