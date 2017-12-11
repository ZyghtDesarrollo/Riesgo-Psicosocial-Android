package com.zyght.riesgopsicosocial;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zyght.riesgopsicosocial.entity.Recommendation;
import com.zyght.riesgopsicosocial.entity.RpMember;

import java.util.ArrayList;

/**
 * Created by Arley Mauricio Duarte on 5/4/17.
 */

public class MembersListAdapter extends BaseAdapter {


    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<RpMember> mDataSource;
    private EmailDelegate emailDelegate;

    public MembersListAdapter(Context context, ArrayList<RpMember> items, EmailDelegate emailDelegate) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.emailDelegate = emailDelegate;
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
        final RpMember entity = mDataSource.get(position);


        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.subTitle = (TextView) convertView.findViewById(R.id.subtitle);
            holder.email = (ImageButton) convertView.findViewById(R.id.email);
            holder.phone = (ImageButton) convertView.findViewById(R.id.phone);
            holder.phoneNumber = (TextView) convertView.findViewById(R.id.phone_number);

            holder.title.setText(entity.getName());
            holder.subTitle.setText(entity.getEmail());

            if(!TextUtils.isEmpty(entity.getPhone())){
                holder.phoneNumber.setText(entity.getPhone());
            }


            holder.email.setOnClickListener(new View.OnClickListener()   {
                public void onClick(View v)  {
                    try {
                        emailDelegate.emailOnClick(entity.getEmail());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            holder.phone.setOnClickListener(new View.OnClickListener()   {
                public void onClick(View v)  {
                    try {
                        emailDelegate.callOnClick(entity.getPhone());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }


        return convertView;


    }

    class ViewHolder {

        TextView title;
        TextView subTitle;
        ImageButton email;
        ImageButton phone;
        TextView phoneNumber;


    }

    public  interface  EmailDelegate {
        public void emailOnClick(String email);
        public void callOnClick(String phone);
    }

}
