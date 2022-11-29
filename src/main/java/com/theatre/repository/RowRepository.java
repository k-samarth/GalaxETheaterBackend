package com.theatre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theatre.entity.Row;
import com.theatre.entity.Theater;

public interface RowRepository extends JpaRepository<Row,Integer> {

//	getting row through row name
	public Row findByName(String name);
}
