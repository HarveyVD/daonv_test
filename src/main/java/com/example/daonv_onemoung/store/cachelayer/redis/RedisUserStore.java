package com.example.daonv_onemoung.store.cachelayer.redis;

import com.example.daonv_onemoung.common.UserDTO;
import com.example.daonv_onemoung.store.BaseStore;
import com.example.daonv_onemoung.store.facade.Store;
import com.example.daonv_onemoung.store.facade.UserStore;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisUserStore implements UserStore {
    Gson gson = new Gson();

    @Autowired
    RedisTemplate redisTemplate;

    Store wrapped;

    public void setWrapped(Store store) {
        this.wrapped = store;
    }

    @Override
    public UserDTO findById(Long id) {
        String key = String.format("user-%s", id);

        String resStr = (String) redisTemplate.opsForValue().get(key);

        UserDTO res = gson.fromJson(resStr, UserDTO.class);
        if (res == null) {
            res = wrapped.user().findById(id);
            if (res != null) {
                redisTemplate.opsForValue().set(key, gson.toJson(res));
            }
        }

        return res;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        UserDTO res = wrapped.user().save(userDTO);

        String key = String.format("user-%s", res.getId());
        redisTemplate.opsForValue().set(key, gson.toJson(res));

        return res;
    }
}
