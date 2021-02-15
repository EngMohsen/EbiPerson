package com.ebi.person.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ebi.person.data.entity.Person;
import com.ebi.person.dto.PersonDto;

@Component
public class PersonToPersonDto implements Converter<Person, PersonDto>{

	@Override
	public PersonDto convert(Person p) {
		PersonDto pDto = new PersonDto();
		if(p.getId()!= null && p.getId()>0) {
			pDto.setId(p.getId());
		}
		pDto.setFirstName(p.getFirstName());
		pDto.setLastName(p.getLastName());
		pDto.setAge(p.getAge());
		pDto.setFavouriteColor(p.getFavouriteColor());
		return pDto;
	}

}
