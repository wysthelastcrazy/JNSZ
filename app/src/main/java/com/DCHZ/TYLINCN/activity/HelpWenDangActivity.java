package com.DCHZ.TYLINCN.activity;

import com.DCHZ.TYLINCN.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class HelpWenDangActivity extends Activity implements OnClickListener {
	private TextView text_wendang;
	private TextView text_back;
	private TextView text_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wendang);
		text_wendang=(TextView) findViewById(R.id.text_wendang);
		text_title=(TextView) findViewById(R.id.text_title);
		text_back=(TextView) findViewById(R.id.text_back);
		text_back.setOnClickListener(this);
		
		String type=getIntent().getStringExtra("type");
		if("login".equals(type)){
			text_wendang.setText("1. 初次登录时请填写服务器地址:mobile.mis.tylin.com.cn:9080。\n2."
					+ "输入账号和密码即可登录。");
			text_title.setText("如何登陆");
		}else if("daiban".equals(type)){
			text_wendang.setText("1. 点击列表中需要办理的业务流程，查看业务详情。\n2. 下滑到“您的意见”可填写审批意见。\n3. 点击“提交方式”选择同意或退回。\n4. 点击“下一环节”选择审批人。\n5. 点击“确定”后提交到下一环节审批。");
			text_title.setText("代办处理");
		}else if("gongshi".equals(type)){
			text_wendang.setText("1. 点击“工时”，将会显示工时审批列表。\n2. 选出需要审批的工时，点击“同意”或“退回”。");
			text_title.setText("工时管理");
		}
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==text_back){
			finish();
		}
	}
}
