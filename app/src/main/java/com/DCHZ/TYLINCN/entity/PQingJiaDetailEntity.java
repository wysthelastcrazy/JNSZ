package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * 报销单实体类
 * @author wys
 *
 */
public class PQingJiaDetailEntity implements Serializable{
	private static final long serialVersionUID = 3L;
	
	public ArrayList<PDetailQJInfoEntity> qingJiaInfo;
	public ArrayList<PDetailHTInfoEntity> htInfo;
	public ArrayList<PDetailTJInfoInfoEntity> tjInfo;

}
