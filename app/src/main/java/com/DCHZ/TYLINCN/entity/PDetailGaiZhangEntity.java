package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class PDetailGaiZhangEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public String GZBeiZhu;
	public String GZFenShu;
	public String GZGaiZhangWenJianLeiXing;
	public String GZHeTongBianHao;
	public String GZID;
	public String GZLeiXing;
	
	public String GZQianYueDanWei;
	public String GZShenPiShiJian;
	public String GZShenPiZhuangTai;
	public String GZShenQingBuMen;
	public String GZShenQingRen;
	
	public String GZShiFouGuiDang;
	public String GZWenJianLeiBie;
	public String GZYongTu;
	public String LiuShuiHao;
	public String XMBianHao;
	public String XMMingCheng;
	public ArrayList<PFuJianEntity> FuJian;

}
