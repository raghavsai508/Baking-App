package com.example.android.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.Recipe;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    public static Recipe recipeSelected;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, Recipe recipe,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
        if (recipe != null) {
            recipeSelected = recipe;
            Intent intent = new Intent(context, WidgetListService.class);
            views.setRemoteAdapter(R.id.widget_list_view, intent);
        }

        views.setEmptyView(R.id.widget_list_view, R.id.empty_view);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, null ,appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static void updateRecipeWidget(Context context, AppWidgetManager appWidgetManager, Recipe recipe, int[] appWidgetIds) {
        for (int appWidgetId: appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipe, appWidgetId);
        }
    }

}

