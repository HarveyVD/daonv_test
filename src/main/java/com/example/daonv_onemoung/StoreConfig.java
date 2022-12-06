package com.example.daonv_onemoung;

import com.example.daonv_onemoung.store.cachelayer.redis.RedisSupplier;
import com.example.daonv_onemoung.store.facade.Store;
import com.example.daonv_onemoung.store.sqllayer.SqlSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class StoreConfig {
    @Primary
    @Bean(name = "store")
    public Store store(RedisSupplier redisSupplier, SqlSupplier sqlStore) {
        redisSupplier.setWrapped(sqlStore);
        return redisSupplier;
    }
}
