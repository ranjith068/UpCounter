package com.countersample.utils;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * Created by ranjith on 29/9/17.
 */

public class MyTabLayout  extends TabLayout{

    private static final int WIDTH_INDEX = 0;
    private static final int DIVIDER_FACTOR = 3;
    private static final String SCROLLABLE_TAB_MIN_WIDTH = "mScrollableTabMinWidth";



    public MyTabLayout(Context context) {
        super(context);
        initTabMinWidth();
    }

    public MyTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTabMinWidth();
    }

    public MyTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTabMinWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        try {
//            if (getTabCount() == 0)
//                return;
//            Field field = TabLayout.class.getDeclaredField("mScrollableTabMinWidth");
//            field.setAccessible(true);
//            field.set(this, (int) (getMeasuredWidth() / (float) getTabCount()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        ViewGroup tabLayout = (ViewGroup)getChildAt(0);
//
//        int childCount = tabLayout.getChildCount();
//
//        int widths[] = new int[childCount+1];
//
//        for(int i = 0; i < childCount; i++){
//            widths[i] = tabLayout.getChildAt(i).getMeasuredWidth();
//            widths[childCount] += widths[i];
//        }
//
//        int measuredWidth = getMeasuredWidth();
//        for(int i = 0; i < childCount; i++){
//            tabLayout.getChildAt(i).setMinimumWidth(measuredWidth*widths[i]/widths[childCount]);
//        }
    }

    private void initTabMinWidth() {
        int[] wh = Utils.getScreenSize(getContext());
        int tabMinWidth = wh[WIDTH_INDEX] / DIVIDER_FACTOR;

        Field field;
        try {
            field = TabLayout.class.getDeclaredField(SCROLLABLE_TAB_MIN_WIDTH);
            field.setAccessible(true);
            field.set(this, tabMinWidth);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
