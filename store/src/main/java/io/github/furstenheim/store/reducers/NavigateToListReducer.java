package io.github.furstenheim.store.reducers;

import io.github.furstenheim.store.Action;
import io.github.furstenheim.store.State;

public class NavigateToListReducer extends Reducer<Action.NavigateToList> {

    @Override
    public State.Navigation reduceNavigation(Action.NavigateToList action,
            State.Navigation current) {
        return State.Navigation.LIST;
    }
}
