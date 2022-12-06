package com.example.daonv_onemoung.store.cachelayer.redis;

import com.example.daonv_onemoung.store.facade.Store;
import com.example.daonv_onemoung.store.facade.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisSupplier implements Store {
    Store wrapped;

    public void setWrapped(Store store) {
        this.wrapped = store;
    }

    @Autowired
    RedisUserStore redisUserStore;

    @Override
    public UserStore user() {
        redisUserStore.setWrapped(wrapped);
        return redisUserStore;
    }
}
