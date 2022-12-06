package com.example.daonv_onemoung.store.sqllayer;

import com.example.daonv_onemoung.common.UserDTO;
import com.example.daonv_onemoung.store.entity.User;
import com.example.daonv_onemoung.store.facade.UserStore;
import com.example.daonv_onemoung.store.sqllayer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class SqlUserStore implements UserStore {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getUsername());
        user.setCreated(Instant.now());
        userRepository.save(user);
        userDTO.setId(user.getId());
        return userDTO;
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getName());

        return userDTO;
    }
}
