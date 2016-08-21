package com.DCHZ.TYLINCN.component;


import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.commen.Global;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * listView为空时显示的View
 * @author yas
 *
 */
public class ListViewEmptyView extends RelativeLayout{
	public static final int TYPE_COURSE=1;	//关注课程为空
	public static final int TYPE_SCHOOL=2;	//关注学校为空
	public static final int TYPE_ENROLL=3;	//报名信息为空
	public static final int TYPE_LOAN=4;		//贷款信息为空
	public static final int TYPE_SOURR_SCHOOL=5;	//附近学校列表为空
	public static final int TYPE_SOURR_COURSE=6;	//搜索课程列表为空
	public static final int TYPE_COMMENT=7;	//评论为空
	private ImageView image_empty;
	private TextView text_empty;
	private Context mContext;
	public ListViewEmptyView(Context context) {
		super(context);
		mContext=context;
		// TODO Auto-generated constructor stub
		init();
	}
	public ListViewEmptyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext=context;
		// TODO Auto-generated constructor stub
		init();
	}
	public ListViewEmptyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext=context;
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		LayoutInflater li=(LayoutInflater) Global.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.list_empty, this,true);
		image_empty=(ImageView) this.findViewById(R.id.image_empty);
		text_empty=(TextView) this.findViewById(R.id.text_empty);
	}
	
	//设置类型
	public void setType(int type){
		Drawable drawable=null;
		String str=null;
		switch (type) {
		case TYPE_COURSE:
//			drawable=Global.getContext().getResources().getDrawable(R.drawable.mycenter_class);
//			str=Global.getContext().getResources().getString(R.string.myFocus_emptyCourse);
			break;
		case TYPE_SCHOOL:
//			drawable=Global.getContext().getResources().getDrawable(R.drawable.mycenter_school);
//			str=Global.getContext().getResources().getString(R.string.myFocus_emptySchool);
			break;
		case TYPE_ENROLL:
//			drawable=Global.getContext().getResources().getDrawable(R.drawable.mycenter_class);
//			str=Global.getContext().getResources().getString(R.string.enroll_empty);
			str="您暂时没有权限查看";
			break;
		case TYPE_LOAN:
//			drawable=Global.getContext().getResources().getDrawable(R.drawable.mycenter_jk);
//			str=Global.getContext().getResources().getString(R.string.userInfo_loan_empty);
			break;
		case TYPE_SOURR_COURSE:
//			drawable=Global.getContext().getResources().getDrawable(R.drawable.mycenter_class);
//			str=Global.getContext().getResources().getString(R.string.course_list_empty);
			break;
		case TYPE_SOURR_SCHOOL:
//			drawable=Global.getContext().getResources().getDrawable(R.drawable.mycenter_school);
//			str=Global.getContext().getResources().getString(R.string.school_list_empty);
			break;
		case TYPE_COMMENT:
//			drawable=Global.getContext().getResources().getDrawable(R.drawable.mycenter_comment);
			str="暂无数据";
			break;
		default:
			break;
		}
		image_empty.setImageDrawable(drawable);
		text_empty.setText(str);
	}
}
