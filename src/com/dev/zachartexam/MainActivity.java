package com.dev.zachartexam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

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
import com.dev.zachartexam.ChartDataVO.Setting;
import com.dev.zachartexam.ChartDataVO.Title;

public class MainActivity extends ActionBarActivity {

	LinearLayout lv;
	List<double[]> values = new ArrayList<double[]>();

	private ChartDataVO getChartDataVO() {
		ChartDataVO vo = new ChartDataVO();
		Data data = vo.getData();
		data.setChartTitle("��Ʈ Ÿ��Ʋ");
		Setting x = new Setting();
		x.setAxisMin("0.5");
		x.setAxisMax("12.5");
		x.getTextLabel().put("1", "1��");
		x.getTextLabel().put("3", "3��");
		x.getTextLabel().put("5", "5��");
		x.getTextLabel().put("7", "7��");
		x.getTextLabel().put("9", "9��");
		x.getTextLabel().put("11", "11��");
		
		Setting y = new Setting();
		y.setAxisMin("0");
		y.setAxisMax("25000");

		Title title1 = new Title();
		title1.setTitle("Temp Title");
		Double[] value1 = new Double[] { 5230.0, 7300.0, 9240.0, 10540.0,
				7900.0, 9200.0, 12030.0, 11200.0, 9500.0, 10500.0, 11600.0,
				13500.0 };
		title1.setValues(Arrays.asList(value1));
		title1.setColor(Color.BLUE);
		data.getTitles().add(title1);

		Title title2 = new Title();
		title2.setTitle("Second Title");
		Double[] value2 = new Double[] { 14230.0, 12300.0, 14240.0, 15244.0,
				15900.0, 19200.0, 22030.0, 21200.0, 19500.0, 15500.0, 12600.0,
				14000.0 };
		title2.setValues(Arrays.asList(value2));
		title2.setColor(Color.RED);
		data.getTitles().add(title2);
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
		Data data = vo.getData();

		// �׷��� ������ �κ� ����
		lv = (LinearLayout) findViewById(R.id.mainlinear);
		GraphicalView gv = (GraphicalView) ChartFactory.getBarChartView(
				// getBaseContext(),buildBarDataset(data.getChartTitle(),
				// values), renderer, Type.DEFAULT);
				getBaseContext(), buildBarDataset(data), buildrenderer(data),
				Type.DEFAULT);
		lv.addView(gv);
		// �׷��� ������ �κ� ����

	}

	protected XYMultipleSeriesDataset buildBarDataset(Data data) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		for (Title title : data.getTitles()) {
			CategorySeries series = new CategorySeries(title.getTitle());
			for (Double value : title.getValues()) {
				series.add(value == null ? 0 : value);
			}
			dataset.addSeries(series.toXYSeries());
		}
		return dataset;
	}

	protected XYMultipleSeriesRenderer buildrenderer(Data data) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		for (Title title : data.getTitles()) {
			SimpleSeriesRenderer simpleSeriesRenderer = new SimpleSeriesRenderer();
			simpleSeriesRenderer.setColor(title.getColor());
			renderer.addSeriesRenderer(simpleSeriesRenderer);
		}

		renderer.setOrientation(Orientation.HORIZONTAL);
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setZoomEnabled(true, true);
		renderer.setZoomRate(1.0f);
		renderer.setBarSpacing(0.2f);
		renderer.setPanEnabled(false, false);
		
		Setting x = data.getX();
		renderer.setXLabels(Integer.parseInt(x.getLabels()));
		renderer.setXTitle(x.getTitle());
		renderer.setXAxisMin(Double.parseDouble(x.getAxisMin()));
		renderer.setXAxisMax(Double.parseDouble(x.getAxisMax()));
		for (Entry<String, String> entry : x.getTextLabel().entrySet()) {
			renderer.addXTextLabel(Integer.parseInt(entry.getKey()),
					entry.getValue());
		}

		Setting y = data.getY();
		renderer.setYLabels(Integer.parseInt(y.getLabels()));
		renderer.setYTitle(y.getTitle());
		renderer.setYAxisMin(Double.parseDouble(y.getAxisMin()));
		renderer.setYAxisMax(Double.parseDouble(y.getAxisMax()));
		for (Entry<String, String> entry : y.getTextLabel().entrySet()) {
			renderer.addYTextLabel(Integer.parseInt(entry.getKey()),
					entry.getValue());
		}

		renderer.setChartTitle(data.getChartTitle());
		renderer.setAxesColor(Integer.parseInt(data.getAxesColor()));
		renderer.setLabelsColor(Integer.parseInt(data.getLabelsColor()));

		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer seriesRenderer = renderer
					.getSeriesRendererAt(i);
			seriesRenderer.setDisplayChartValues(true);
		}
		return renderer;
	}
}
