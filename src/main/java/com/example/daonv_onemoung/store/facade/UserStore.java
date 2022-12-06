package com.example.daonv_onemoung.store.facade;

import com.example.daonv_onemoung.common.UserDTO;

public interface UserStore {
    public UserDTO findById(Long id);
    public UserDTO save(UserDTO userDTO);
}
