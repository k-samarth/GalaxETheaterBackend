package com.theatre.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.theatre.entity.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Integer>{

//		getting list of theaters through city
	@Query("From Theater t inner join t.address a  where a.city like :city%")
	public List<Theater> findByCity(@Param("city") String city);
		
//	getting list of theaters through theater name
	@Query("From Theater t where t.name like :name%")
	public  Theater findByName(String name);
	
	
	
//	getting list of theaters through address.
	@Query("From Theater t inner join t.address  a where a.city like :input% or a.state like :input% or a.pincode like :input% or a.addressLine1 like :input% or a.addressLine2 like :input% or a.country like :input%")
    public List<Theater> findByAddress(@Param("input")String input);
	
}

	
 

