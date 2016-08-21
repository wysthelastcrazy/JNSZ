package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class PGaiZhangDetailEntity implements Serializable{
	private static final long serialVersionUID = 3L;

	public ArrayList<PDetailGaiZhangEntity> GaiZhangShenQingInfo;
	public ArrayList<PDetailHTInfoEntity> htInfo;
	public ArrayList<PDetailTJInfoInfoEntity> tjInfo;
}
