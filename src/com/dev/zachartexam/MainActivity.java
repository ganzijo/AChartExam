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

		data.setChartTitle("��Ʈ Ÿ��Ʋ");
		data.setxTitle("X�� Ÿ��Ʋ");
		data.setyTitle("Y�� Ÿ��Ʋ");
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
		// // ���� �ִ� ��� ó��
		// } else {
		// Toast.makeText(getApplicationContext(), "null ����Ȯ��",
		// Toast.LENGTH_LONG).show();
		// // ���� ���� ��� ó��
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

		// ���� ������ �Է�
		renderer.addXTextLabel(1, "1��");
		renderer.addXTextLabel(3, "3��");
		renderer.addXTextLabel(5, "5��");
		renderer.addXTextLabel(7, "7��");
		renderer.addXTextLabel(9, "9��");
		renderer.addXTextLabel(11, "11��");

		// �з�� ���� ũ�� �� �� ���� ����
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer seriesRenderer = renderer
					.getSeriesRendererAt(i);
			seriesRenderer.setDisplayChartValues(true); // ��Ʈ ���� value����
														// ǥ���Ǵ� �κ�
		}

		// �׷��� ������ �κ� ����
		lv = (LinearLayout) findViewById(R.id.mainlinear);
		GraphicalView gv = (GraphicalView) ChartFactory.getBarChartView(
				// getBaseContext(),buildBarDataset(data.getChartTitle(),
				// values), renderer, Type.DEFAULT);
				getBaseContext(), buildBarDataset(titles, values), renderer,
				Type.DEFAULT);
		lv.addView(gv);
		// �׷��� ������ �κ� ����

	}

	protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setOrientation(Orientation.HORIZONTAL); // �׷��� ����
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setZoomEnabled(true, true); // Zoom ��� On/Off
		renderer.setZoomRate(1.0f); // Zoom ����
		renderer.setBarSpacing(0.2f); // ���밣 ���� ����
		renderer.setPanEnabled(false, false); // X,Y�� ��ũ�� ���� On/Off

		int length = colors.length;
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			renderer.addSeriesRenderer(r);
		}
		return renderer;
	}

	// ���� ���� ���� : �ɼ�(�ؽ�Ʈ ũ�� ���)���� ������ ������ dataset ���·� ����
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
// data.setChartTitle("��Ʈ Ÿ��Ʋ");
// data.setxTitle("X�� Ÿ��Ʋ");
// data.setyTitle("Y�� Ÿ��Ʋ");
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
// // // ���� �ִ� ��� ó��
// // } else {
// // Toast.makeText(getApplicationContext(), "null ����Ȯ��",
// // Toast.LENGTH_LONG).show();
// // // ���� ���� ��� ó��
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
// renderer.setOrientation(Orientation.HORIZONTAL); // �׷��� ����
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
// renderer.setZoomEnabled(true, true); // Zoom ��� On/Off
// renderer.setZoomRate(1.0f); // Zoom ����
// renderer.setBarSpacing(0.2f); // ���밣 ���� ����
// renderer.setPanEnabled(false, false); // X,Y�� ��ũ�� ���� On/Off
//
// // �з�� ���� ũ�� �� �� ���� ����
// int length = renderer.getSeriesRendererCount();
// for (int i = 0; i < length; i++) {
// SimpleSeriesRenderer seriesRenderer = renderer
// .getSeriesRendererAt(i);
// seriesRenderer.setDisplayChartValues(true); // ��Ʈ ���� value����
// // ǥ���Ǵ� �κ�
// }
//
// // �׷��� ������ �κ� ����
// lv = (LinearLayout) findViewById(R.id.mainlinear);
// GraphicalView gv = (GraphicalView) ChartFactory.getBarChartView(
// // getBaseContext(),buildBarDataset(data.getChartTitle(),
// // values), renderer, Type.DEFAULT);
// getBaseContext(), buildBarDataset(titles, values), renderer,
// Type.DEFAULT);
// lv.addView(gv);
// // �׷��� ������ �κ� ����
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
// // ���� ���� ���� : �ɼ�(�ؽ�Ʈ ũ�� ���)���� ������ ������ dataset ���·� ����
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

