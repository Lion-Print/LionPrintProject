package com.example.lionprintfirstproject.mapper;

import com.example.lionprintfirstproject.controller.vm.UserVm;
import com.example.lionprintfirstproject.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    UserVm asUserVm(User user);

    List<UserVm> asUserList(List<User> users);

}
