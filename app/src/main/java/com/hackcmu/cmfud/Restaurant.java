package com.hackcmu.cmfud;

public class Restaurant {

    String mName;
    int mColor;
    int mStartTime;
    int mEndTime;
    int mLeftPadding;
    int mWidth;

    public Restaurant(String name, int color, int startTime, int endTime, int leftPad, int width) {
        mName = name;
        mColor = color;
        mStartTime = startTime;
        mEndTime = endTime;
        mLeftPadding = leftPad;
        mWidth = width;
    }

    public String getName() {
        return mName;
    }

    public int getColor() {
        return mColor;
    }

    public int getStartTime() {
        return mStartTime;
    }

    public int getEndTime() {
        return mEndTime;
    }

    static int determinePosition(int time) {
        int difference = time - 700;
        if (difference <= 200) {
            return (int)(difference * (165.0 / 350.0));
        } else if (difference <= 400) {
            return (int)(difference * (142.0 / 350.0));
        } else if (difference <= 1000) {
            return (int)(difference * (138.0 / 350.0));
        } else if (difference <= 1700) {
            return (int)(difference * (137.0 / 350.0));
        }
        return 0;
    }
}
