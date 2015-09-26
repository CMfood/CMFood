package com.hackcmu.cmfud;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CustomAdapter extends ArrayAdapter<Restaurant>{

    final int BAR_LEFT_BASE_PADDING = 15;
    final int BAR_TOPBOTTOM_PADDING = 50;

    public CustomAdapter(Context context, int resource, List<Restaurant> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.restaurant_item, null);
        }

        Restaurant p = getItem(position);

        if (p != null) {
            TextView nameTextView = (TextView) v.findViewById(R.id.name_textview);
            if (nameTextView != null) {
                nameTextView.setText(p.getName().toUpperCase());

                ShapeDrawable background = new ShapeDrawable();
                float r = 80 * 2;
                float[] outerRadii = new float[] {r, r, r, r, r, r, r, r};
                background.setShape(new RoundRectShape(outerRadii, null, null));
                background.getPaint().setColor(p.getColor());

                nameTextView.setBackground(background);
            }
            ImageView timeBarImageView = (ImageView) v.findViewById(R.id.time_bar_imageview);
            if (timeBarImageView != null) {
                timeBarImageView.setImageDrawable(new ColorDrawable(p.getColor()));
                int leftPadding = Restaurant.determinePosition(p.getStartTime());
                int width = Restaurant.determinePosition(p.getEndTime());

                timeBarImageView.setPadding(leftPadding,
                        BAR_TOPBOTTOM_PADDING, 0, BAR_TOPBOTTOM_PADDING);
                timeBarImageView.setLayoutParams(new RelativeLayout.LayoutParams(width,
                        RelativeLayout.LayoutParams.FILL_PARENT));
            }
            RelativeLayout timeLayout = (RelativeLayout) v.findViewById(R.id.time_layout);
            if (timeLayout != null) {
                timeLayout.setPadding(BAR_LEFT_BASE_PADDING, 0, 0, 0);
            }
            final View elapsedTimeView = v.findViewById(R.id.elapsed_time_view);
            if (elapsedTimeView != null) {
                final Handler handler = new Handler();
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                GregorianCalendar calendar = new GregorianCalendar();
                                int now = calendar.get(Calendar.HOUR_OF_DAY) * 100;
                                elapsedTimeView.setLayoutParams(new RelativeLayout.LayoutParams(
                                        Restaurant.determinePosition(now) + 5,
                                        RelativeLayout.LayoutParams.FILL_PARENT));
                            }
                        });
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 1000 * 60);
            }
        }

        return v;
    }
}
