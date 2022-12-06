package com.theatre.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.theatre.dto.TheaterDTO;
import com.theatre.entity.Theater;

@Mapper
public interface TheaterListDTOMapper {

		@Mapping(target = "code", source = "code")
		@Mapping(target = "name", source = "name")
		@Mapping(target = "imgUrl", source = "imgUrl")
		@Mapping(target = "address", source = "address")
		public List<TheaterDTO> convertToDto(List<Theater> theater);
		
		@Mapping(target = "code", source = "code")
		@Mapping(target = "name", source = "name")
		@Mapping(target = "imgUrl", source = "imgUrl")
		@Mapping(target = "address", source = "address")
		public List<Theater> convertToEntity(List<TheaterDTO> theaterDto);
	}

