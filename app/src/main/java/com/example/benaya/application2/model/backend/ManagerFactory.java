package com.example.benaya.application2.model.backend;

import com.example.benaya.application2.model.datasource.listDsManager;

/**
 * Created by User on 29/01/2017.
 */

public class ManagerFactory {

    static IDSManager manager = null;

    public static IDSManager getManager() {
        if (manager == null)
            manager = new listDsManager();
        return manager;
    }
}

