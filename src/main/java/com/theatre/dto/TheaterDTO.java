package com.theatre.dto;

import java.util.List;

import com.theatre.entity.Address;
import com.theatre.entity.Row;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/*
 * DTO For Theater Entity
 */
public class TheaterDTO {
	private int id;
	private String code;
	private String name;
	private String imgUrl;
	private Address address;
	private List<Row> row;
}

