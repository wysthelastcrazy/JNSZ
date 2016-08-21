package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;
/**
 * 代办实体类
 * @author wys
 *
 */
public class PDaiBanEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	public String SLID;
	public String LCID;		//流程id
	public String YWID;		//业务id
	public String YWName;	//业务名称
	public String BLAcceptDate;//接受日期
	public String BLState;	//办理状态
	public String BLOpinion;	//意见
	public String BLAcceptorName;//
	public String BLUrl;	//办理URL
	public String ShenQingRen;//审批人
	public String YWTypeKey;
	public String YWTypeValue;
	public String JianKong;
	public String BLReferDate;
}
