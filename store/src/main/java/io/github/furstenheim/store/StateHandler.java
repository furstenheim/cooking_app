package io.github.furstenheim.store;

import lombok.Getter;

public abstract class StateHandler extends Subscriber<State> {
    @Getter
    private ThreadExecutor threadExecutorService;
    public StateHandler (ThreadExecutor threadExecutorService) {
        super(threadExecutorService);
    }
}
