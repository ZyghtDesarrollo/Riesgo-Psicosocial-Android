package com.zyght.riesgopsicosocial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zyght.riesgopsicosocial.entity.Recommendation;

import java.util.ArrayList;

/**
 * Created by Arley Mauricio Duarte on 5/4/17.
 */

public class RecommendationsListAdapter extends BaseAdapter {


    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Recommendation> mDataSource;

    public RecommendationsListAdapter(Context context, ArrayList<Recommendation> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        ViewHolder holder;
        Recommendation entity = mDataSource.get(position);


        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.subTitle = (TextView) convertView.findViewById(R.id.subtitle);


            holder.title.setText(entity.getTitle());
            holder.subTitle.setText(entity.getDescription());

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }


        return convertView;


    }

    class ViewHolder {

        TextView title;
        TextView subTitle;



    }
}
