package com.dev.zachartexam;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer.Orientation;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;

import com.dev.zachartexam.ChartDataVO.Data;

public class MainActivity extends ActionBarActivity {

	LinearLayout lv;
	List<double[]> values = new ArrayList<double[]>();

	private ChartDataVO getChartDataVO() {
		ChartDataVO vo = new ChartDataVO();
		Data data = vo.getData();

		data.setChartTitle("차트 타이틀");
		data.setxTitle("X축 타이틀");
		data.setyTitle("Y축 타이틀");
		data.setXAxisMin("0.5");
		data.setXAxisMax("12.5");
		data.setYAxisMin("0");
		data.setYAxisMax("25000");
		data.getxTextLabel();
		data.getyTextLabel();
		data.setxLabels("1");
		data.setyLabels("10");
		data.setAxesColor(Color.BLUE);
		data.setLabelsColor(Color.RED);
		return vo;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ChartDataVO vo = getChartDataVO();
		// ChartDataVO vo = new ChartDataVO();
		Data data = vo.getData();

		// if (data == null || data.equals(null)) {
		// // 값이 있는 경우 처리
		// } else {
		// Toast.makeText(getApplicationContext(), "null 유무확인",
		// Toast.LENGTH_LONG).show();
		// // 값이 없는 경우 처리
		// }
		String[] titles = { "Temp Title", "Second Title" };

		values.add(new double[] { 5230, 7300, 9240, 10540, 7900, 9200, 12030,
				11200, 9500, 10500, 11600, 13500 });
		values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200,
				22030, 21200, 19500, 15500, 12600, 14000 });

		int[] colors = new int[] { data.getAxesColor(), data.getLabelsColor() };
		XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);

		renderer.setXLabels(Integer.parseInt(data.getxLabels()));
		renderer.setYLabels(Integer.parseInt(data.getyLabels()));
		setChartSettings(renderer, data);

		// for (Entry<String, String> entry : data.getxTextLabel().entrySet()) {
		// renderer.addXTextLabel(Integer.parseInt(entry.getKey()),
		// entry.getValue());
		// }

		// 수동 데이터 입력
		renderer.addXTextLabel(1, "1월");
		renderer.addXTextLabel(3, "3월");
		renderer.addXTextLabel(5, "5월");
		renderer.addXTextLabel(7, "7월");
		renderer.addXTextLabel(9, "9월");
		renderer.addXTextLabel(11, "11월");

		// 분루명 글자 크기 및 각 색상 지정
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer seriesRenderer = renderer
					.getSeriesRendererAt(i);
			seriesRenderer.setDisplayChartValues(true); // 차트 내의 value들이
														// 표현되는 부분
		}

		// 그래프 붙히는 부분 시작
		lv = (LinearLayout) findViewById(R.id.mainlinear);
		GraphicalView gv = (GraphicalView) ChartFactory.getBarChartView(
				// getBaseContext(),buildBarDataset(data.getChartTitle(),
				// values), renderer, Type.DEFAULT);
				getBaseContext(), buildBarDataset(titles, values), renderer,
				Type.DEFAULT);
		lv.addView(gv);
		// 그래프 붙히는 부분 종료

	}

	protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setOrientation(Orientation.HORIZONTAL); // 그래프 방향
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setZoomEnabled(true, true); // Zoom 기능 On/Off
		renderer.setZoomRate(1.0f); // Zoom 비율
		renderer.setBarSpacing(0.2f); // 막대간 간격 설정
		renderer.setPanEnabled(false, false); // X,Y축 스크롤 여부 On/Off

		int length = colors.length;
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			renderer.addSeriesRenderer(r);
		}
		return renderer;
	}

	// 설정 정보 설정 : 옵션(텍스트 크기 등등)등의 설정한 정보를 dataset 형태로 저장
	// protected XYMultipleSeriesDataset buildBarDataset(String titles,
	// List<double[]> values) {
	// XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	// int length = titles.length();
	// for (int i = 0; i < length; i++) {
	// CategorySeries series = new CategorySeries(titles.);
	// double[] v = values.get(i);
	//
	// int seriesLength = v.length;
	// for (int k = 0; k < seriesLength; k++) {
	// series.add((v[k]));
	// }
	// dataset.addSeries(series.toXYSeries());
	// }
	// return dataset;
	// }

	protected XYMultipleSeriesDataset buildBarDataset(String[] titles,
			List<double[]> values) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			CategorySeries series = new CategorySeries(titles[i]);
			double[] v = values.get(i);
			int seriesLength = v.length;
			for (int k = 0; k < seriesLength; k++) {
				series.add(v[k]);
			}
			dataset.addSeries(series.toXYSeries());
		}
		return dataset;
	}

	private void setChartSettings(XYMultipleSeriesRenderer renderer, Data data) {
		renderer.setChartTitle(data.getChartTitle());
		renderer.setXTitle(data.getxTitle());
		renderer.setYTitle(data.getYTitle());
		renderer.setXAxisMin(Double.parseDouble(data.getXAxisMin()));
		renderer.setXAxisMax(Double.parseDouble(data.getXAxisMax()));
		renderer.setYAxisMin(Double.parseDouble(data.getYAxisMin()));
		renderer.setYAxisMax(Double.parseDouble(data.getYAxisMax()));
		renderer.setAxesColor(data.getAxesColor());
		renderer.setLabelsColor(data.getLabelsColor());
	}
}





















