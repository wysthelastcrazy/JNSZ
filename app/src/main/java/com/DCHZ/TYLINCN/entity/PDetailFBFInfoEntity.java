package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class PDetailFBFInfoEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public String GFBanGongDianHua;
	public String GFBeiZhu;
	public String GFBianHao;
	public String GFChuanZhen;
	public String GFDanWeiZiZhi;
	public String GFDengJi;
	
	public String GFDengJiBuMen;
	public String GFDengJiRen;
	public String GFDengJiRiQi;
	public String GFDiZhi;
	public String GFDianHua;
	public String GFFaRen;
	
	public String GFHangYe;
	public String GFID;
	public String GFKaiHuMingCheng;
	public String GFKaiHuYinHang;
	public String GFKaiHuZhangHao;
	public String GFLianXiRen;
	
	public String GFMingCheng;
	public String GFPingJia;
	public String GFPingShenJieLun;
	public String GFRenYuanShiFouManZu;
	public String GFShenPiShiJian;
	public String GFShenPiZhuangTai;
	
	public String GFShiFouChangQiHeZuo;
	public String GFShuiHao;
	public String GFShuoMing;
	public String GFWangZhi;
	public String GFXingZhi;
	public String GFYeWuFanWei;
	
	public String GFYouBian;
	public String GFZhuYaoRenYuanPeiZhi;
	public String GFZhuanYeSheZhi;
	public String GFZuZhiJiGouDaiMa;
	
	public String GFYeJi;
	public ArrayList<PFuJianEntity> FuJian;
	public ArrayList<Shuxing> ShuXing;
	
	
	public class Shuxing implements Serializable{
		private static final long serialVersionUID = 99999999L;
		public String GFPingShenJieLun;
		public boolean ReadOnly;
	}
}
