package com.camilomontoya.lifekeeper.other;

import android.graphics.Bitmap;

/**
 * Created by CamiloMontoya on 16/05/17.
 */

public class EmergencyBanner {

    private String titulo, subtitulo;
    private boolean avaliable;
    private Bitmap bitmap;

    public EmergencyBanner(String titulo, String subtitulo, boolean avaliable, Bitmap bitmap) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.avaliable = avaliable;
        this.bitmap = bitmap;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public boolean isAvaliable() {
        return avaliable;
    }

    public void setAvaliable(boolean avaliable) {
        this.avaliable = avaliable;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
