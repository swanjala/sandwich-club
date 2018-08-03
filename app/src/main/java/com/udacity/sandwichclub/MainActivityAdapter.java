package com.udacity.sandwichclub;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivityAdapter extends
        RecyclerView.Adapter<MainActivityAdapter.MainActivityViewAdapter> {

    private JSONArray mSandwichSet;
    private JSONArray mDetailSet;
    private LayoutInflater layoutInflater;
    private Context context;
    Sandwich sandwich = new Sandwich();

    public MainActivityAdapter(Context context, JSONArray sandwichdata, JSONArray mDetailSet) {

        this.mSandwichSet = sandwichdata;
        this.mDetailSet = mDetailSet;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;

    }

    @Override
    public MainActivityViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater
                .inflate(R.layout.activity_main_card_view,
                        parent, false);
        MainActivityViewAdapter viewHolder = new MainActivityViewAdapter(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainActivityViewAdapter holder, int position) {
        try {
            holder.setData(mSandwichSet, mDetailSet, position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mSandwichSet.length();
    }

    class MainActivityViewAdapter extends RecyclerView.ViewHolder {
        TextView tv_name;
        Button bt_view_sandwich_details;
        ImageView img_sandwich_photo;
        int position;

        private JSONObject dataObject;

        public MainActivityViewAdapter(View mainView) {
            super(mainView);
            tv_name = mainView.findViewById(R.id.tv_sandwich_name);
            img_sandwich_photo = mainView.findViewById(R.id.img_sandwich);
            bt_view_sandwich_details = mainView.findViewById(R.id.bt_view_details);


        }


        public void setData(final JSONArray currentData,
                            JSONArray currentDetailSet,
                            final int position) throws JSONException {

            dataObject = new JSONObject(currentDetailSet.getString(position));

            sandwich.setMainName(currentData.getString(position));
            sandwich.setImage(dataObject.getString("image"));
            this.tv_name.setText(sandwich.getMainName());
            Picasso.with(context)
                    .load(sandwich.getImage())
                    .fit()
                    .centerCrop()
                    .into(img_sandwich_photo);

            bt_view_sandwich_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("extra_position", position);

                    context.startActivity(intent);
                }
            });

            this.position = position;

        }
    }
}
