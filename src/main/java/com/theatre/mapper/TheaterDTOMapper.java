package com.theatre.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.theatre.dto.TheaterDTO;
import com.theatre.entity.Theater;

@Mapper
public interface TheaterDTOMapper {
	@Mapping(target = "code", source = "theater.code")
	@Mapping(target = "name", source = "theater.name")
	@Mapping(target = "imgUrl", source = "theater.imgUrl")
	@Mapping(target = "address", source = "theater.address")
	public TheaterDTO convertToDto(Theater theater);
	
	@Mapping(target = "code", source = "code")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "imgUrl", source = "imgUrl")
	@Mapping(target = "address", source = "address")
	public Theater convertToEntity(TheaterDTO theater);
}
