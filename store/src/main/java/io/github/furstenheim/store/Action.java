package io.github.furstenheim.store;

import lombok.Data;

@Data
public abstract class Action {
    public static class Navigation extends Action {
    }
    @Data
    public static class CreateRecipeFromHTML extends Action {
        public final String recipeHTML;
    }
}
