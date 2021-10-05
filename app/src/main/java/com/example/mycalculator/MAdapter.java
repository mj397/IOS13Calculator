package com.example.mycalculator;


import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by Mohamad Jamous on 9/12/2021
 */


public class MAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ButtonObject> buttonObjects;
    private ClickListener listener;

    public MAdapter(Context mContext, ArrayList<ButtonObject> buttonObjects, ClickListener listener) {
        this.mContext = mContext;
        this.buttonObjects = buttonObjects;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return buttonObjects.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View v1 = layoutInflater.inflate(R.layout.button_layout, null);

        Button button = CustomizeButton(buttonObjects.get(position));

        v1 = (View) button;

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isNumeric(buttonObjects.get(position).getSymbol()))
                {
                    listener.onClick(true, buttonObjects.get(position), true);
                }
                else
                {
                    listener.onClick(true, buttonObjects.get(position), false);
                }

            }
        });

        return v1;
    }



    private Button CustomizeButton(ButtonObject object)
    {
        Button button = new Button(mContext);

        button.setWidth(object.getWidth());
        button.setHeight(object.getHeight());

//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//        );
//        params.setMargins(60, 60, 60, 60);
//
//        button.setLayoutParams(params);

        button.setText(object.getSymbol());
        button.setBackground(object.getBackground());
        button.setTextColor(mContext.getResources().getColor(object.getTextColor()));
        button.setGravity(Gravity.CENTER);
        button.setTextSize(18);
        return button;

    }


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
