package com.theatre.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.theatre.dto.AddressDTO;
import com.theatre.entity.Address;


@Mapper
public interface AddressDTOMapper {

	@Mapping(target = "city", source = "city")
	@Mapping(target = "state", source = "state")
	@Mapping(target = "country", source = "country")
	@Mapping(target = "pincode", source = "pincode")
	@Mapping(target = "addressLine1", source = "addressLine1")
	@Mapping(target = "addressLine2", source = "addressLine2")
	public AddressDTO convertToDto(Address address);
	
	@Mapping(target = "city", source = "city")
	@Mapping(target = "state", source = "state")
	@Mapping(target = "country", source = "country")
	@Mapping(target = "pincode", source = "pincode")
	@Mapping(target = "addressLine1", source = "addressLine1")
	@Mapping(target = "addressLine2", source = "addressLine2")
	public Address convertToEntity(AddressDTO addressDto);
	
}
