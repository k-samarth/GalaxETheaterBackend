package com.theatre.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name="seat")
/*
 * Seat Entity
 */
public class Seat {
        @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private SeatBooking booking;
	    
}
