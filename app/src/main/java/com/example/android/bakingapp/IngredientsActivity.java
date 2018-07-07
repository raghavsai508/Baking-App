package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.fragments.IngredientsFragment;
import com.example.android.bakingapp.models.Recipe;

public class IngredientsActivity extends AppCompatActivity {

    private static final String INTENT_KEY = "recipe";

    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        Intent intentCalled = getIntent();
        if (intentCalled != null) {
            if (intentCalled.hasExtra(INTENT_KEY)) {
                recipe = (Recipe) intentCalled.getSerializableExtra(INTENT_KEY);
            }
        }

        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        ingredientsFragment.setRecipe(recipe);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.ingredients_container, ingredientsFragment)
                .commit();
    }
}
