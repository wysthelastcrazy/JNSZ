package com.DCHZ.TYLINCN.activity;

import com.DCHZ.TYLINCN.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class HelpActivity extends Activity implements OnClickListener {
	private TextView text_login;
	private TextView text_daiban;
	private TextView text_gongshi;
	private TextView text_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		text_login=(TextView) findViewById(R.id.text_login);
		text_login.setOnClickListener(this);
		text_daiban=(TextView) findViewById(R.id.text_daiban);
		text_daiban.setOnClickListener(this);
		text_gongshi=(TextView) findViewById(R.id.text_gongshi);
		text_gongshi.setOnClickListener(this);
		
		text_back=(TextView) findViewById(R.id.text_back);
		text_back.setOnClickListener(this);
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==text_login){
			Intent intent=new Intent(HelpActivity.this, HelpWenDangActivity.class);
			intent.putExtra("type", "login");
			startActivity(intent);
		}else if(view==text_daiban){
			Intent intent=new Intent(HelpActivity.this, HelpWenDangActivity.class);
			intent.putExtra("type", "daiban");
			startActivity(intent);
		}else if(view==text_gongshi){
			Intent intent=new Intent(HelpActivity.this, HelpWenDangActivity.class);
			intent.putExtra("type", "gongshi");
			startActivity(intent);
		}else if(view==text_back){
			finish();
		}
	}
}
