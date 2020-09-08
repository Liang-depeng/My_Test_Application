package com.example.my_application.other;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichTextConfig;
import com.zzhoujay.richtext.callback.ImageGetter;
import com.zzhoujay.richtext.callback.ImageLoadNotify;

class TxtGlideImageLoader implements ImageGetter {

    private Context context;

    public TxtGlideImageLoader(Context context) {
        this.context = context;
    }

    @Override
    public void registerImageLoadNotify(ImageLoadNotify imageLoadNotify) {

    }

    @Override
    public Drawable getDrawable(ImageHolder holder, RichTextConfig config, TextView textView) {

        final Drawable[] drawable = new Drawable[1];

        Glide.with(context).load(holder.getSource()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                drawable[0] = resource;
            }
        });
        return drawable[0];
    }

    @Override
    public void recycle() {

    }
}
