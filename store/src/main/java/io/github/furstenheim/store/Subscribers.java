package io.github.furstenheim.store;

import java.util.concurrent.CopyOnWriteArrayList;

public interface Subscribers {
    public CopyOnWriteArrayList<SideEffect> getSideEffects();
    public CopyOnWriteArrayList<StateHandler> getStateHandlers();
    public void dispatch (Action action);
    public void dispatch (State state);
}
