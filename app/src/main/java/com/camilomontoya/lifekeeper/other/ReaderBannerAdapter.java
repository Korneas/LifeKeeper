package com.camilomontoya.lifekeeper.other;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.camilomontoya.lifekeeper.R;

import java.util.List;

/**
 * Created by CamiloMontoya on 17/05/17.
 */

public class ReaderBannerAdapter extends RecyclerView.Adapter<ReaderBannerAdapter.MyViewHolder> {

    private List<ReaderBanner> bannerList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, subtitle;
        public ImageView icon;
        public FrameLayout frame;

        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.read_title);
            subtitle = (TextView) view.findViewById(R.id.read_subtitle);
            icon = (ImageView) view.findViewById(R.id.reader_icon);

            frame = (FrameLayout) view.findViewById(R.id.frame_read);

        }
    }

    public ReaderBannerAdapter(List<ReaderBanner> bannerList){
        this.bannerList = bannerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.read_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ReaderBanner banner = bannerList.get(position);
        holder.title.setText(banner.getTitle());
        holder.subtitle.setText(banner.getSubtitle());

        holder.title.setTypeface(ControlTipografia.getInstance().getTypeTitle());
        holder.subtitle.setTypeface(ControlTipografia.getInstance().getTypeSubtitle());

        holder.icon.setImageDrawable(banner.getIcon());
    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

}