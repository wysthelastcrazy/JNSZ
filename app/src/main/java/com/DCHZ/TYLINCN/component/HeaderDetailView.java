package com.DCHZ.TYLINCN.component;


import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.commen.Global;
import com.DCHZ.TYLINCN.entity.PDaiBanEntity;
import com.DCHZ.TYLINCN.listener.IHeaderClickListener;
import com.common.util.DateUtil;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;
public class HeaderDetailView extends RelativeLayout implements OnClickListener{
	private TextView text_title;
	private TextView text_header_back;
	private TextView text_item1_key;
	private TextView text_item1_value;
	private TextView text_item2_key;
	private TextView text_item2_value;
	private TextView text_item3_key;
	private TextView text_item3_value;
	private TextView text_item4_key;
	private TextView text_item4_value;
	private CheckBox item_cb;
	private IHeaderClickListener mListener;
	public HeaderDetailView(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}
	public HeaderDetailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}
	public HeaderDetailView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
		// TODO Auto-generated constructor stub
	}
	private void init() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) Global.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.header_detail,this,true);
		text_title=(TextView) this.findViewById(R.id.text_title);
		text_header_back=(TextView) this.findViewById(R.id.text_header_back);
		this.text_header_back.setOnClickListener(this);
		text_item1_key=(TextView) this.findViewById(R.id.text_item1_key);
		text_item1_value=(TextView) this.findViewById(R.id.text_item1_value);
		text_item2_key=(TextView) this.findViewById(R.id.text_item2_key);
		text_item2_value=(TextView) this.findViewById(R.id.text_item2_value);
		text_item3_key=(TextView) this.findViewById(R.id.text_item3_key);
		text_item3_value=(TextView) this.findViewById(R.id.text_item3_value);
		text_item4_key=(TextView) this.findViewById(R.id.text_item4_key);
		text_item4_value=(TextView) this.findViewById(R.id.text_item4_value);
		item_cb=(CheckBox) this.findViewById(R.id.item_cb);
		
		item_cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					text_item4_value.setText("合格");
				}else{
					text_item4_value.setText("不合格");
				}
			}
		});
	}
	
	public void showCheckBox(){
		item_cb.setVisibility(View.VISIBLE);
	}
	public void setCheckBox(boolean isChecked){
		item_cb.setChecked(isChecked);
	}
	public void hideCheckBox(){
		item_cb.setVisibility(View.GONE);
	}
	public String getValue(){
		String value=text_item4_value.getText().toString();
		return value;
	}
	/**
	 * set数据
	 */
	public void setData(PDaiBanEntity mEntity){
		String str=mEntity.LCID;
		initKey(str);
		text_item1_value.setText(mEntity.ShenQingRen);
		String date=mEntity.BLAcceptDate;
		if (!TextUtils.isEmpty(date)) {
			date = date.split(" ")[0];
		}
		text_item3_value.setText(date);
		if(!Common.FENBAO.equals(str)){
			text_item4_value.setText(mEntity.YWTypeValue);
		}
	}
	
	public void setItem(String str){
		text_item2_value.setText(str);
	}
	public void setItem3(String str){
		text_item3_value.setText(str);
	}
	public void setValue(String value1,String value2,String value3,String value4){
		text_item1_value.setText(value1);
		text_item2_value.setText(value2);
		text_item3_value.setText(value3);
		text_item4_value.setText(value4);
	}
	public void setClickListener(IHeaderClickListener mListener){
		this.mListener=mListener;
	}
	public void setValue4(String value){
		text_item4_value.setText(value);
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==text_header_back){
			if(mListener!=null){
				mListener.backClick();
			}
		}
	}
	
	public void initKey(String str){
		//费用报销单
		if(Common.FEIYONG.equals(str)){
			text_title.setText("费用报销单");
			
			text_item1_key.setText("报 销 人：");
			text_item2_key.setText("报销部门：");
			text_item3_key.setText("报销时间：");
			text_item4_key.setText("报销金额：");
		//请假申请
		}else if(Common.QINGJIA.equals(str)){
			text_title.setText("请假申请");
			
			text_item1_key.setText("请 假 人：");
			text_item2_key.setText("所属部门：");
			text_item3_key.setText("申请时间：");
			text_item4_key.setText("请假天数：");
			//借款单
		}else if(Common.JIEKUAN.equals(str)){
			text_title.setText("借款审批");
			
			text_item1_key.setText("申  请  人：");
			text_item2_key.setText("申请部门：");
			text_item3_key.setText("申请时间：");
			text_item4_key.setText("借款金额：");
			//应收合同评审
		}else if(Common.YINGSHOUHT.equals(str)){
			text_title.setText("应收合同评审");
			
			text_item1_key.setText("记 录 人：");
			text_item2_key.setText("项目经理：");
			text_item3_key.setText("登记时间：");
			text_item4_key.setText("合同金额：");
			//应付合同评审
		}else if(Common.YINGFUHT.equals(str)){
			text_title.setText("分包合同评审");
			
			text_item1_key.setText("记 录 人：");
			text_item2_key.setText("项目经理：");
			text_item3_key.setText("登记时间：");
			text_item4_key.setText("合同金额：");
			//发票开具
		}else if(Common.FAPIAO.equals(str)){
			text_title.setText("发票开具申请");
			
			text_item1_key.setText("申 请 人：");
			text_item2_key.setText("所属部门：");
			text_item3_key.setText("申请时间：");
			text_item4_key.setText("金额：");
			//投标或履约保证金
		}else if(Common.TOUBIAO.equals(str)){
			text_title.setText("投标保证金申请");
			
			text_item1_key.setText("申 请 人：");
			text_item2_key.setText("申请部门：");
			text_item3_key.setText("申请时间：");
			text_item4_key.setText("申请金额：");
			//分包方评审
		}else if(Common.FENBAO.equals(str)){
			text_title.setText("合格分包方评审");
			
			text_item1_key.setText("登记人：");
			text_item2_key.setText("所属部门：");
			text_item3_key.setText("登记时间：");
			text_item4_key.setText("是否合格：");
		}else if(Common.GAIZHANG.equals(str)){
			text_title.setText("盖章申请");
			
			text_item1_key.setText("申请人：");
			text_item2_key.setText("申请部门：");
			text_item3_key.setText("项目编号：");
			text_item4_key.setText("盖章类型：");
		}else if(Common.FAREN.equals(str)){
			text_title.setText("法人授权申请");
			
			text_item1_key.setText("被授权人：");
			text_item2_key.setText("所属部门：");
			text_item3_key.setText("登记时间：");
			text_item4_key.setText("授权期限：");
		}else if(Common.XIANGMUXIADA.equals(str)){
			text_title.setText("项目下达");
			
			text_item1_key.setText("审定人：");
			text_item2_key.setText("管理级别：");
			text_item3_key.setText("项目编号：");
			text_item4_key.setText("项目负责：");
		}else if(Common.GONGCHENG.equals(str)){
			text_title.setText("工程拨款申请");
			
			text_item1_key.setText("申请人：");
			text_item2_key.setText("申请部门：");
			text_item3_key.setText("申请时间：");
			text_item4_key.setText("本次支付：");
		}else if(Common.GONGZHANGJIECHU.equals(str)){
			text_title.setText("公章借出申请");

			text_item1_key.setText("申请人：");
			text_item2_key.setText("申请部门：");
			text_item3_key.setText("借出时间：");
			text_item4_key.setText("归还时间：");
		}else if(Common.GUDINGZICHAN.equals(str)){
			text_title.setText("固定资产购置申请");

			text_item1_key.setText("申请人：");
			text_item2_key.setText("申请部门：");
			text_item3_key.setText("申请日期：");
			text_item4_key.setText("购置部门：");
		}else if(Common.DIZHIYIHAOPIN.equals(str)){
			text_title.setText("低值易耗品购置申请");

			text_item1_key.setText("申请人：");
			text_item2_key.setText("申请部门：");
			text_item3_key.setText("申请日期：");
			text_item4_key.setText("购置部门：");
		}else if(Common.TOUBIAOFEIYONG.equals(str)){
			text_title.setText("投标费用审批");

			text_item1_key.setText("申请人：");
			text_item2_key.setText("申请部门：");
			text_item3_key.setText("申请日期：");
			text_item4_key.setVisibility(View.INVISIBLE);
		}else if(Common.CHENGBAOFEI.equals(str)){
			text_title.setText("投标费用审批");

			text_item1_key.setText("申请人：");
			text_item2_key.setText("申请部门：");
			text_item3_key.setText("申请日期：");
			text_item4_key.setVisibility(View.INVISIBLE);
		}
		else{
			//TODO ：错误数据处理
		}
	}
}
