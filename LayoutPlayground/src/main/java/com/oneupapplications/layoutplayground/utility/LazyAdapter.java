package com.oneupapplications.layoutplayground.utility;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.oneupapplications.layoutplayground.R;
import com.oneupapplications.layoutplayground.model.Article;

import java.util.ArrayList;
import java.util.HashMap;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<Article> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public LazyAdapter(Activity a, ArrayList<Article>  d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
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
            vi = inflater.inflate(R.layout.article_row, null);

        TextView post_title = (TextView)vi.findViewById(R.id.post_title);
        TextView post_body = (TextView)vi.findViewById(R.id.post_body);
        TextView mTag = (TextView)vi.findViewById(R.id.tag);
        ImageView post_image=(ImageView)vi.findViewById(R.id.post_image);
        TextView likelihood = (TextView)vi.findViewById(R.id.likelihood);
        
        HashMap<String, String> song = new HashMap<String, String>();
        //song = data.get(position);
        Article temp = data.get(position);
        
        // Setting all values in listview
        post_title.setText(temp.getPostTitle());
        post_body.setText(temp.getPostBody());
        mTag.setText(temp.getTags() + "  |  " + temp.getPredictionYear());
        imageLoader.DisplayImage(temp.getImageUrl(), post_image);
        likelihood.setText(temp.getLikeliness());
        return vi;
    }
}