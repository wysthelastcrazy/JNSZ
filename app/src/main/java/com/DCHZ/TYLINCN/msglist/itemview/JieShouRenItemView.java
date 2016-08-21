package com.DCHZ.TYLINCN.msglist.itemview;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.adapter.JieShouRenListAdapter;
import com.DCHZ.TYLINCN.commen.Global;
import com.DCHZ.TYLINCN.entity.VJieShouRenEntity;
import com.DCHZ.TYLINCN.entity.VJieShouRenListEntity;
import com.DCHZ.TYLINCN.listener.IGongShiShenPiClickListener;
import com.DCHZ.TYLINCN.msglist.MsgPage;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.util.MyLog;

public class JieShouRenItemView extends BaseItemView<VJieShouRenEntity>{

	private VJieShouRenEntity mEntity;
	private MsgPage mMsgPage;
	private JieShouRenListAdapter mAdapter;
	private HashMap<Integer, Boolean> isSelected;
	public JieShouRenItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setMsg(VJieShouRenEntity t) {
		// TODO Auto-generated method stub
		mEntity=t;
		if(t!=null){
			ArrayList<VJieShouRenListEntity> arr=new ArrayList<VJieShouRenListEntity>();
			for(int i=0;i<5;i++){
				VJieShouRenListEntity entity=new VJieShouRenListEntity();
				entity.name="data"+i;
				arr.add(entity);
			}
			MyLog.debug(TAG, "[setMsg] arr:"+arr.size());
			if(mAdapter==null){
				mAdapter=new JieShouRenListAdapter(arr);
			}
			mAdapter.setType(BaseListAdapter.ADAPTER_TYPE_NO_BOTTOM);
			
			mAdapter.setLisntener(mListener);
			if(isSelected!=null&&isSelected.size()>0){
				mAdapter.setIsSelected(isSelected);
			}
//		    isSelected=mAdapter.getIsSelected();
			mMsgPage.setListAdapter(mAdapter);
			mMsgPage.setEnablePullDown(false);
//			int totalHeight = 0;  
//			  for (int i = 0; i < mAdapter.getCount(); i++) {  
//			   View listItem = mAdapter.getView(i, null, mMsgPage);  
//			   listItem.measure(0, 0);  
//			   totalHeight += listItem.getMeasuredHeight();  
//			  }  
//			  ViewGroup.LayoutParams params = mMsgPage.getLayoutParams();  
//			  params.height = totalHeight;
//			  mMsgPage.setLayoutParams(params); 
		}
	}

	@Override
	public VJieShouRenEntity getMsg() {
		// TODO Auto-generated method stub
		return mEntity;
	}

	@Override
	public void onInflate() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
	   li.inflate(com.DCHZ.TYLINCN.R.layout.jieshouren_item, this,
				true);
	   mMsgPage=(MsgPage) this.findViewById(R.id.mMsgPage);
	}

	private IGongShiShenPiClickListener mListener = new IGongShiShenPiClickListener() {

		@Override
		public void selectedListener(int pos) {
			// TODO Auto-generated method stub
			mAdapter.getIsSelected().put(pos, true);
			isSelected=mAdapter.getIsSelected();
		}

		@Override
		public void cancelSelectListener(int pos) {
			// TODO Auto-generated method stub
			mAdapter.getIsSelected().put(pos, false);
			isSelected=mAdapter.getIsSelected();
		}
	};
}
