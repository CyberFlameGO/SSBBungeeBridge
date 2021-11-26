package com.bgsoftware.superiorskyblock.modules.bungeebridge.database;

import com.bgsoftware.superiorskyblock.api.data.DatabaseBridge;
import com.bgsoftware.superiorskyblock.api.data.DatabaseFilter;
import com.bgsoftware.superiorskyblock.api.objects.Pair;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Consumer;

public final class BungeeDatabaseBridge implements DatabaseBridge {

    private static final BungeeDatabaseBridge INSTANCE = new BungeeDatabaseBridge();

    public static BungeeDatabaseBridge getInstance() {
        return INSTANCE;
    }

    private BungeeDatabaseBridge() {

    }

    @Override
    public void loadAllObjects(String table, Consumer<Map<String, Object>> consumer) {
        // We do not load objects, the bungee-handler will load them for us.
    }

    @Override
    public void startSavingData() {

    }

    @Override
    public void batchOperations(boolean b) {

    }

    @SafeVarargs
    @Override
    public final void updateObject(String table, @Nullable DatabaseFilter databaseFilter, Pair<String, Object>... pairs) {

    }

    @SafeVarargs
    @Override
    public final void insertObject(String table, Pair<String, Object>... pairs) {

    }

    @Override
    public void deleteObject(String table, @Nullable DatabaseFilter databaseFilter) {

    }

    @Override
    public void loadObject(String table, @Nullable DatabaseFilter databaseFilter, Consumer<Map<String, Object>> consumer) {

    }

}
