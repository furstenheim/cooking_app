package io.github.furstenheim.store;

import lombok.Getter;

public abstract class SideEffect extends Subscriber<Action> {
    @Getter
    private ThreadExecutorService threadExecutorService;
    public SideEffect (ThreadExecutorService threadExecutorService) {
        super(threadExecutorService);
    }

}
