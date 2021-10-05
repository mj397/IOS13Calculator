package com.example.mycalculator;


import android.graphics.drawable.Drawable;

/**
 * Created by Mohamad Jamous on 9/12/2021
 */
public class ButtonObject {

    String symbol;
    Drawable background;
    int textColor;
    int width;
    int height;

    public ButtonObject(int width, int height , Drawable background, int textColor, String symbol) {
        this.symbol = symbol;
        this.background = background;
        this.textColor = textColor;
        this.width = width;
        this.height = height;
    }


    public String getSymbol() {
        return symbol;
    }

    public Drawable getBackground() {
        return background;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
