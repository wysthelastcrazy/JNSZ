package com.DCHZ.TYLINCN.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.DCHZ.TYLINCN.R;

/**
 * Created by yas on 2016/10/24.
 */
public class DetailShenPiYiJianTitleView extends RelativeLayout{
    public DetailShenPiYiJianTitleView(Context context) {
        super(context);
        init();
    }
    public DetailShenPiYiJianTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DetailShenPiYiJianTitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    private void init() {
        LayoutInflater li= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.detail_shenpiyijian_title,this,true);
    }
}
