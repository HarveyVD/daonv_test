package com.example.daonv_onemoung.store.sqllayer;

import com.example.daonv_onemoung.store.facade.Store;
import com.example.daonv_onemoung.store.facade.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqlSupplier implements Store {
    @Autowired
    SqlUserStore sqlUserStore;

    @Override
    public UserStore user() {
        return sqlUserStore;
    }
}
