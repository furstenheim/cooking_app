package io.github.furstenheim.store.reducers;

import java.util.List;

import io.github.furstenheim.CopyDown;
import io.github.furstenheim.store.Action;
import io.github.furstenheim.store.State;

public class AddRecipeFromTextReducer extends Reducer<Action.CreateRecipeFromHTML> {

    public static final CopyDown copyDown = new CopyDown();
    @Override
    public List<State.Recipe> reduceRecipeList(Action.CreateRecipeFromHTML action,
            List<State.Recipe> recipeList) {
        String url = null;
        State.Recipe recipe = new State.Recipe(copyDown.convert(action.recipeHTML), url);
    }
}
