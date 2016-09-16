package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 借款单实体类
 * @author wys
 *
 */
public class PGuDingZiChanDetailEntity implements Serializable{
	private static final long serialVersionUID = 3L;
	
	public ArrayList<PGuDingZiChanEntity> GuDingZiChanInfo;
	public ArrayList<PDetailHTInfoEntity> htInfo;
	public ArrayList<PDetailTJInfoInfoEntity> tjInfo;

}
