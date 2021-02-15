package com.ebi.person.model;

import java.util.Date;

import com.ebi.person.dto.PersonDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDetails {
	private Date timestamp;
    private String message;
    private String details;
    private int code;
}
