package com.mitienda.tiendavirtual.fragments;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ButtonHightLighterOnTouch implements View.OnTouchListener {

    ImageButton imageButton;
    ImageView imageView;

    private static final int TRANSPARENT_GREY = Color.argb(0, 185, 185, 185);
    private static final int FILTERED_GREY = Color.argb(155, 185, 185, 185);


    public ButtonHightLighterOnTouch(final ImageButton imageButton) {
          super();
          this.imageButton = imageButton;
     }

    public ButtonHightLighterOnTouch(final ImageView imageView) {
         super();
         this.imageView = imageView;
    }

    public boolean onTouch(final View view, final MotionEvent motionEvent) {
         if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
             //grey color filter, you can change the color as you like
             if (imageButton != null) imageButton.setColorFilter(Color.argb(155, 185, 185, 185));
             if (imageView != null) imageView.setColorFilter(Color.argb(155, 185, 185, 185));
         } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
             if (imageButton != null) imageButton.setColorFilter(Color.argb(0, 185, 185, 185));
             if (imageView != null) imageView.setColorFilter(Color.argb(0, 185, 185, 185));
         }
         return false;
    }
}

