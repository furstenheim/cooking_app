package io.github.furstenheim.store;

import lombok.Data;

@Data
public abstract class Action {
    public static class Navigation extends Action {
    }
    @Data
    public static class CreateRecipeFromHTML extends Action {
        private final String recipeHTML;
    }

    @Data
    public static class NavigateToList extends Action {

    }

    @Data
    public static class NavigateToView extends Action {
        private final State.Recipe nextRecipe;
    }
}
