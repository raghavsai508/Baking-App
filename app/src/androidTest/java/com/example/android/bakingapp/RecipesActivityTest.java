package com.example.android.bakingapp;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.example.android.bakingapp.Helpers.RecipeHelper;
import com.example.android.bakingapp.fragments.RecipeFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecipesActivityTest {
    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        RecipeFragment recipeFragment = (RecipeFragment) intentsTestRule.getActivity().getSupportFragmentManager().findFragmentByTag(RecipeFragment.class.getSimpleName());
        mIdlingResource = recipeFragment.getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }

    @Test
    public void idlingResourceTest() {
//        onData(anything()).inAdapterView(withId(R.id.recipe_recycler_view)).atPosition(0).perform(click());
//        onView(new RecyclerViewMatcher());
//        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        Intent intent = new Intent(targetContext, IngredientsActivity.class);
//        List<Recipe> recipeList = RecipeHelper.getRecipesList(targetContext);
//        intent.putExtra("recipe", recipeList.get(0));
//        intentsTestRule.launchActivity(intent);

        onView(ViewMatchers.withId(R.id.recipe_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        intended(hasComponent(IngredientsActivity.class.getName()));
    }


    @Test
    public void nutellaPieTextDisplayedTest() {
        onView(withText("Nutella Pie")).check(matches(isDisplayed()));
    }

    @Test
    public void numberOfRecipesDisplayedTest() {
        RecipeFragment recipeFragment = (RecipeFragment) intentsTestRule.getActivity().getSupportFragmentManager().findFragmentByTag(RecipeFragment.class.getSimpleName());
        RecyclerView recyclerView = recipeFragment.getView().findViewById(R.id.recipe_recycler_view);
        int adapterListCount = recyclerView.getAdapter().getItemCount();
        int recipeListCount = RecipeHelper.getRecipesList(intentsTestRule.getActivity().getApplicationContext()).size();
        assertThat(adapterListCount, is(recipeListCount));
    }

}
