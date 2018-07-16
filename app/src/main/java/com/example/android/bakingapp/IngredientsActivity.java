package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.fragments.IngredientsFragment;
import com.example.android.bakingapp.fragments.RecipeDetailFragment;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.models.RecipeStep;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity implements IngredientsFragment.IngFragmentRecipeStepClickListener, RecipeDetailFragment.OnDetailFragmentInteractionListener {

    private static final String INTENT_KEY = "recipe";
    private static final String INTENT_KEY_RECIPE_STEPS = "recipe_steps";
    private static final String INTENT_KEY_RECIPE_STEP_POSITION = "recipe_step_position";

    private Recipe recipe;
    private RecipeDetailFragment recipeDetailFragment;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        Intent intentCalled = getIntent();
        if (intentCalled != null) {
            if (intentCalled.hasExtra(INTENT_KEY)) {

                recipe = intentCalled.getParcelableExtra(INTENT_KEY);

            }
            if (findViewById(R.id.tablet_layout) != null) {
                mTwoPane = true;
                if (savedInstanceState == null) {
                    setupTabletLayout();
                }
            } else {
                mTwoPane = false;
            }

            IngredientsFragment ingredientsFragment = new IngredientsFragment();
            ingredientsFragment.setRecipe(recipe);
            ingredientsFragment.setIngFragmentRecipeStepClickListener(this);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.ingredients_container, ingredientsFragment)
                    .commit();
        }
    }

    @Override
    public void recipeStepClickAt(int position) {
        if (mTwoPane) {
            recipeDetailFragment.refreshToPosition(position);
        } else {
            Intent intent = new Intent(this, RecipeDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(INTENT_KEY_RECIPE_STEPS, (ArrayList<RecipeStep>)recipe.getmRecipeSteps());
            bundle.putInt(INTENT_KEY_RECIPE_STEP_POSITION, position);
            intent.putExtra("extra", bundle);
            startActivity(intent);
        }
    }

    private void setupTabletLayout() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        recipeDetailFragment = new RecipeDetailFragment();
        recipeDetailFragment.setRecipeStepList(recipe.getmRecipeSteps(), true);
        fragmentManager.beginTransaction()
                .add(R.id.detail_recipe_container, recipeDetailFragment)
                .commit();
    }

    
    @Override
    public void onDetailFragmentInteraction() {
        recipeDetailFragment.refreshToPosition(0);
    }
}
