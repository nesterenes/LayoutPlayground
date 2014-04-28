package com.oneupapplications.layoutplayground.utility;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oneupapplications.layoutplayground.R;
import com.oneupapplications.layoutplayground.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Category> data;
    private static LayoutInflater inflater=null;

    public CategoryAdapter(Activity a, ArrayList<Category> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.category_row, null);

        TextView row_item = (TextView)vi.findViewById(R.id.cat_row_item);

        Category temp = data.get(position);
        
        // Setting all values in listview
        row_item.setText(temp.getTitle());
        return vi;
    }
}