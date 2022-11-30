package com.theatre.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.theatre.dto.RowDTO;
import com.theatre.entity.Row;

@Mapper
public interface RowDTOMapper {

	@Mapping(source="code",target="row.code")
	@Mapping(source="name",target="row.name")
	@Mapping(source="price",target="row.price")
	@Mapping(source="seatType",target="row.seatType")
	public RowDTO convertToDto(Row row);
	
	@Mapping(source="code",target="code")
	@Mapping(source="name",target="name")
	@Mapping(source="price",target="price")
	@Mapping(source="seatType",target="seatType")
	public Row convertToEntity(RowDTO rowDto);
}
