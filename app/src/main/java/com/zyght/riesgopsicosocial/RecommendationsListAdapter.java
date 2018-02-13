package com.zyght.riesgopsicosocial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
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
    private OnRecommendationListener onRecommendationListener;

    public RecommendationsListAdapter(Context context, ArrayList<Recommendation> items, OnRecommendationListener onRecommendationListener) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.onRecommendationListener = onRecommendationListener;
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
        final Recommendation entity = mDataSource.get(position);


        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.recomentadion_row, null);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.subTitle = (TextView) convertView.findViewById(R.id.subtitle);


            holder.title.setText(entity.getTitle());
            holder.subTitle.setText(entity.getDescription());

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }


        convertView.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                onRecommendationListener.onRecommendationClick(entity);
            }
        });

        return convertView;


    }

    class ViewHolder {

        TextView title;
        TextView subTitle;




    }

    interface OnRecommendationListener{
        void onRecommendationClick(Recommendation recommendation);

    }
}
