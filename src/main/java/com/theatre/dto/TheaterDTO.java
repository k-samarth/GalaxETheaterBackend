package com.theatre.dto;

import com.theatre.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterDTO {
	private String code;
	private String name;
	private String imgUrl;
	private Address address;
}
