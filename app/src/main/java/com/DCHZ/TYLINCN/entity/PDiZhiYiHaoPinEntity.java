package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/16.
 */
public class PDiZhiYiHaoPinEntity implements Serializable{
    public String GZID;
    public String GZShenQingBuMen;
    public String ShenQingBuMenMingCheng;
    public String GZShenQingRen;
    public String ShenQingRenName;
    public String GZShenQingRiQi;
    public String GZYuanYin;
    public String GZBuMen;
    public String GZBuMenMingCheng;
    public String GZShenPiZhuangTai;
    public String GZShenPiRiQi;
    public String GZFenLei;
    public ArrayList<PFuJianEntity> GZFuJian;
    public ArrayList<PDiZhiYiHaoPinItemEntity> DiZhiYiHaoMXInfo;
}
