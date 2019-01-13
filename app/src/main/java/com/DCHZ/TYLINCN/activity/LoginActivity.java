package com.DCHZ.TYLINCN.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.commen.Global;
import com.DCHZ.TYLINCN.entity.ReadPwdEntity;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspLoginEntity;
import com.DCHZ.TYLINCN.http.rsp.RspUpDateEntity;
import com.DCHZ.TYLINCN.util.IntentUtils;
import com.DCHZ.TYLINCN.util.SharePreLoginUtil;
import com.DCHZ.TYLINCN.util.SharePreReadPwdUtil;
/****
 * 登录页
 * @author wys
 *
 */
public class LoginActivity extends BaseNormalActivity implements OnClickListener{
	private final int FLAG_LOAGIN=0x100;
	private final int FLAG_UPDATE=0x101;
	private EditText etUserName;
	private EditText etPwd;
	private Button btnLogin;
	private ImageView imgReadPwd;
	private List<Integer> mReqList = new ArrayList<Integer>();
	private boolean ischecked;
	private TextView server_adress;
	private TextView text_help;
	private TextView text_update;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		registMsgRecevier(EventCommon.EVENT_LOGIN);
		registMsgRecevier(EventCommon.EVENT_UPDATE);
		registMsgRecevier(EventCommon.EVENT_PUSH);
		int seq=ProtocalManager.getInstance().Update();
		mReqList.add(seq);
		initLayout();
	}
	
	private void initLayout() {
		// TODO Auto-generated method stub
		etUserName=(EditText) findViewById(R.id.edit_userName);
		etPwd=(EditText) findViewById(R.id.edit_pwd);
		btnLogin=(Button) findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(this);
		imgReadPwd=(ImageView) findViewById(R.id.img_switch);
		imgReadPwd.setOnClickListener(this);
		text_update=(TextView) findViewById(R.id.text_update);
		text_update.setOnClickListener(this);
		ReadPwdEntity entity=SharePreReadPwdUtil.loadUserInfo();
		if(entity!=null){
			ischecked=entity.isChecked;
			imgReadPwd.setSelected(ischecked);
			if(ischecked){
				etUserName.setText(entity.userName);
				etPwd.setText(entity.pwd);
			}
		}
		server_adress=(TextView) findViewById(R.id.server_adress);
		server_adress.setOnClickListener(this);
		text_help=(TextView) findViewById(R.id.text_help);
		text_help.setOnClickListener(this);
	}

	@Override
	public void handleReceiveMsg(int eventId, int seqNo, Object obj) {
		// TODO Auto-generated method stub
		if(eventId==EventCommon.EVENT_LOGIN){
			if(mReqList.remove(Integer.valueOf(seqNo))){
				if(obj instanceof RspLoginEntity){
					RspLoginEntity rsp=(RspLoginEntity) obj;
					Message msg=Message.obtain();
					msg.what=FLAG_LOAGIN;
					msg.obj=rsp;
					sendMsg(msg);
				}
			}
		}else if(eventId==EventCommon.EVENT_UPDATE){
			if(mReqList.remove(Integer.valueOf(seqNo))){
				if(obj instanceof RspUpDateEntity){
					RspUpDateEntity rsp=(RspUpDateEntity) obj;
					Message msg=Message.obtain();
					msg.what=FLAG_UPDATE;
					msg.obj=rsp;
					sendMsg(msg);
				}
			}
		}
	}
	@Override
	protected void handleMsg(Message msg) {
		hideLoadingDialog();
		// TODO Auto-generated method stub
		int what=msg.what;
		switch (what) {
		case FLAG_LOAGIN:
			RspLoginEntity rsp=(RspLoginEntity) msg.obj;
			if(rsp!=null&&rsp.isSucc){
				showToast("登录成功！");
//				showToast(Global.clientid);
				SharePreLoginUtil.saveLoginInfo(rsp.mEntity);
				IntentUtils.startMainActivity(this);
				
				ProtocalManager.getInstance().reqPush(rsp.mEntity.YHID, Global.clientid);
			}else{
				showToast("网络异常！");
			}
			break;
		case FLAG_UPDATE:
			RspUpDateEntity rsp1=(RspUpDateEntity) msg.obj;
			if(rsp1!=null&&rsp1.isSucc){
				String str=rsp1.mEntity.AppVersionInfo.get(1).Android;
				if(!"V2.5".equals(str)){
					text_update.setVisibility(View.VISIBLE);
				}
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==btnLogin){
			String userName=etUserName.getText().toString();
			String pwd=etPwd.getText().toString();
			ReadPwdEntity entity=new ReadPwdEntity();
			entity.isChecked=ischecked;
			entity.pwd=pwd;
			entity.userName=userName;
			SharePreReadPwdUtil.saveUserInfo(entity);
			int seq= ProtocalManager.getInstance().getLogin(userName, pwd);
			mReqList.add(seq);
			showLoading("正在登录...");
		}else if(view==imgReadPwd){
			ischecked=!ischecked;
			imgReadPwd.setSelected(ischecked);
		}else if(view==server_adress){
			IntentUtils.startXiuGaiIpActivity(this);
		}else if(view==text_help){
			IntentUtils.startHelpActivity(this);
		}else if(view==text_update){
			   Uri  uri = Uri.parse("http://www.dchzsoft.com/jnapk.html");
			   Intent  intent = new  Intent(Intent.ACTION_VIEW, uri);
			   startActivity(intent);
		}
	}

}
