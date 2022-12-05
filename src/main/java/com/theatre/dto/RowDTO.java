package com.theatre.dto;

import com.theatre.entity.SeatType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RowDTO {

	private String code;
	private String name;
	private int price;
	private SeatType seatType;
	private int totalSeats;
}
