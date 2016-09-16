package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/16.
 * 公章借出申请实体类
 */
public class PGongZhangDetailEntity implements Serializable{
    public ArrayList<PGongZhangEntity> GongZhangJieChuInfo;
    public ArrayList<PDetailHTInfoEntity> htInfo;
    public ArrayList<PDetailTJInfoInfoEntity> tjInfo;
}
