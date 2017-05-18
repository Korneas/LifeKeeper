package com.camilomontoya.lifekeeper.other;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.camilomontoya.lifekeeper.HomeActivity;
import com.camilomontoya.lifekeeper.KeeperActivity;
import com.camilomontoya.lifekeeper.R;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by CamiloMontoya on 16/05/17.
 */

public class EmergencyBannerAdapter extends RecyclerView.Adapter<EmergencyBannerAdapter.MyViewHolder> {

    private List<EmergencyBanner> bannerList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, subtitle;
        public ImageView banner,shadow;
        public RelativeLayout frame;

        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.banner_title);
            subtitle = (TextView) view.findViewById(R.id.banner_subtitle);
            banner = (ImageView) view.findViewById(R.id.image_banner);
            shadow = (ImageView) view.findViewById(R.id.shadow_banner);

            frame = (RelativeLayout) view.findViewById(R.id.layout_banner);

            context = view.getContext();

        }
    }

    public EmergencyBannerAdapter(List<EmergencyBanner> bannerList){
        this.bannerList = bannerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banner_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final EmergencyBanner banner = bannerList.get(position);
        holder.title.setText(banner.getTitulo());
        holder.subtitle.setText(banner.getSubtitulo());

        holder.title.setTypeface(ControlTipografia.getInstance().getTypeTitle());
        holder.subtitle.setTypeface(ControlTipografia.getInstance().getTypeSubtitle());

        holder.banner.setImageBitmap(banner.getBitmap());

        if(banner.isAvaliable()){
            holder.shadow.setVisibility(View.INVISIBLE);
            holder.frame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent;
                    switch (position){
                        case 0:
                            intent = new Intent(context,KeeperActivity.class);
                            break;
                        default:
                            intent = new Intent(context,HomeActivity.class);
                            break;
                    }
                    context.startActivity(intent);
                }
            });
        } else {
            holder.shadow.setVisibility(View.VISIBLE);
            holder.title.setTextColor(Color.parseColor("#FF838383"));
        }
    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

}
