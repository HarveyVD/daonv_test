package com.example.daonv_onemoung.service.facade;

import com.example.daonv_onemoung.common.UserDTO;

public interface UserService {
    public UserDTO getUser(Long id);

    public UserDTO saveUser(UserDTO userDTO);
}
