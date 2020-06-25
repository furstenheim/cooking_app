package io.github.furstenheim.store;

import java.util.concurrent.ThreadPoolExecutor;

public class Store {
    ThreadPoolExecutor a = null;

    public State getState() {
        return state;
    }

    State state = null;
}
