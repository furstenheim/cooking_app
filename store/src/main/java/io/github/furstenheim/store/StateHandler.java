package io.github.furstenheim.store;

import java.util.concurrent.ThreadPoolExecutor;

import lombok.Getter;

public abstract class StateHandler extends Subscriber<State> {
    @Getter
    private ThreadPoolExecutor threadPoolExecutor;
    public StateHandler (ThreadPoolExecutor threadPoolExecutor) {
        super(threadPoolExecutor);
    }
}
