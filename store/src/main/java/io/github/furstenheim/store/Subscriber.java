package io.github.furstenheim.store;

import lombok.Getter;

public abstract class Subscriber<T> {
    @Getter
    private ThreadExecutor threadExecutor;
    public Subscriber(ThreadExecutor threadExecutor) {
        this.threadExecutor = threadExecutor;
    }
    public void onNext(T data) {
        if (this.threadExecutor != null) {
            this.threadExecutor.execute(() -> handle(data));
        }

        handle(data);
    }

    public abstract void handle(T data);
}
