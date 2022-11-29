package com.theatre.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Table(name="TheatreRow")
@Data
@Component
public class Row {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	private String name;
	private int price;
	private SeatType seatType;
	private int totalSeats;
	
//	one to many relationship with seat
	@OneToMany(targetEntity = Seat.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "rowid", referencedColumnName = "id")
    private List<Seat> seats;
	
}
