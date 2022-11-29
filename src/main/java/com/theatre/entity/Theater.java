package com.theatre.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.Data;

@Entity
@Data
@Table(name="theatre")
@Component
@CrossOrigin("*")
public class Theater {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String code;
	private String name;
	private String imgUrl;
	
//	one to one mapping with address
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "addressid" )
	private Address address;
	
//	one to many mapping with row
	@OneToMany(targetEntity = Row.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "theatreId", referencedColumnName = "id")
	private List<Row> row;
	

	
}
