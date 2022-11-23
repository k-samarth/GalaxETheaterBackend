package com.theatre.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


import lombok.Data;

@Entity
@Data
@Table(name="theatre")
@Component
public class Theatre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String code;
	private String name;
	private String imgUrl;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "addressid" )
	private Address address;
	
	@OneToMany(targetEntity = Row.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "theatreId", referencedColumnName = "id")
	private List<Row> row;
	

	
}
