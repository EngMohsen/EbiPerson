package com.ebi.person.dto;


import com.ebi.person.data.entity.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonDto {
	
	private Long id;
	@ApiModelProperty(value = "person firstname",allowEmptyValue = false,required = true)
	private String firstName;
	@ApiModelProperty(value = "person lirstname",allowEmptyValue = false,required = true)
	private String lastName;
	private String age;
	private String favouriteColor;
	private String[] links;
	
}
