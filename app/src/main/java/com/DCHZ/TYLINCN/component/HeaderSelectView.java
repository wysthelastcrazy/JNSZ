package com.DCHZ.TYLINCN.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.listener.IHeaderSelecterTabClickListener;

/**
 * Created by yas on 2016/8/22.
 */
public class HeaderSelectView extends LinearLayout implements View.OnClickListener {
    private TextView txtLeft;
    private TextView txtRight;
    private IHeaderSelecterTabClickListener mListener;
    public HeaderSelectView(Context context) {
        super(context);
        init();
    }

    public HeaderSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeaderSelectView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LayoutInflater li= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.header_selecter_view,this,true);
        txtLeft= (TextView) this.findViewById(R.id.textView_left);
        txtLeft.setOnClickListener(this);
        txtRight= (TextView) this.findViewById(R.id.textView_right);
        txtRight.setOnClickListener(this);
        txtLeft.setEnabled(false);
        txtRight.setEnabled(true);
    }
    public void setTabSelectClickListener(IHeaderSelecterTabClickListener mListener){
        this.mListener=mListener;
    }
    public void setTabTitle(String tabLeft,String tabRight){
        txtLeft.setText(tabLeft);
        txtRight.setText(tabRight);
    }
    @Override
    public void onClick(View view) {
        if (mListener!=null){
            if (view==txtLeft){
                mListener.leftTabClick();
                txtLeft.setEnabled(false);
                txtRight.setEnabled(true);
            }else if (view==txtRight){
                mListener.rightTabClick();
                txtLeft.setEnabled(true);
                txtRight.setEnabled(false);
            }
        }
    }
}
