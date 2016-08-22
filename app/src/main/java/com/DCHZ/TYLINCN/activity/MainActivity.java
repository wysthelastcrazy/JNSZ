package com.DCHZ.TYLINCN.activity;


import com.DCHZ.TYLINCN.DCHZApp;
import com.DCHZ.TYLINCN.R;
import com.igexin.sdk.PushManager;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends TabActivity implements OnClickListener{
	private final String TAB_1 = "tab_1";
	private final String TAB_2 = "tab_2";
	private final String TAB_3 = "tab_3";
	
	private RelativeLayout relaFirst;
	private RelativeLayout relaSecond;
	private RelativeLayout relaThird;
	private RelativeLayout relaForth;
	
//	private TextView[] txtArray;
	private ImageView[] imgArray;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		PushManager.getInstance().initialize(this.getApplicationContext());
		initTab();
	}

	private void initTab() {
		// TODO Auto-generated method stub
		TabHost mTabHost=getTabHost();
		TabHost.TabSpec tabHome=mTabHost.newTabSpec(TAB_1).setIndicator(TAB_1);
		Intent intent1=new Intent(this,FirstActivity.class);
		tabHome.setContent(intent1);
		try{
			mTabHost.addTab(tabHome);
		}catch(Exception ee){
			Log.e(TAB_1, ee.toString());
		}
		
		
		TabHost.TabSpec tabSecond=mTabHost.newTabSpec(TAB_2).setIndicator(TAB_2);
		Intent intent2=new Intent(this,ThreeActivity.class);
		tabSecond.setContent(intent2);
		try{
			mTabHost.addTab(tabSecond);
		}catch(Exception ee){
			Log.e(TAB_2, ee.toString());
		}
		
		TabHost.TabSpec tab3=mTabHost.newTabSpec(TAB_3).setIndicator(TAB_3);
		Intent intent3=new Intent(this,SettingActivity.class);
		tab3.setContent(intent3);
		try{
			mTabHost.addTab(tab3);
		}catch(Exception ee){
			Log.e(TAB_3, ee.toString());
		}
		
		
		
		
		
		this.relaFirst = (RelativeLayout) this.findViewById(R.id.rela_btn_first);
		this.relaFirst.setOnClickListener(this);
		
		this.relaSecond = (RelativeLayout) this.findViewById(R.id.rela_btn_second);
		this.relaSecond.setOnClickListener(this);
		
		this.relaThird = (RelativeLayout) this.findViewById(R.id.rela_btn_third);
		this.relaThird.setOnClickListener(this);
		
//		TextView txtFirst = (TextView) this.relaFirst.findViewById(R.id.btn_txt_first);
//		TextView txtSecond = (TextView) this.relaSecond.findViewById(R.id.btn_txt_second);
//		TextView txtThird = (TextView) this.relaThird.findViewById(R.id.btn_txt_third);
//		TextView txtForth = (TextView) this.relaForth.findViewById(R.id.btn_txt_forth);
//		TextView[] tempArray = {txtFirst,txtSecond,txtThird,txtForth};
//		this.txtArray = tempArray;
		
		ImageView imgFirst = (ImageView) this.relaFirst.findViewById(R.id.btn_img_first);
		ImageView imgSecond = (ImageView) this.relaSecond.findViewById(R.id.btn_img_second);
		ImageView imgThird = (ImageView) this.relaThird.findViewById(R.id.btn_img_third);
//		ImageView imgForth = (ImageView) this.relaForth.findViewById(R.id.btn_img_forth);
		ImageView[] imgTempArray = {imgFirst,imgSecond,imgThird};
		this.imgArray = imgTempArray;
		setIndex(0);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				if(view == this.relaFirst){
					setIndex(0);
				}else if(view == this.relaSecond){
					setIndex(1);
				}else if(view == this.relaThird){
					setIndex(2);
				}else if(view == this.relaForth){
					setIndex(3);
				}
	}
	
	private void setIndex(int index){
		int cNormal = getResources().getColor(R.color.tab_txt_color_grey);
		int cBlue = getResources().getColor(R.color.tab_txt_color_blue);
//		for(int i = 0;i < txtArray.length;i++){
//			TextView txtView = txtArray[i];
//			if(i == index){
//				txtView.setTextColor(cBlue);
//			}else{
//				txtView.setTextColor(cNormal);
//			}
//		}
		
		for(int i = 0;i < imgArray.length;i++){
			ImageView imgView = imgArray[i];
			if(i == index){
				imgView.setSelected(true);
			}else{
				imgView.setSelected(false);
			}
		}
		
		TabHost mTabHost = getTabHost();
		if (mTabHost.getCurrentTab() == index) {
			return;
		}
		mTabHost.setCurrentTab(index);
	}
}
