package io.github.furstenheim.cookingapp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import io.github.furstenheim.store.Store;
import io.github.furstenheim.store.ThreadExecutorService;

public class AppStore extends Store {
    private static AppStore instance = null;

    private AppStore(ThreadExecutorService executor) {
        super(executor);
    }

    public static AppStore AppStore() {
        if (instance == null) {
            // Probably need to synchronize
            instance = new AppStore(new ThreadExecutorService(Executors.newSingleThreadExecutor(new NamedThreadFactory("store"))));
        }
        return instance;
    }

    public void enablePersistence() {
        // TODO
        // PersistenceSideEffect(store = this, persistenceThread =
    }
    private static class NamedThreadFactory implements ThreadFactory {
        String name;

        private NamedThreadFactory(String name) {
            this.name = name;
        }
        private AtomicInteger threadNumber = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, String.format("%s - %d", name, threadNumber.getAndIncrement()));
        }
    }
}
