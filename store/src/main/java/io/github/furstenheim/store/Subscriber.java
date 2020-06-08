package io.github.furstenheim.store;

import java.util.concurrent.ThreadPoolExecutor;

import lombok.Getter;

public abstract class Subscriber<T> {
    @Getter
    private ThreadPoolExecutor threadPoolExecutor;
    public Subscriber(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }
    public void onNext(T data) {
        if (this.threadPoolExecutor != null) {
            this.threadPoolExecutor.execute(() -> handle(data));
        }

        handle(data);
    }

    public abstract void handle(T data);
}
