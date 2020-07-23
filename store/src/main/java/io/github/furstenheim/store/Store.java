package io.github.furstenheim.store;

import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import io.github.furstenheim.store.reducers.Reducers;

public class Store implements Subscribers {
    private CopyOnWriteArrayList<SideEffect> sideEffects = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<StateHandler> stateHandlers = new CopyOnWriteArrayList<>();
    private Queue<Action> actions = new LinkedBlockingQueue<>();
    private ThreadExecutorService threadExecutor = null;

    public Store(ThreadExecutorService threadExecutor) {
        this.threadExecutor = threadExecutor;
    }

    public State getState() {
        return state;
    }

    State state = State.builder().build();

    @Override
    public CopyOnWriteArrayList<SideEffect> getSideEffects() {
        return sideEffects;
    }

    @Override
    public CopyOnWriteArrayList<StateHandler> getStateHandlers() {
        return stateHandlers;
    }

    @Override
    public synchronized void dispatch(Action action) {
        actions.offer(action);
        if (threadExecutor == null) {
            handle(actions.poll());
        } else {
            threadExecutor.execute(() -> handle(actions.poll()));
        }
    }

    @Override
    public void dispatch(State state) {
        this.state = state;
        for (StateHandler stateHandler : this.stateHandlers) {
            stateHandler.onNext(state);
        }
    }

    private void handle(Action action) {
        State newState = reduce(action, state);
        this.dispatch(newState);
        for (SideEffect sideEffect : this.sideEffects) {
            sideEffect.onNext(action);
        }

    }

    private State reduce (Action action, State currentState) {
        // Breaking open close principle :/
        if (action instanceof Action.CreateRecipeFromHTML) {
            return Reducers.AddRecipeFromHTMLReducer.reduce((Action.CreateRecipeFromHTML) action, currentState);
        } else if (action instanceof Action.NavigateToView) {
            return Reducers.NavigateToViewReducer
                    .reduce((Action.NavigateToView) action, currentState);
        } else if (action instanceof Action.NavigateToList) {
            return Reducers.NavigateToListReducer
                    .reduce((Action.NavigateToList) action, currentState);
        } else {
            throw new IllegalArgumentException(String.format("Not supported action %s", action));
        }
    }
}
