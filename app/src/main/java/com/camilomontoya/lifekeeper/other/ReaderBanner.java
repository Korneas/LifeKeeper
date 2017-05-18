package com.camilomontoya.lifekeeper.other;

import android.graphics.drawable.Drawable;

/**
 * Created by CamiloMontoya on 17/05/17.
 */

public class ReaderBanner {

    private String title,subtitle;
    private Drawable icon;

    public ReaderBanner(String title, String subtitle, Drawable icon) {
        this.title = title;
        this.subtitle = subtitle;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
