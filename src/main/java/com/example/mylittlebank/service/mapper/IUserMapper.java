package com.example.mylittlebank.service.mapper;


import com.example.mylittlebank.controller.dto.UserDto;
import com.example.mylittlebank.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(implementationName = "IUserMapperImpl")
public interface IUserMapper {

    IUserMapper USER_MAPPER = Mappers.getMapper(IUserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);

}
