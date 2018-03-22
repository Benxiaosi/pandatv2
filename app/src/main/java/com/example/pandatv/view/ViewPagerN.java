package com.example.pandatv.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 本丶小丝 on 2018/3/12.
 */

public class ViewPagerN extends ViewPager {


    public ViewPagerN(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPagerN(Context context) {
        super(context);
    }
private boolean isCroll=false;

    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isCroll){
            return super.onTouchEvent(ev);
        }else {
            return false;
        }

    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        if (isCroll){
            return super.onInterceptHoverEvent(event);
        }else {
            return false;
        }
    }
}
