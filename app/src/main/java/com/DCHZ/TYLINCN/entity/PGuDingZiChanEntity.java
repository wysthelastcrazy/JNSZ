package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/16.
 */
public class PGuDingZiChanEntity implements Serializable{
    public String GZID;
    public String BianHao;
    public String ShenQingBuMen;
    public String ShenQingBuMenName;
    public String ShenQingRen;
    public String ShenQingRenName;
    public String ShenQingRiQi;
    public String GouZhiYuanYin;
    public String GouZhiBuMen;
    public String GouZhiBuMenName;
    public String ShenPiZhuangTai;
    public String ShenPiRiQi;
    public String LeiXing;
    public String FlowID;
    public ArrayList<PFuJianEntity> FuJian;
    public ArrayList<PGuDingZiChanItemEntity> GuDingZiChanMXInfo;
}
