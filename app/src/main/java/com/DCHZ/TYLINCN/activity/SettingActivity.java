package com.DCHZ.TYLINCN.activity;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.util.IntentUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class SettingActivity extends Activity implements OnClickListener {
	private LinearLayout layout_type;
	private LinearLayout layout_tuichu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		layout_type=(LinearLayout) findViewById(R.id.layout_type);
		layout_type.setOnClickListener(this);
		layout_tuichu=(LinearLayout) findViewById(R.id.layout_tuichu);
		layout_tuichu.setOnClickListener(this);
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==layout_type){
			IntentUtils.startXiuGaiIpActivity(this);
		}else if(view==layout_tuichu){
			IntentUtils.startLonginActivity(this);
			finish();
		}
	}
}
