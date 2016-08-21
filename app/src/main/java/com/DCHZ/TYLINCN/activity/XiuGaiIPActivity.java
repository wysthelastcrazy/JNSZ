package com.DCHZ.TYLINCN.activity;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.util.SharePreIPUtil;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class XiuGaiIPActivity extends Activity {
	private EditText editText_ip;
	private Button btn_ok;
	private TextView text_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xiugai_ip);
		editText_ip=(EditText) findViewById(R.id.editText_ip);
		String ip=SharePreIPUtil.loadIpInfo();
		if(!TextUtils.isEmpty(ip)){
			editText_ip.setText(ip);
		}else{
			editText_ip.setText("60.208.75.182:9080");
//			editText_ip.setText("mobile.mis.tylin.com.cn:9080");
		}
		
		btn_ok=(Button) findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String ip=editText_ip.getText().toString();
				SharePreIPUtil.saveIpInfo(ip);
				finish();
			}
		});
		
		text_back=(TextView) findViewById(R.id.text_back);
		text_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
