package com.dev.zachartexam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	LinearLayout lv;

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.1.172:1521:xe";
		String userid = "WRJO";
		String passwd = "qwer";
		
		try {
			Class.forName(driver);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String elec = "";
		Integer gas = null;
		Integer water = null;
		
		String[] titles = new String[] { "2007", "2008" };
//		List<Double> values = new ArrayList<Double>();
		List<Double> list = new ArrayList<Double>();
		Double values = null;
		try {
			
			con = DriverManager.getConnection(url, userid, passwd);
			Toast.makeText(getApplicationContext(), "DB 확인" , Toast.LENGTH_LONG).show();
			String query = "SELECT elec FROM NURITEST";

			stmt = con.prepareStatement(query);

			rs = stmt.executeQuery();
//			List<Double> elec = new ArrayList<Double>();
			
			
			while (rs.next()) {
				
				list.add(rs.getDouble("elec"));
//				elec = rs.getString(elec);
//				gas = rs.getInt("gas");
//				water = rs.getInt("water");
//				values.add(new double[] );
				
			}



//			values.add(new double[] {});
			// values.add(new double[] { 5230, 7300, 9240, 10540, 7900, 9200, 12030,
			// 11200, 9500, 10500, 11600, 13500 });
//			values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200,
//					22030, 21200, 19500, 15500, 12600, 14000 });
			int[] colors = new int[] { Color.CYAN, Color.BLUE };

			XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);

			renderer.setOrientation(Orientation.HORIZONTAL); // 그래프 방향
			renderer.setChartValuesTextSize(40);

			setChartSettings(renderer, "Monthly sales in the last 2 years",
					"Month", "Units sold", 0.5, 12.5, 0, 24000, Color.GRAY,
					Color.LTGRAY);
			renderer.setXLabels(1);
			renderer.setYLabels(10);
			renderer.addXTextLabel(1, "Jan");
			renderer.addXTextLabel(3, "Mar");
			renderer.addXTextLabel(5, "May");
			renderer.addXTextLabel(7, "Jul");
			renderer.addXTextLabel(10, "Oct");
			renderer.addXTextLabel(12, "Dec");
			renderer.setZoomEnabled(true, true); // Zoom 기능 On/Off
			renderer.setZoomRate(1.0f); // Zoom 비율
			renderer.setBarSpacing(0.2f); // 막대간 간격 설정
			renderer.setPanEnabled(false, false); // X,Y축 스크롤 여부 On/Off

			// 분루명 글자 크기 및 각 색상 지정
			int length = renderer.getSeriesRendererCount();
			for (int i = 0; i < length; i++) {
				SimpleSeriesRenderer seriesRenderer = renderer
						.getSeriesRendererAt(i);
				seriesRenderer.setDisplayChartValues(true); // 차트 내의 value들이 표현되는 부분
			}

			// 그래프 붙히는 부분 시작
			lv = (LinearLayout) findViewById(R.id.mainlinear);
			GraphicalView gv = (GraphicalView) ChartFactory.getBarChartView(
					getBaseContext(), buildBarDataset(titles, values), renderer,
					Type.DEFAULT);
			lv.addView(gv);
			// 그래프 붙히는 부분 종료
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

//		String[] titles = new String[] { "2007", "2008" };
//		List<double[]> values = new ArrayList<double[]>();
//		values.add(new double[] { elec, gas, water });
//		// values.add(new double[] { 5230, 7300, 9240, 10540, 7900, 9200, 12030,
//		// 11200, 9500, 10500, 11600, 13500 });
//		values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200,
//				22030, 21200, 19500, 15500, 12600, 14000 });
//		int[] colors = new int[] { Color.CYAN, Color.BLUE };
//
//		XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
//
//		renderer.setOrientation(Orientation.HORIZONTAL); // 그래프 방향
//		renderer.setChartValuesTextSize(40);
//
//		setChartSettings(renderer, "Monthly sales in the last 2 years",
//				"Month", "Units sold", 0.5, 12.5, 0, 24000, Color.GRAY,
//				Color.LTGRAY);
//		renderer.setXLabels(1);
//		renderer.setYLabels(10);
//		renderer.addXTextLabel(1, "Jan");
//		renderer.addXTextLabel(3, "Mar");
//		renderer.addXTextLabel(5, "May");
//		renderer.addXTextLabel(7, "Jul");
//		renderer.addXTextLabel(10, "Oct");
//		renderer.addXTextLabel(12, "Dec");
//		renderer.setZoomEnabled(true, true); // Zoom 기능 On/Off
//		renderer.setZoomRate(1.0f); // Zoom 비율
//		renderer.setBarSpacing(0.2f); // 막대간 간격 설정
//		renderer.setPanEnabled(false, false); // X,Y축 스크롤 여부 On/Off
//
//		// 분루명 글자 크기 및 각 색상 지정
//		int length = renderer.getSeriesRendererCount();
//		for (int i = 0; i < length; i++) {
//			SimpleSeriesRenderer seriesRenderer = renderer
//					.getSeriesRendererAt(i);
//			seriesRenderer.setDisplayChartValues(true); // 차트 내의 value들이 표현되는 부분
//		}
//
//		// 그래프 붙히는 부분 시작
//		lv = (LinearLayout) findViewById(R.id.mainlinear);
//		GraphicalView gv = (GraphicalView) ChartFactory.getBarChartView(
//				getBaseContext(), buildBarDataset(titles, values), renderer,
//				Type.DEFAULT);
//		lv.addView(gv);
//		// 그래프 붙히는 부분 종료
	}

	protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);

		int length = colors.length;
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			renderer.addSeriesRenderer(r);
		}
		return renderer;
	}

	// 설정 정보 설정 : 옵션(텍스트 크기 등등)등의 설정한 정보를 dataset 형태로 저장
	@SuppressWarnings("unused")
	protected XYMultipleSeriesDataset buildBarDataset(String[] titles,
			Double values) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			CategorySeries series = new CategorySeries(titles[i]);
//			Double v = values.get(i);
			Double v = Double.valueOf(i);
//			int seriesLength = v.length;
			int seriesLength = Double.SIZE;
			for (int k = 0; k < seriesLength; k++) {
//				series.add(v[k]);
				series.add(Double.valueOf(k));
			}
			dataset.addSeries(series.toXYSeries());
		}
		return dataset;
	}

	protected void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int axesColor,
			int labelsColor) {
		renderer.setChartTitle(title);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setAxesColor(axesColor);
		renderer.setLabelsColor(labelsColor);
	}
}
