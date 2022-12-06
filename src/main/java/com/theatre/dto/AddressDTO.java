package com.theatre.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/*
 * DTO For Address Entity
 */
public class AddressDTO {

	private String city;
	private String state;
	private String country;
	private String pincode;
	private String addressLine1;
	private String addressLine2;
}
