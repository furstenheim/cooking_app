package io.github.furstenheim.cookingapp;

import java.util.concurrent.ThreadPoolExecutor;

import io.github.furstenheim.store.State;
import io.github.furstenheim.store.StateHandler;
import io.github.furstenheim.store.Store;

public abstract class ControllerView extends StateHandler implements LifecycleCallbacks  {
    private Store store;
    private ThreadPoolExecutor mainThread;
    private Boolean isActivityRunning = false;

    public ControllerView (Store store, ThreadPoolExecutor mainThread) {
        super(mainThread);
        this.store = store;
        this.mainThread = mainThread;
    }
    public State getState () {
        return store.getState();
    }
    @Override
    public void onStart() {
        isActivityRunning = true;
        store.getStateHandlers().add(this);
        handleState(store.getState());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {
        isActivityRunning = false;
    }

    @Override
    public void onDestroy() {
        store.getStateHandlers().remove(this);
    }

    @Override
    public void handle(State data) {
        if (isActivityRunning) handleState(data);
    }

    public abstract void handleState (State state);
}
