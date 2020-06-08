package io.github.furstenheim.store;

import java.util.concurrent.ThreadPoolExecutor;

import lombok.Getter;

public abstract class SideEffect extends Subscriber<Action> {
    @Getter
    private ThreadPoolExecutor threadPoolExecutor;
    public SideEffect (ThreadPoolExecutor threadPoolExecutor) {
        super(threadPoolExecutor);
    }

}
