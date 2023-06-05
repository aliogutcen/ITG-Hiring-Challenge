package com.ogutcenali.mapper;

import com.ogutcenali.dto.request.CreateCreditCardRequest;
import com.ogutcenali.model.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICreditCardMapper {

    ICreditCardMapper INSTANCE  = Mappers.getMapper(ICreditCardMapper.class);

    CreditCard toCreditCard(final CreateCreditCardRequest createCreditCardRequest);
}
