package com.theatre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theatre.entity.Address;


public interface AddressRepository extends JpaRepository<Address, Integer>{

}

