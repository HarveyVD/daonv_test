package com.example.daonv_onemoung.store;

import com.example.daonv_onemoung.store.facade.Store;
import com.example.daonv_onemoung.store.facade.UserStore;

public class BaseStore implements Store {
    protected Store wrapped;

    public BaseStore() {}

    @Override
    public UserStore user() {
        return wrapped.user();
    }
}
