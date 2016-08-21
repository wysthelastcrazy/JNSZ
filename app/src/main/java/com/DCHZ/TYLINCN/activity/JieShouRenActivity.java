package com.DCHZ.TYLINCN.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.AbsListView.LayoutParams;
import android.widget.TextView;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseActivity;
import com.DCHZ.TYLINCN.entity.VJieShouRenEntity;
import com.DCHZ.TYLINCN.listener.IItemCheckedListener;
import com.DCHZ.TYLINCN.msglist.itemview.JieShouRenItemView1;
import com.DCHZ.TYLINCN.util.IntentUtils;
import com.DCHZ.TYLINCN.util.MyLog;

public class JieShouRenActivity extends BaseActivity {
	private ArrayList<VJieShouRenEntity> mList;
	private ScrollView mScrollView;
	private ArrayList<JieShouRenItemView1> mViewList;
	private String name;
	private String id;
	private TextView text_back;
	private int number=0;
	private boolean isEnd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jieshouren);
		initExtras();
		initLayout();
	}

	private void initLayout() {
		// TODO Auto-generated method stub
		mViewList=new ArrayList<JieShouRenItemView1>();
		
		text_back=(TextView) findViewById(R.id.text_back);
		text_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getName();
				if(number>1){
					showToast("只能选一个部门");
					number=0;
				}else{
					Intent intent=new Intent(); 
					if(!isEnd){
						if(!TextUtils.isEmpty(name)){
							intent.putExtra("name", name);
						}else{
							intent.putExtra("name", "下一环节");
						}
					}else{
						intent.putExtra("name", "结束");
					}
					if(!TextUtils.isEmpty(id)){
						intent.putExtra("id", id);
					}
					setResult(Activity.RESULT_OK, intent);
					finish();
				}
			}
		});
		
		mScrollView = (ScrollView) this.findViewById(R.id.mScrollView);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		layout.setLayoutParams(params);
		for (int i = 0; i < mList.size(); i++) {
			JieShouRenItemView1 itemView = new JieShouRenItemView1(this);
			itemView.setData(mList.get(i),i);

			itemView.showTitle();
			
			String title="";
			if (mList.get(i).type == VJieShouRenEntity.TYPE_HT) {
				title=mList.get(i).HTInfoEntity.nodeName;
				itemView.setTitle(title);
				if("结束".equals(title)){
					isEnd=true;
				}
			} else {
				title=mList.get(i).TJInfoEntity.nodeName;
				itemView.setTitle(title);
				if("结束".equals(title)){
					isEnd=true;
				}
			}
			itemView.setCheckedListener(mCheckedListener);
			layout.addView(itemView);
			mViewList.add(itemView);
		}
		mScrollView.addView(layout);
	}

	private void initExtras() {
		mList = new ArrayList<VJieShouRenEntity>();
		Intent intent = getIntent();
		mList = (ArrayList<VJieShouRenEntity>) intent
				.getSerializableExtra(IntentUtils.KEY_ENTITY);
	}
		private void getName(){
				for(int i=0;i<mViewList.size();i++){
					if(!TextUtils.isEmpty(mViewList.get(i).getName())){
						name=mViewList.get(i).getName();
						id=mViewList.get(i).getIds();
						number++;
					}
				}
	}
	private IItemCheckedListener mCheckedListener=new IItemCheckedListener() {
		@Override
		public void onlyOn(int pos,int index) {
			// TODO Auto-generated method stub
			for(int i=0;i<mViewList.size();i++){
				if(i==index){
					mViewList.get(i).cancelOther(pos);
					mViewList.get(i).getAdapter().notifyDataSetChanged();
					MyLog.debug(TAG, "[onlyOn]  adapter: "+mViewList.get(i).getAdapter().toString());
				}else{
//					mViewList.get(i).cancelChecked();
//					mViewList.get(i).getAdapter().notifyDataSetChanged();
				}
			}
		}
	};
}
