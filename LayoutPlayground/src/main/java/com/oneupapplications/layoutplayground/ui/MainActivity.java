package com.oneupapplications.layoutplayground.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.google.gson.Gson;

import com.oneupapplications.layoutplayground.R;
import com.oneupapplications.layoutplayground.model.Article;


public class MainActivity  extends FragmentActivity
        implements CategoryListFragment.Callbacks, onArtSelChngListener {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        if (findViewById(R.id.right_panel_container) != null) {
            mTwoPane = true;

            CategoryListFragment fragment1 = new CategoryListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.left_panel_container, fragment1)
                    .commit();

            ArtListFragment fragment = new ArtListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.right_panel_container, fragment)
                    .commit();
        } else {
            ArtListFragment fragment = new ArtListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.left_panel_container, fragment)
                    .commit();
        }

    }

    /**
     * Callback method from {@link CategoryListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onCatSelChanged(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(CategoryDetailFragment.ARG_ITEM_ID, id);
            CategoryDetailFragment fragment = new CategoryDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.right_panel_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, CategoryDetailActivity.class);
            detailIntent.putExtra(CategoryDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    @Override
    public void onArtSelChanged(Article artItem) {
        //FragmentManager fm = getFragmentManager();
        Toast mToast = Toast.makeText(this, artItem.getPostTitle(), Toast.LENGTH_SHORT);
        mToast.show();


        String myJsonArticle = new Gson().toJson(artItem, Article.class);

        if (mTwoPane) {

            //arguments.putString(CategoryDetailFragment.ARG_ITEM_ID, "1");
            ArtDetailFragment fragment = new ArtDetailFragment();
            Bundle arguments = new Bundle();
            arguments.putString("ArticleKey", myJsonArticle);
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.right_panel_container, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            Intent detailIntent = new Intent(this, ArtDetailActivity.class);
            detailIntent.putExtra("ArticleKey", myJsonArticle);
            startActivity(detailIntent);
        }

    }  //END onArtSelChanged
}