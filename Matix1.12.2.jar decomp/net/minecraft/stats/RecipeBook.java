// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.stats;

import net.minecraft.item.crafting.CraftingManager;
import javax.annotation.Nullable;
import net.minecraft.item.crafting.IRecipe;
import java.util.BitSet;

public class RecipeBook
{
    protected final BitSet recipes;
    protected final BitSet unseenRecipes;
    protected boolean isGuiOpen;
    protected boolean isFilteringCraftable;
    
    public RecipeBook() {
        this.recipes = new BitSet();
        this.unseenRecipes = new BitSet();
    }
    
    public void apply(final RecipeBook that) {
        this.recipes.clear();
        this.unseenRecipes.clear();
        this.recipes.or(that.recipes);
        this.unseenRecipes.or(that.unseenRecipes);
    }
    
    public void setRecipes(final IRecipe recipe) {
        if (!recipe.isHidden()) {
            this.recipes.set(getRecipeId(recipe));
        }
    }
    
    public boolean containsRecipe(@Nullable final IRecipe recipe) {
        return this.recipes.get(getRecipeId(recipe));
    }
    
    public void removeRecipe(final IRecipe recipe) {
        final int i = getRecipeId(recipe);
        this.recipes.clear(i);
        this.unseenRecipes.clear(i);
    }
    
    protected static int getRecipeId(@Nullable final IRecipe recipe) {
        return CraftingManager.REGISTRY.getIDForObject(recipe);
    }
    
    public boolean isRecipeUnseen(final IRecipe recipe) {
        return this.unseenRecipes.get(getRecipeId(recipe));
    }
    
    public void setRecipeSeen(final IRecipe recipe) {
        this.unseenRecipes.clear(getRecipeId(recipe));
    }
    
    public void addDisplayedRecipe(final IRecipe recipe) {
        this.unseenRecipes.set(getRecipeId(recipe));
    }
    
    public boolean isGuiOpen() {
        return this.isGuiOpen;
    }
    
    public void setGuiOpen(final boolean p_192813_1_) {
        this.isGuiOpen = p_192813_1_;
    }
    
    public boolean isFilteringCraftable() {
        return this.isFilteringCraftable;
    }
    
    public void setFilteringCraftable(final boolean p_192810_1_) {
        this.isFilteringCraftable = p_192810_1_;
    }
}
