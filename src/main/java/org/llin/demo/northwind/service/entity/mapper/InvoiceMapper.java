package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.InvoiceDto;
import org.llin.demo.northwind.model.entity.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
	InvoiceDto toDto(Invoice invoice);
	List<InvoiceDto> toDtoList(List<Invoice> invoice);
	Invoice toEntity(InvoiceDto invoiceDto);
}