// package com.dev.zachartexam;
//
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map.Entry;
//
// import org.achartengine.ChartFactory;
// import org.achartengine.GraphicalView;
// import org.achartengine.chart.BarChart.Type;
// import org.achartengine.model.CategorySeries;
// import org.achartengine.model.XYMultipleSeriesDataset;
// import org.achartengine.renderer.SimpleSeriesRenderer;
// import org.achartengine.renderer.XYMultipleSeriesRenderer;
// import org.achartengine.renderer.XYMultipleSeriesRenderer.Orientation;
//
// import android.graphics.Color;
// import android.os.Bundle;
// import android.os.StrictMode;
// import android.support.v7.app.ActionBarActivity;
// import android.widget.LinearLayout;
// import android.widget.Toast;
//
// import com.dev.zachartexam.ChartDataVO.Data;
//
// public class MainActivity extends ActionBarActivity {
//
// LinearLayout lv;
// List<double[]> values = new ArrayList<double[]>();
//
// private ChartDataVO getChartDataVO() {
// ChartDataVO vo = new ChartDataVO();
// Data data = vo.getData();
//
// data.setChartTitle("차트 타이틀");
// data.setxTitle("X축 타이틀");
// data.setyTitle("Y축 타이틀");
// data.setXAxisMin("0.5");
// data.setXAxisMax("12.5");
// data.setYAxisMin("0");
// data.setYAxisMax("25000");
// data.getxTextLabel();
// data.getyTextLabel();
// data.setxLabels("1");
// data.setyLabels("10");
// data.setAxesColor(Color.WHITE);
// data.setLabelsColor(Color.WHITE);
// return vo;
//
// }
//
// @Override
// protected void onCreate(Bundle savedInstanceState) {
// super.onCreate(savedInstanceState);
// setContentView(R.layout.activity_main);
// StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
// .permitAll().build();
// StrictMode.setThreadPolicy(policy);
//
// ChartDataVO vo = getChartDataVO();
// // ChartDataVO vo = new ChartDataVO();
// Data data = vo.getData();
//
// // if (data == null || data.equals(null)) {
// // // 값이 있는 경우 처리
// // } else {
// // Toast.makeText(getApplicationContext(), "null 유무확인",
// // Toast.LENGTH_LONG).show();
// // // 값이 없는 경우 처리
// // }
// String[] titles = {"Temp Title", "Second Title"};
//
// values.add(new double[] { 5230, 7300, 9240, 10540, 7900, 9200, 12030,
// 11200, 9500, 10500, 11600, 13500 });
// values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200,
// 22030, 21200, 19500, 15500, 12600, 14000 });
//
// int[] colors = new int[] { Color.RED, Color.BLUE };
// XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
// renderer.setOrientation(Orientation.HORIZONTAL); // 그래프 방향
// renderer.setXLabels(Integer.parseInt(data.getxLabels()));
// renderer.setYLabels(Integer.parseInt(data.getyLabels()));
// setChartSettings(renderer, data);
//
// // for (Entry<String, String> entry : data.getxTextLabel().entrySet()) {
// // renderer.addXTextLabel(Integer.parseInt(entry.getKey()),
// // entry.getValue());
// // }
// renderer.addXTextLabel(0, "0");
// renderer.addXTextLabel(3, "1");
// renderer.addXTextLabel(5, "2");
// renderer.addXTextLabel(7, "3");
// renderer.addXTextLabel(9, "4");
//
// renderer.setZoomEnabled(true, true); // Zoom 기능 On/Off
// renderer.setZoomRate(1.0f); // Zoom 비율
// renderer.setBarSpacing(0.2f); // 막대간 간격 설정
// renderer.setPanEnabled(false, false); // X,Y축 스크롤 여부 On/Off
//
// // 분루명 글자 크기 및 각 색상 지정
// int length = renderer.getSeriesRendererCount();
// for (int i = 0; i < length; i++) {
// SimpleSeriesRenderer seriesRenderer = renderer
// .getSeriesRendererAt(i);
// seriesRenderer.setDisplayChartValues(true); // 차트 내의 value들이
// // 표현되는 부분
// }
//
// // 그래프 붙히는 부분 시작
// lv = (LinearLayout) findViewById(R.id.mainlinear);
// GraphicalView gv = (GraphicalView) ChartFactory.getBarChartView(
// // getBaseContext(),buildBarDataset(data.getChartTitle(),
// // values), renderer, Type.DEFAULT);
// getBaseContext(), buildBarDataset(titles, values), renderer,
// Type.DEFAULT);
// lv.addView(gv);
// // 그래프 붙히는 부분 종료
//
// }
//
// protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
// XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
// renderer.setAxisTitleTextSize(16);
// renderer.setChartTitleTextSize(20);
// renderer.setLabelsTextSize(15);
// renderer.setLegendTextSize(15);
//
// int length = colors.length;
// for (int i = 0; i < length; i++) {
// SimpleSeriesRenderer r = new SimpleSeriesRenderer();
// r.setColor(colors[i]);
// renderer.addSeriesRenderer(r);
// }
// return renderer;
// }
//
// // 설정 정보 설정 : 옵션(텍스트 크기 등등)등의 설정한 정보를 dataset 형태로 저장
// // protected XYMultipleSeriesDataset buildBarDataset(String titles,
// // List<double[]> values) {
// // XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
// // int length = titles.length();
// // for (int i = 0; i < length; i++) {
// // CategorySeries series = new CategorySeries(titles.);
// // double[] v = values.get(i);
// //
// // int seriesLength = v.length;
// // for (int k = 0; k < seriesLength; k++) {
// // series.add((v[k]));
// // }
// // dataset.addSeries(series.toXYSeries());
// // }
// // return dataset;
// // }
//
// protected XYMultipleSeriesDataset buildBarDataset(String[] titles,
// List<double[]> values) {
// XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
// int length = titles.length;
// for (int i = 0; i < length; i++) {
// CategorySeries series = new CategorySeries(titles[i]);
// double[] v = values.get(i);
// int seriesLength = v.length;
// for (int k = 0; k < seriesLength; k++) {
// series.add(v[k]);
// }
// dataset.addSeries(series.toXYSeries());
// }
// return dataset;
// }
//
// private void setChartSettings(XYMultipleSeriesRenderer renderer, Data data) {
// renderer.setChartTitle(data.getChartTitle());
// renderer.setXTitle(data.getxTitle());
// renderer.setYTitle(data.getYTitle());
// renderer.setXAxisMin(Double.parseDouble(data.getXAxisMin()));
// renderer.setXAxisMax(Double.parseDouble(data.getXAxisMax()));
// renderer.setYAxisMin(Double.parseDouble(data.getYAxisMin()));
// renderer.setYAxisMax(Double.parseDouble(data.getYAxisMax()));
// renderer.setAxesColor(data.getAxesColor());
// renderer.setLabelsColor(data.getLabelsColor());
// }
// }

