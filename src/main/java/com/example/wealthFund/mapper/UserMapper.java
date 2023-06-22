package com.example.wealthFund.mapper;

import com.example.wealthFund.dto.UserDto;
import com.example.wealthFund.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
   UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

   @Mapping(target = "name", source = "name")
   UserDto userToUserDto(User user);

   @Mapping(target = "name", source = "name")
   User userDtoToUser(UserDto userDto);

   List<UserDto> userListToUserDtoList(List<User> userList);

   List<User> userDtoListToUserList(List<UserDto> userDtoList);
}

