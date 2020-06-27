package io.github.furstenheim.store;

import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import io.github.furstenheim.store.reducers.AddRecipeFromHTMLReducer;
import io.github.furstenheim.store.reducers.Reducers;

public class Store implements Subscribers {
    ThreadPoolExecutor a = null;
    private CopyOnWriteArrayList<SideEffect> sideEffects;
    private CopyOnWriteArrayList<StateHandler> stateHandlers;
    private Queue<Action> actions = new LinkedBlockingQueue<>();
    private ThreadPoolExecutor threadPoolExecutor = null;


    public State getState() {
        return state;
    }

    State state = null;

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
        if (threadPoolExecutor == null) {
            handle(actions.poll());
        } else {
            threadPoolExecutor.execute(() -> handle(actions.poll()));
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
