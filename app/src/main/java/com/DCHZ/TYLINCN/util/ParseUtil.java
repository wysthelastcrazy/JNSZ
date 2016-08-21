package com.DCHZ.TYLINCN.util;

import java.util.ArrayList;

import android.text.TextUtils;

import com.DCHZ.TYLINCN.entity.PDetailHTInfoEntity;
import com.DCHZ.TYLINCN.entity.PDetailTJInfoInfoEntity;
import com.DCHZ.TYLINCN.entity.PJiDuHeTongItemEntity;
import com.DCHZ.TYLINCN.entity.PJiDuShouKuanItemEntity;
import com.DCHZ.TYLINCN.entity.VJieShouRenEntity;
import com.DCHZ.TYLINCN.entity.VThirdItemEntity;


/**
 * 解析工具类
 * @author yas
 */
public class ParseUtil {
	public static ArrayList<VThirdItemEntity> getThirdList(ArrayList<PJiDuHeTongItemEntity> srcList){
		ArrayList<VThirdItemEntity> mList=new ArrayList<VThirdItemEntity> ();
		if(srcList!=null&&srcList.size()>0){
			int ShiJiTotal=0;
			int YuSuanTotal=0;
			VThirdItemEntity mEntity=new VThirdItemEntity();
			PJiDuHeTongItemEntity entity=new PJiDuHeTongItemEntity();
			entity.BMName="部门";
			entity.NianDuHeTongYuSuan="预算";
			entity.NianDuHeTongShiJi="实际";
			entity.WanChengBiLi="比例";
			mEntity.mEntity=entity;
			mEntity.type=VThirdItemEntity.TYPE_HeTong;
			mList.add(mEntity);
			
			for(int i=0;i<srcList.size();i++){
				mEntity=new VThirdItemEntity();
				mEntity.mEntity=srcList.get(i);
				mEntity.type=VThirdItemEntity.TYPE_HeTong;
				mList.add(mEntity);
				if (!"总部小计".equals(srcList.get(i).BMName)
						|| !"子公司小计".equals(srcList.get(i).BMName)
						|| !"分公司小计".equals(srcList.get(i).BMName)) {
					String str = srcList.get(i).NianDuHeTongShiJi;
					if(!TextUtils.isEmpty(str)){
						ShiJiTotal = ShiJiTotal + Integer.valueOf(str);
					}
					
					String str1 = srcList.get(i).NianDuHeTongYuSuan;
					if(!TextUtils.isEmpty(str1)){
						YuSuanTotal = YuSuanTotal + Integer.valueOf(str1);
					}
				}
			}
			
			mEntity=new VThirdItemEntity();
		    entity=new PJiDuHeTongItemEntity();
			entity.BMName="总计";
			entity.NianDuHeTongYuSuan=YuSuanTotal+"";
			entity.NianDuHeTongShiJi=ShiJiTotal+"";
			entity.WanChengBiLi="";
			mEntity.mEntity=entity;
			mEntity.type=VThirdItemEntity.TYPE_HeTong;
			mList.add(mEntity);
		}
		return mList;
	}
	
