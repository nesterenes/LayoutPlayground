package com.oneupapplications.layoutplayground.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.oneupapplications.layoutplayground.api.Controller.WsAPIController;
import com.oneupapplications.layoutplayground.model.Category;
import com.oneupapplications.layoutplayground.utility.CategoryAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CategoryListFragment extends ListFragment {

    //Class Level Variables
    private WsAPIController controller = null;
    public ArrayList<Category> CategoryArray = new ArrayList<Category>();

    public static final String ARG_ITEM_Key = "CategoryKey";


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoryListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setListAdapter(new CategoryAdapter(getActivity(), Category.ITEMS));


        if(CategoryArray.size() < 1 && savedInstanceState == null){
            GetWsCategoryList();
        } else if (savedInstanceState.containsKey(ARG_ITEM_Key)) {
            String tempContent = savedInstanceState.getString(ARG_ITEM_Key);
            CategoryArray = new ArrayList<Category>(Arrays.asList(new Gson().fromJson(tempContent, Category[].class)));
            setListAdapter(new CategoryAdapter(getActivity(), CategoryArray));
        }

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
//        if (savedInstanceState != null
//                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
//            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
//        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (CategoryArray.size() > 0) {

            Category[] tempArray = new Category[CategoryArray.size()];
            tempArray = CategoryArray.toArray(tempArray);

            String myJsonCategory = new Gson().toJson(tempArray, Category[].class);

            // Serialize and persist the activated item position.
            outState.putString(ARG_ITEM_Key, myJsonCategory);

        }
    }


    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        Toast mToast = Toast.makeText(getActivity(), Long.toString(id), Toast.LENGTH_SHORT);
        mToast.show();

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
//        mCallbacks.onCatSelChanged(DummyContent.ITEMS.get(position).id);

//        onCatSelChngListener listener = (onCatSelChngListener)getActivity();
//        listener.onCatSelChanged(Integer.parseInt(DummyContent.ITEMS.get(position).id));

    }






    protected void GetWsCategoryList() {
        controller = WsAPIController.getInstance(getActivity());
        controller.getCategoriesAll(new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonobj_tmp = jsonArray.getJSONObject(i);

                        Category catTemp = new Category(
                                Integer.parseInt(jsonobj_tmp.getString("Id"))
                                , jsonobj_tmp.getString("Title")
                        );

                        CategoryArray.add(catTemp);
                    } //END Loop

                    setListAdapter(new CategoryAdapter(getActivity(), CategoryArray));

                } catch (JSONException e) {
                    Log.i("CategoryListFrag-GetWsCategoryList", e.toString());
                } //END TryCatch
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("VolleyInfo", volleyError.toString());
            }
        });

    } //END GetWsCategoryList


}
