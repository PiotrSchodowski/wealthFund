package com.example.wealthFund.mapper;

import com.example.wealthFund.dto.UserDto;
import com.example.wealthFund.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

   UserDto userToUserDto(User user);
   User userDtoToUser(UserDto userDto);

   List<UserDto> userListToUserDtoList(List<User> userList);
   List<User> userDtoListToUserList(List<UserDto> userDtoList);
}

