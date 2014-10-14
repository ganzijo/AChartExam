package com.dev.zachartexam;

import java.util.List;
import java.util.SortedMap;

public class ChartDataVO {
	
	private Data data = new Data();

	public static class Data {

		private String xTitle;
		private String yTitle;
		private List<Double> xValue;
		private List<Double> yValue;
		private String chartTitle;
		private String XTitle;
		private String YTitle;
		private String XAxisMin;
		private String XAxisMax;
		private String YAxisMin;
		private String YAxisMax;
		private int axesColor;
		private int labelsColor;
		private String xLabels;
		private String yLabels;
		private SortedMap<String, String> xTextLabel;
		private SortedMap<String, String> yTextLabel;
		
		public String getxTitle() {
			return xTitle;
		}
		public void setxTitle(String xTitle) {
			xTitle = "X축 타이틀";
			this.xTitle = xTitle;
		}
		public String getyTitle() {
			return yTitle;
		}
		public void setyTitle(String yTitle) {
			yTitle = "Y축 타이틀";
			this.yTitle = yTitle;
		}
		public List<Double> getxValue() {
			return xValue;
		}
		public void setxValue(List<Double> xValue) {
			xValue.add(1,100.0);
			this.xValue = xValue;
		}
		public List<Double> getyValue() {
			return yValue;
		}
		public void setyValue(List<Double> yValue) {
			yValue.add(1,100.0);
			this.yValue = yValue;
		}
		public String getChartTitle() {
			return chartTitle;
		}
		public void setChartTitle(String chartTitle) {
			chartTitle = "차트 타이틀";
			this.chartTitle = chartTitle;
		}
		public String getXTitle() {
			return XTitle;
		}
		public void setXTitle(String xTitle) {
			XTitle = xTitle;
		}
		public String getYTitle() {
			return YTitle;
		}
		public void setYTitle(String yTitle) {
			YTitle = yTitle;
		}
		public String getXAxisMin() {
			return XAxisMin;
		}
		public void setXAxisMin(String xAxisMin) {
			XAxisMin = xAxisMin;
		}
		public String getXAxisMax() {
			return XAxisMax;
		}
		public void setXAxisMax(String xAxisMax) {
			XAxisMax = xAxisMax;
		}
		public String getYAxisMin() {
			return YAxisMin;
		}
		public void setYAxisMin(String yAxisMin) {
			YAxisMin = yAxisMin;
		}
		public String getYAxisMax() {
			return YAxisMax;
		}
		public void setYAxisMax(String yAxisMax) {
			YAxisMax = yAxisMax;
		}
		public int getAxesColor() {
			return axesColor;
		}
		public void setAxesColor(int axesColor) {
			this.axesColor = axesColor;
		}
		public int getLabelsColor() {
			return labelsColor;
		}
		public void setLabelsColor(int labelsColor) {
			this.labelsColor = labelsColor;
		}
		public String getxLabels() {
			return xLabels;
		}
		public void setxLabels(String xLabels) {
			this.xLabels = xLabels;
		}
		public String getyLabels() {
			return yLabels;
		}
		public void setyLabels(String yLabels) {
			this.yLabels = yLabels;
		}
		public SortedMap<String, String> getxTextLabel() {
			return xTextLabel;
		}
		public void setxTextLabel(SortedMap<String, String> xTextLabel) {
			xTextLabel.put("1", "X축 라벨항목");
			this.xTextLabel = xTextLabel;
		}
		public SortedMap<String, String> getyTextLabel() {
			return yTextLabel;
		}
		public void setyTextLabel(SortedMap<String, String> yTextLabel) {
			yTextLabel.put("1", "Y축 라벨항목");
			this.yTextLabel = yTextLabel;
		}
		
		
		
		
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	

}