package com.DCHZ.TYLINCN.msglist;


import com.DCHZ.TYLINCN.msglist.listener.IPageScrollListener;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

public class ListViewV6 extends ListView{
	private final String TAG = "ListViewV6";
	
	private IPageScrollListener mListener;
	private float xDistance, yDistance, xLast, yLast;
	
	public ListViewV6(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ListViewV6(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public ListViewV6(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public void setIPageListener(IPageScrollListener mListener){
		this.mListener = mListener;
	}
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		try{
			super.dispatchDraw(canvas);
		}catch(IllegalArgumentException ee){
//			MyLog.error(TAG,ee);
		}
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);
//		MyLog.debug(TAG,"[onScrollChanged]" + "l:" + l + " t:" + t + " oldl:" + oldl + " oldt:" + oldt);
		if(mListener != null){
			View viewFirst = getChildAt(0);
			if(viewFirst != null){
				int top = viewFirst.getTop();
				top = Math.abs(top);
				if(top > viewFirst.getHeight() || getFirstVisiblePosition() >= 1){
					mListener.onPageScroll(10000);
				}else{
					mListener.onPageScroll(top);
				}
			}
		}
	}
	
	@Override  
    public boolean onInterceptTouchEvent(MotionEvent ev) {  
        switch (ev.getAction()) {  
            case MotionEvent.ACTION_DOWN:  
                xDistance = yDistance = 0f;  
                xLast = ev.getX();  
                yLast = ev.getY();  
                break;  
            case MotionEvent.ACTION_MOVE:  
                final float curX = ev.getX();  
                final float curY = ev.getY();             
                xDistance += Math.abs(curX - xLast);  
                yDistance += Math.abs(curY - yLast);  
                xLast = curX;  
                yLast = curY;  
                if(xDistance > yDistance){  
                    return false;  
                }    
        }  
        return super.onInterceptTouchEvent(ev);  
    }
}
