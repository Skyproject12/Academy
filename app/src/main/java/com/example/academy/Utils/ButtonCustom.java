package com.example.academy.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.example.academy.R;

import static android.view.Gravity.CENTER;

// custom button

public class ButtonCustom extends AppCompatButton {
    private Drawable enableBackground;
    private Drawable disableBackground;
    private int textColor;

    public ButtonCustom(Context context) {
        super(context);
        init();
    }

    public ButtonCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // initial enable bacground, disable background, textcolor
    private void init() {
        Resources resource = getResources();
        enableBackground = resource.getDrawable(R.drawable.bg_button);
        disableBackground = resource.getDrawable(R.drawable.bg_button_disable);

        textColor = ContextCompat.getColor(getContext(), android.R.color.background_light);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(isEnabled() ? enableBackground : disableBackground);
        setTextColor(textColor);
        setTextSize(12.f);
        setGravity(CENTER);
    }

}