	public static ArrayList<VThirdItemEntity> getThirdList(ArrayList<PJiDuHeTongItemEntity> srcList,String BMID){
		ArrayList<VThirdItemEntity> mList=new ArrayList<VThirdItemEntity> ();
		if(srcList!=null&&srcList.size()>0){
			int ShiJiTotal=0;
			int YuSuanTotal=0;
			VThirdItemEntity mEntity=new VThirdItemEntity();
			PJiDuHeTongItemEntity entity=new PJiDuHeTongItemEntity();
			entity.BMName="部门";
			entity.NianDuHeTongYuSuan="预算";
			entity.NianDuHeTongShiJi="实际";
			entity.WanChengBiLi="比例";
			mEntity.mEntity=entity;
			mEntity.type=VThirdItemEntity.TYPE_HeTong;
			mList.add(mEntity);
			
			for(int i=0;i<srcList.size();i++){
				if(srcList.get(i).BMID.equals(BMID)){
					mEntity=new VThirdItemEntity();
					mEntity.mEntity=srcList.get(i);
					mEntity.type=VThirdItemEntity.TYPE_HeTong;
					mList.add(mEntity);
				}
			}
		}
		return mList;
	}
	public static ArrayList<VThirdItemEntity> getThirdList1(ArrayList<PJiDuShouKuanItemEntity> srcList){
		ArrayList<VThirdItemEntity> mList=new ArrayList<VThirdItemEntity> ();
		if(srcList!=null&&srcList.size()>0){
			int ShiJiTotal=0;
			int YuSuanTotal=0;
			VThirdItemEntity mEntity=new VThirdItemEntity();
			PJiDuShouKuanItemEntity entity=new PJiDuShouKuanItemEntity();
			entity.BMName="部门";
			entity.NianDuShouKuanYuSuan="预算";
			entity.NianDuShouKuanShiJi="实际";
			entity.WanChengBiLi="比例";
			mEntity.shouKuanEntity=entity;
			mEntity.type=VThirdItemEntity.TYPE_SHOUKUAN;
			mList.add(mEntity);
			
			for(int i=0;i<srcList.size();i++){
				mEntity=new VThirdItemEntity();
				mEntity.shouKuanEntity=srcList.get(i);
				mEntity.type=VThirdItemEntity.TYPE_SHOUKUAN;
				mList.add(mEntity);
				if (!"总部小计".equals(srcList.get(i).BMName)
						|| !"子公司小计".equals(srcList.get(i).BMName)
						|| !"分公司小计".equals(srcList.get(i).BMName)) {
					String str = srcList.get(i).NianDuShouKuanShiJi;
					if(!TextUtils.isEmpty(str)){
						ShiJiTotal = ShiJiTotal + Integer.valueOf(str);
					}
					
					String str1 = srcList.get(i).NianDuShouKuanYuSuan;
					if(!TextUtils.isEmpty(str1)){
						YuSuanTotal = YuSuanTotal + Integer.valueOf(str1);
					}
				}
			}
			
			mEntity=new VThirdItemEntity();
		    entity=new PJiDuShouKuanItemEntity();
			entity.BMName="总计";
			entity.NianDuShouKuanYuSuan=YuSuanTotal+"";
			entity.NianDuShouKuanShiJi=ShiJiTotal+"";
			entity.WanChengBiLi="";
			mEntity.shouKuanEntity=entity;
			mEntity.type=VThirdItemEntity.TYPE_SHOUKUAN;
			mList.add(mEntity);
		}
		return mList;
	}
	
	
	public static ArrayList<VThirdItemEntity> getThirdList1(ArrayList<PJiDuShouKuanItemEntity> srcList,String BMID){
		ArrayList<VThirdItemEntity> mList=new ArrayList<VThirdItemEntity> ();
		if(srcList!=null&&srcList.size()>0){
			int ShiJiTotal=0;
			int YuSuanTotal=0;
			VThirdItemEntity mEntity=new VThirdItemEntity();
			PJiDuShouKuanItemEntity entity=new PJiDuShouKuanItemEntity();
			entity.BMName="部门";
			entity.NianDuShouKuanYuSuan="预算";
			entity.NianDuShouKuanShiJi="实际";
			entity.WanChengBiLi="比例";
			mEntity.shouKuanEntity=entity;
			mEntity.type=VThirdItemEntity.TYPE_SHOUKUAN;
			mList.add(mEntity);
			
			for(int i=0;i<srcList.size();i++){
				if(srcList.get(i).BMID.equals(BMID)){
					mEntity=new VThirdItemEntity();
					mEntity.shouKuanEntity=srcList.get(i);
					mEntity.type=VThirdItemEntity.TYPE_SHOUKUAN;
					mList.add(mEntity);
				}
			}
			
		}
		return mList;
	}
	public static ArrayList<VJieShouRenEntity> getJieShouRen(ArrayList<PDetailHTInfoEntity> srcList){
		 ArrayList<VJieShouRenEntity> mList=new ArrayList<VJieShouRenEntity>();
		 if(srcList!=null&&srcList.size()>0){
			 for(int i=0;i<srcList.size();i++){
				 VJieShouRenEntity mEntity=new VJieShouRenEntity();
				 mEntity.HTInfoEntity=srcList.get(i);
				 mEntity.type=VJieShouRenEntity.TYPE_HT;
				 mList.add(mEntity);
			 }
		 }
		 return mList;
	}
	
	public static ArrayList<VJieShouRenEntity> getJieShouRen1(ArrayList<PDetailTJInfoInfoEntity> srcList){
		 ArrayList<VJieShouRenEntity> mList=new ArrayList<VJieShouRenEntity>();
		 if(srcList!=null&&srcList.size()>0){
			 for(int i=0;i<srcList.size();i++){
				 VJieShouRenEntity mEntity=new VJieShouRenEntity();
				 mEntity.TJInfoEntity=srcList.get(i);
				 mEntity.type=VJieShouRenEntity.TYPE_TJ;
				 mList.add(mEntity);
			 }
		 }
		 return mList;
	}
}
