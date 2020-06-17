package io.github.furstenheim.store.reducers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import io.github.furstenheim.store.Action;
import io.github.furstenheim.store.State;

public abstract class Reducer<T extends Action> {
    public State reduce (T action, State currentState) {
        return State.builder()
                .recipeList(reduceRecipeList(action, currentState.getRecipeList()))
                .currentNavigation(reduceNavigation(action, currentState.getCurrentNavigation()))
                .build();
    }
    public State.Navigation reduceNavigation(T action, State.Navigation current) {
        return current;
    }
    public List<State.Recipe> reduceRecipeList (T action, List<State.Recipe> recipeList) {
        return recipeList.stream()
                .map(r -> reduceRecipeItem(action, r))
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }
    public State.Recipe reduceRecipeItem (T action, State.Recipe recipe) {
        return recipe;
    }
}
