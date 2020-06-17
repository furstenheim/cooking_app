package io.github.furstenheim.store;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class State {

    @Builder.Default
    List<Recipe> recipeList = new CopyOnWriteArrayList<Recipe>();

    @Builder.Default
    Navigation currentNavigation = Navigation.VIEW;

    @Builder.Default
    Recipe currentRecipe = null;

    @Data
    public static class Recipe {
        public String recipeText;
        public String url;
        public Recipe (String recipeText, String url) {
            this.recipeText = recipeText;
            this.url = url;
        }
    }

    public static enum Navigation {
        VIEW,
        EDIT,
        LIST
    }
}
