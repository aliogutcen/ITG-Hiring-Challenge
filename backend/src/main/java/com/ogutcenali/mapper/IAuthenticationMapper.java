package com.ogutcenali.mapper;

import com.ogutcenali.dto.request.RegisterRequest;
import com.ogutcenali.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthenticationMapper {

    IAuthenticationMapper INSTANCE = Mappers.getMapper(IAuthenticationMapper.class);


    User toUserRegister(final RegisterRequest registerRequest);
}
