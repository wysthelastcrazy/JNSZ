package com.DCHZ.TYLINCN.msglist.itemview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.entity.PHeTongSearchItemEntity;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;
import com.DCHZ.TYLINCN.util.TimeUtils;

/**
 * Created by yas on 2016/10/12.
 */
public class SearchHeTongItemView extends BaseItemView<PHeTongSearchItemEntity>{
    private TextView txtJinE;
    private TextView txtName;
    private TextView txtTime;
    private PHeTongSearchItemEntity mEntity;
    public SearchHeTongItemView(Context context) {
        super(context);
    }

    @Override
    public void setMsg(PHeTongSearchItemEntity pHeTongSearchItemEntity) {
        mEntity=pHeTongSearchItemEntity;
        txtJinE.setText(mEntity.HTJinE);
        txtName.setText(mEntity.HTMingCheng);
        String[] strs=mEntity.HTQianDingRiQi.split("/");
        if (strs!=null&&strs.length>0){
            int month= Integer.parseInt(strs[1]);
            String year=strs[0];
            if (TimeUtils.getCurrYear().equals(year)){
                if (month%2==0){
                    txtTime.setBackgroundColor(Color.parseColor("#E1E3D0"));
                }else{
                    txtTime.setBackgroundColor(Color.parseColor("#CC00FF"));
                }
            }else{
                txtTime.setBackgroundColor(Color.parseColor("#F1F1F1"));
            }
            txtTime.setText(month+"月");
        }

    }

    @Override
    public PHeTongSearchItemEntity getMsg() {
        return mEntity;
    }

    @Override
    public void onInflate() {
        LayoutInflater li= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.search_hetong_itemview,this,true);
        txtJinE= (TextView) this.findViewById(R.id.txtJinE);
        txtName= (TextView) this.findViewById(R.id.txtName);
        txtTime= (TextView) this.findViewById(R.id.txTime);
    }
}
