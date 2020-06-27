package io.github.furstenheim.store;

import lombok.Getter;

public abstract class StateHandler extends Subscriber<State> {
    @Getter
    private ThreadExecutorService threadExecutorService;
    public StateHandler (ThreadExecutorService threadExecutorService) {
        super(threadExecutorService);
    }
}
