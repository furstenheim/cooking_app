package io.github.furstenheim.store;

import java.util.concurrent.ExecutorService;

public class ThreadExecutorService implements ThreadExecutor {
    private ExecutorService executorService;
    public ThreadExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }
}
