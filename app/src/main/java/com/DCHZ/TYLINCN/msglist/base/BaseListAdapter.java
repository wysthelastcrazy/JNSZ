package com.DCHZ.TYLINCN.msglist.base;

import java.util.ArrayList;
import java.util.HashMap;

import com.DCHZ.TYLINCN.commen.Global;
import com.DCHZ.TYLINCN.msglist.MsgPageBottomView;
import com.DCHZ.TYLINCN.msglist.listener.IRefreshListener;
import com.DCHZ.TYLINCN.util.MyLog;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;



public abstract class BaseListAdapter<T> extends BaseAdapter{
	private final String TAG = "BaseListAdapter";
	private ArrayList<T> mList;
	private final int TYPE_FOOTER = 0;
	private final int TYPE_ITEM_NORMAL = 1;
	private MsgPageBottomView mBottomView;
//	private PPageEntity mPageEntity;	//翻页的标记
	private IRefreshListener mRefreshListener;
	private int mType = ADAPTER_TYPE_NORMAL;
	
	public static final int ADAPTER_TYPE_NORMAL = 10;
	public static final int ADAPTER_TYPE_NO_BOTTOM = 11;
	
	 // 用来控制item的选中状况  
    private static HashMap<Integer, Boolean> isSelected;
	public BaseListAdapter(ArrayList<T> mList) {
		this.mList = mList;
		isSelected = new HashMap<Integer, Boolean>();
		initDate(mList);
	}
	 // 初始化isSelected的数据  
    private void initDate(ArrayList<T> mList) {  
        for (int i = 0; i < mList.size(); i++) {  
            getIsSelected().put(i, false);  
        }  
    }  
//    private void addData(ArrayList<T> mList){
//    	for (int i = this.mList.size()-mList.size(); i < this.mList.size(); i++) {  
//            getIsSelected().put(i, false);  
//        } 
//    }
	public void appendList(ArrayList<T> mList){
		if(this.mList == null){
			this.mList = mList;
		}else{
			this.mList.addAll(mList);
		}
		initDate(this.mList);
		notifyDataSetChanged();
	}
	public void reSetList(ArrayList<T> mList){
		this.mList = mList;
		initDate(mList);
		notifyDataSetChanged();
	}

	public void setRefreshListener(IRefreshListener mListener){
		this.mRefreshListener = mListener;
	}
	
	/***
	 * 设置分页标志位
	 * @param entity
	 */
//	public void updatePageFlag(PPageEntity entity){
//		this.mPageEntity = entity;
//		int hasNext = this.mPageEntity.hasNext;
//		if(hasNext > 0){
//			updateState(MsgPageBottomView.STATE_LISTVIEW_INIT);
//		}else{
//			updateState(MsgPageBottomView.STATE_LISTVIEW_NOMORE);
//		}
//	}

	/***
	 * 获取分页标志位
	 * @return
	 */
//	public PPageEntity getPageFlag(){
//		return this.mPageEntity;
//	}
//	
	public void setType(int mType){
		this.mType = mType;
	}
	
	@Override
	public int getCount() {
		MyLog.debug(TAG, "[getCount] list:"+mList.size());
		// TODO Auto-generated method stub
		if(this.mList != null){		//footer也计算在内
			if(mType == ADAPTER_TYPE_NO_BOTTOM){
				return this.mList.size();
			}else{
				return this.mList.size() + 1;
			}
		}
		return 0;
	}

	@Override
	public Object getItem(int pos) {
		MyLog.debug(TAG, "[getItem] pos:"+pos);
		// TODO Auto-generated method stub
		if(this.mList != null && mList.size() > 0 && pos < this.mList.size()){
			return this.mList.get(pos);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	private MsgPageBottomView getBottomView(){
		if(mBottomView == null){
			mBottomView = new MsgPageBottomView(Global.mContext);
			mBottomView.setListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if(mRefreshListener != null){
						mRefreshListener.bottomClick(mBottomView.getState());
					}
				}
			});
		}
		return mBottomView;
	}
	
	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		if(mType == ADAPTER_TYPE_NO_BOTTOM){
			return TYPE_ITEM_NORMAL;
		}else{
			if (position == getCount() - 1) {
				return TYPE_FOOTER;
			}
		}
		return TYPE_ITEM_NORMAL;
	}
	
	@Override
	public View getView(int pos, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		MyLog.debug(TAG, "[getView]  pos:"+pos);
		View mView = null;
		if(getItemViewType(pos) == TYPE_ITEM_NORMAL){
			T t = (T) getItem(pos);
			BaseItemView<T> mItemView = null;
			if(convertView == null){
				mItemView = getItemView(t);
			}else{
				mItemView = (BaseItemView) convertView;
			}
			mItemView.setPos(pos);
			if(getIsSelected().get(pos)!=null){
				mItemView.setIsSelected(getIsSelected().get(pos));
			}else{
//				mItemView.setIsSelected(false);
			}
			mItemView.setMsg(t);
			mView = mItemView;
		}else{	//footer item
//			MsgPageBottomView bottomView = getBottomView();
//			mView = bottomView;
		}
		return mView;
	}
	
	/***
	 * 更新底部状态
	 * @param state
	 */
	public void updateState(int state){
		if(mBottomView != null){
			mBottomView.updateState(state);
		}
	}
	public  HashMap<Integer, Boolean> getIsSelected() {  
        return isSelected;  
    }  
  
    public  void setIsSelected(HashMap<Integer, Boolean> isSelected) {  
    	BaseListAdapter.isSelected = isSelected;  
    }  
	public abstract BaseItemView<T> getItemView(T t);
}
