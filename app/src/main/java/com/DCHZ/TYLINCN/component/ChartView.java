package com.DCHZ.TYLINCN.component;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.DCHZ.TYLINCN.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

/**
 * Created by yas on 2016/8/23.
 */
public class ChartView extends LinearLayout{
    private PieChart mPicChart; //饼图
    private LineChart mChart;
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
        li.inflate(R.layout.chart_view, this, true);

        initPieChart();
        initLineChart();
    }

    private void initLineChart() {
        mChart= (LineChart) this.findViewById(R.id.mLineChart1);
        /**
         * ====================1.初始化-自由配置===========================
         */
        // 是否在折线图上添加边框
        mChart.setDrawGridBackground(false);
        mChart.setDrawBorders(true);
        mChart.setDrawMarkerViews(false);
        // 设置右下角描述
        mChart.setDescription("");
        //设置透明度
        mChart.setAlpha(0.8f);
        //设置网格底下的那条线的颜色
        mChart.setBorderColor(Color.rgb(213, 216, 214));
        //设置高亮显示
//        mChart.setHighlightEnabled(true);
        //设置是否可以触摸，如为false，则不能拖动，缩放等
        mChart.setTouchEnabled(true);
        //设置是否可以拖拽
        mChart.setDragEnabled(false);
        //设置是否可以缩放
        mChart.setScaleEnabled(false);
        //设置是否能扩大扩小
        mChart.setPinchZoom(false);
        /**
         * ====================2.布局点添加数据-自由布局===========================
         */
        // 折线图的点，点击战士的布局和数据
//        MyMarkView mv = new MyMarkView(this);
//        mChart.setMarkerView(mv);
        // 加载数据
        LineData data = getLineData();
        mChart.setData(data);
        /**
         * ====================3.x，y动画效果和刷新图表等===========================
         */
        //从X轴进入的动画
        mChart.animateX(4000);
        mChart.animateY(3000);   //从Y轴进入的动画
        mChart.animateXY(3000, 3000);    //从XY轴一起进入的动画
        //设置最小的缩放
        mChart.setScaleMinima(0.5f, 1f);
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);  //设置图最下面显示的类型
        l.setTextSize(15);
        l.setTextColor(Color.rgb(104, 241, 175));
        l.setFormSize(30f);

        // 设置Y轴右边不显示数字
        mChart.getAxisRight().setEnabled(false);

        XAxis xAxis = mChart.getXAxis();
        // 设置X轴的数据显示在报表的下方
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setDrawAxisLine(false);
        // 设置不从X轴发出纵向直线
//        xAxis.setDrawGridLines(false);
        // 刷新图表
        mChart.invalidate();
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


    public void setData(){

    }

    private LineData getLineData() {
        String[] xx = {"2", "4", "6", "8", "10", "12", "14", "16", "18"};
        String[] yy = {"20", "80", "10", "60", "30", "70", "55", "22", "40"};

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < xx.length; i++) {
            xVals.add(xx[i]);
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < yy.length; i++) {
            yVals.add(new Entry(Float.parseFloat(yy[i]), i));
        }

        LineDataSet set1 = new LineDataSet(yVals, "LineChart Test");
        set1.setDrawCubic(true);  //设置曲线为圆滑的线
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(false);  //设置包括的范围区域填充颜色
        set1.setDrawCircles(false);  //设置有圆点
        set1.setLineWidth(2f);    //设置线的宽度
        set1.setCircleSize(5f);   //设置小圆的大小
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(104, 241, 175));    //设置曲线的颜色
        set1.setValueTextSize(0);
        return new LineData(xVals, set1);
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
