package com.DCHZ.TYLINCN.component;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.util.MyLog;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by yas on 2016/8/23.
 */
public class ChartView extends LinearLayout{
    private PieChart mPicChart; //饼图
    private LineChartView mLineChart;// 折线图
    public ChartView(Context context) {
        super(context);
        init();
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LayoutInflater li= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.chart_view,this,true);

        initPieChart();
        initLine();
    }

    private void initPieChart() {
        mPicChart= (PieChart) this.findViewById(R.id.mPieChart);
        mPicChart.setHoleColorTransparent(true);

        mPicChart.setHoleRadius(60f);  //半径
        mPicChart.setTransparentCircleRadius(64f); // 半透明圈
        //pieChart.setHoleRadius(0)  //实心圆

        mPicChart.setDescription("");

        // mChart.setDrawYValues(true);
        mPicChart.setDrawCenterText(false);  //饼状图中间可以添加文字

        mPicChart.setDrawHoleEnabled(false);

        mPicChart.setRotationAngle(180); // 初始旋转角度

        // draws the corresponding description value into the slice
        // mChart.setDrawXValues(true);

        // enable rotation of the chart by touch
        mPicChart.setRotationEnabled(false); // 可以手动旋转

        // display percentage values
        mPicChart.setUsePercentValues(true);  //显示成百分比
        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//      mChart.setOnChartValueSelectedListener(this);
        // mChart.setTouchEnabled(false);

//      mChart.setOnAnimationListener(this);

//        mPicChart.setCenterText("Quarterly Revenue");  //饼状图中间的文字

        Legend mLegend = mPicChart.getLegend();  //设置比例图
        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);  //最右边显示
//      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);

        mPicChart.animateXY(1000, 1000);  //设置动画
        PieData data=getPieData(2,100);
        mPicChart.setData(data);
    }

    private void initLine() {
        mLineChart= (LineChartView) this.findViewById(R.id.mLineChart);
        //设置行为属性，支持缩放、滑动以及平移
        mLineChart.setInteractive(false);
        ArrayList<PointValue> mPointValues=new ArrayList<PointValue>();
        ArrayList<AxisValue> mAxisValues=new ArrayList<AxisValue>();
        for (int i = 0; i < 12 ; i++) {
            mPointValues.add(new PointValue(i, new Random().nextInt(10)));
            mAxisValues.add(new AxisValue(i).setLabel(i+"")); //为每个对应的i设置相应的label(显示在X轴)
        }
        Line line = new Line(mPointValues).setStrokeWidth(1).setColor(Color.GRAY).setPointRadius(2).setPointColor(Color.GRAY).setCubic(false);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);
        axisX.setTextColor(Color.BLACK);
        axisX.setLineColor(Color.GRAY);
        axisX.setName("采集时间");
        axisX.setValues(mAxisValues);
        data.setAxisXBottom(axisX);

        Axis axisY = new Axis();  //Y轴
        axisY.setTextColor(Color.BLACK);
        axisY.setLineColor(Color.GRAY);
        data.setAxisYLeft(axisY);
        mLineChart.setLineChartData(data);
    }

    public void setData(){

    }

    /**
     *
     * @param count 分成几部分
     * @param range
     */
    private PieData getPieData(int count, float range) {

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容

        for (int i = 0; i < count; i++) {
            xValues.add("Quarterly" + (i + 1));  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4
        }

        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据

        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */
        float quarterly1 = 24;
        float quarterly2 = 76;

        yValues.add(new Entry(quarterly1, 0));
        yValues.add(new Entry(quarterly2, 1));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, ""/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));

        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);

        return pieData;
    }

}
