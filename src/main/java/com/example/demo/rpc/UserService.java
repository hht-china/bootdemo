package com.example.demo.rpc;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.example.demo.dto.UserDTO;
import com.github.pagehelper.PageInfo;

/**
 * @author hongtao.hao
 * @date 2019/6/27
 */
public interface UserService {
    List<UserDTO> listUser();

    PageInfo listPageUser(Integer start, Integer offset);

    void saveUser(UserDTO userDTO);


    UserDTO getUser(@NotBlank String userName);
}
