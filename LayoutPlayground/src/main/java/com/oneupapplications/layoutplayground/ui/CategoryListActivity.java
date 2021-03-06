package com.oneupapplications.layoutplayground.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.oneupapplications.layoutplayground.R;
import com.oneupapplications.layoutplayground.model.Article;


/**
 * An activity representing a list of Categories. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link com.oneupapplications.layoutplayground.ui.CategoryDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link CategoryListFragment} and the item details
 * (if present) is a {@link com.oneupapplications.layoutplayground.ui.CategoryDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link CategoryListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class CategoryListActivity  extends FragmentActivity
        implements CategoryListFragment.Callbacks, onArtSelChngListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        if (findViewById(R.id.category_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((CategoryListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.category_list))
                    .setActivateOnItemClick(true);
        }

        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            //Bundle arguments = new Bundle();
            //arguments.putString(CategoryDetailFragment.ARG_ITEM_ID, id);

            ArtListFragment fragment = new ArtListFragment();
            //CategoryDetailFragment fragment = new CategoryDetailFragment();
            //fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.category_detail_container, fragment)
//                    .commit();
            //CategoryListFragment fragment = new CategoryListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.category_detail_container, fragment)
                    .commit();

        }
        // TODO: If exposing deep links into your app, handle intents here.
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
                    .replace(R.id.category_detail_container, fragment)
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

        /*
        Bundle arguments = new Bundle();
        arguments.putString(CategoryDetailFragment.ARG_ITEM_ID, "1");
        CategoryDetailFragment fragment = new CategoryDetailFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.category_detail_container, fragment)
                .commit();
        */


    }
}