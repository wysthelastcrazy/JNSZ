package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;

public class VJieShouRenEntity implements Serializable{
	private static final long serialVersionUID = -888888L;
	public static final int TYPE_HT=1;
	public static final int TYPE_TJ=2;
	
	public PDetailHTInfoEntity HTInfoEntity;
	public PDetailTJInfoInfoEntity TJInfoEntity;
	public int type;
}
