package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.PurchaseOrderDto;
import org.llin.demo.northwind.model.entity.PurchaseOrder;
import org.mapstruct.Mapper;

@Mapper(		config = _CentralConfig.class,
	    componentModel = "spring",
			    uses = {
			        SupplierMapper.class,
			        EmployeeMapper.class,      // for approvedBy / createdBy / submittedBy
			        OrderStatusMapper.class    // for orderStatus
			    }
	)
	public interface PurchaseOrderMapper {
	    PurchaseOrderDto toDto(PurchaseOrder purchaseOrder);
	    List<PurchaseOrderDto> toDtoList(List<PurchaseOrder> list);
	    PurchaseOrder toEntity(PurchaseOrderDto dto);
	}