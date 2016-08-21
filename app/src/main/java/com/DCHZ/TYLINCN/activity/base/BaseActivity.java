package com.DCHZ.TYLINCN.activity.base;


import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.dialog.MLoadingDialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

/***
 * Activity
 */
public class BaseActivity extends Activity{
	protected final String TAG = getClass().getSimpleName();
	
	private static Toast toast;
	private MLoadingDialog mLoadingDialog;
	private int width;
	private int height;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

		  width = wm.getDefaultDisplay().getWidth();
		  height = wm.getDefaultDisplay().getHeight();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		hideLoadingDialog();
	}
	
	protected void showLoading(String msg){
		showLoading(msg,true);
	}

	protected void showLoading(){
//		String str = getResources().getString(R.string.dialog_loading_loaddata_tips);
		String str="数据正在加载中...";
		showLoading(str);
	}
	
	protected void showLoading(String msg,boolean isCancle){
		hideLoadingDialog();
		mLoadingDialog = new MLoadingDialog(this,R.style.MyDialogBg);
		mLoadingDialog.setCanceledOnTouchOutside(isCancle);
		mLoadingDialog.show();
		mLoadingDialog.setTips(msg);
	}
	
	protected void hideLoadingDialog(){
		if(mLoadingDialog != null){
			mLoadingDialog.dismiss();
			mLoadingDialog = null;
		}
	}
	
	protected void showToast(String str){
		if(toast != null){
			toast.cancel();
			toast = null;
		}
		toast=new Toast(this);
//		View view=getLayoutInflater().inflate(R.layout.toast,null);
//		TextView textView=(TextView) view.findViewById(R.id.text);
//		textView.setText(str);
//		toast.setView(view);
//		toast.setGravity(Gravity.TOP, 0, height/4+20);
		toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
		toast.show();
	}

	/****
	 * Toast
	 * @param str
	 * @param isLong
	 */
	protected void showToast(String str,boolean isLong){
		if(toast != null){
			toast.cancel();
			toast = null;
		}
		toast=new Toast(this);
//		View view=getLayoutInflater().inflate(R.layout.toast, (ViewGroup)findViewById(R.id.toast));
//		TextView textView=(TextView) view.findViewById(R.id.text);
//		textView.setText(str);
//		toast.setView(view);
//		toast.setGravity(Gravity.TOP, 0, height/4+20);
//		toast.setDuration(Toast.LENGTH_LONG);
		toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
		toast.show();
	}
	
//	public void showToast(String str,boolean succ){
//		showToast(succ,str);
//	}
//	
//	private void showToast(boolean isSucc,String msg){
//		if(toast != null){
//			toast.cancel();
//			toast = null;
//		}
//		toast = new Toast(this);
//		toast.setDuration(Toast.LENGTH_SHORT);
//		toast.setGravity(Gravity.CENTER, 0, 250);
//		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View layout = inflater.inflate(R.layout.tw_toast_layout,null);
//		ImageView imageView = (ImageView) layout.findViewById(R.id.toast_imageview);
//		TextView textView = (TextView) layout.findViewById(R.id.toast_textview);
//		if(isSucc){
//			imageView.setBackgroundResource(R.drawable.tw_setting_selected);
//			textView.setText(msg);
//		}else{
//			imageView.setBackgroundResource(R.drawable.tw_share_result_error);
//			textView.setText(msg);
//		}
//		toast.setView(layout);
//		toast.show();
//	}
}
