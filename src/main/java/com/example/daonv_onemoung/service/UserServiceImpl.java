package com.example.daonv_onemoung.service;

import com.example.daonv_onemoung.common.UserDTO;
import com.example.daonv_onemoung.service.facade.UserService;
import com.example.daonv_onemoung.store.facade.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    Store store;

    public UserDTO getUser(Long id) {
        UserDTO dto = store.user().findById(id);

        return dto;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        UserDTO dto = store.user().save(userDTO);

        return dto;
    }
}
