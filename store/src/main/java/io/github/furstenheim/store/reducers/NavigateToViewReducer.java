package io.github.furstenheim.store.reducers;

import io.github.furstenheim.store.Action;
import io.github.furstenheim.store.State;

public class NavigateToViewReducer extends Reducer<Action.NavigateToView> {

    @Override
    public State.Navigation reduceNavigation(Action.NavigateToView action,
            State.Navigation current) {
        return State.Navigation.VIEW;
    }

    @Override
    public State.Recipe reduceRecipeItem(Action.NavigateToView action, State.Recipe recipe) {
        return action.getNextRecipe();
    }
}
