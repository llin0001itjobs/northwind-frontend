package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.PaymentTypeDto;
import org.llin.demo.northwind.model.entity.PaymentType;
import org.mapstruct.Mapper;

@Mapper(config = _CentralConfig.class, componentModel = "spring")
public interface PaymentTypeMapper {
	PaymentTypeDto toDto(PaymentType paymentType);
	List<PaymentTypeDto> toDtoList(List<PaymentType> paymentTypes);
	PaymentType toEntity(PaymentTypeDto paymentTypeDto);
}
