package io.github.furstenheim.store;

import lombok.Getter;

public abstract class Subscriber<T> {
    @Getter
    private ThreadExecutorService threadExecutorService;
    public Subscriber(ThreadExecutorService threadExecutorService) {
        this.threadExecutorService = threadExecutorService;
    }
    public void onNext(T data) {
        if (this.threadExecutorService != null) {
            this.threadExecutorService.execute(() -> handle(data));
        }

        handle(data);
    }

    public abstract void handle(T data);
}
