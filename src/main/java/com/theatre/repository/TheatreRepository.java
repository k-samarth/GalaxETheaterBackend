package com.theatre.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.theatre.entity.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre, Integer>{
	//@Query("From Theatre t inner join Address a on t.address=a.id  inner join t.row where a.city=:city")
	@Query("From Theatre t inner join t.address  a inner join t.row where a.city=:city")
	public List<Theatre> findByCity(String city);
	
	@Query("From Theatre t inner join t.address  a inner join t.row where t.name=:name")
	public List<Theatre> findByName(String name);
 
}
