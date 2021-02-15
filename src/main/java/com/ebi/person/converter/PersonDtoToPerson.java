package com.ebi.person.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ebi.person.data.entity.Person;
import com.ebi.person.dto.PersonDto;

@Component
public class PersonDtoToPerson implements Converter<PersonDto, Person> {

	@Override
	public Person convert(PersonDto pDto) {
		Person p = new Person();
		if(pDto.getId()!= null && pDto.getId()>0) {
			p.setId(pDto.getId());
		}
		p.setFirstName(pDto.getFirstName());
		p.setLastName(pDto.getLastName());
		p.setAge(pDto.getAge());
		p.setFavouriteColor(pDto.getFavouriteColor());
		return p;
	}

}
