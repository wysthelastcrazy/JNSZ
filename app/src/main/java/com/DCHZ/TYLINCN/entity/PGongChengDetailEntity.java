package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * 分包方合同单实体类
 * @author wys
 *
 */
public class PGongChengDetailEntity implements Serializable{
	private static final long serialVersionUID = 3L;
	
	public ArrayList<PDetailGongChengEntity> fenBaoFuKuanInfo;
	public ArrayList<PDetailHTInfoEntity> htInfo;
	public ArrayList<PDetailTJInfoInfoEntity> tjInfo;

}
