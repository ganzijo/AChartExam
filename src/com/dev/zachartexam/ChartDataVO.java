package com.dev.zachartexam;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import lombok.Getter;
import lombok.Setter;

public class ChartDataVO {

	private Data data = new Data();

	@Getter
	@Setter
	public static class Title {
		private String title;
		private Integer color;
		private List<Double> values = new ArrayList<Double>();
	}

	@Getter
	@Setter
	public static class Setting {
		private String title;
		private String axisMin;
		private String axisMax;
		private String labels;
		private SortedMap<String, String> textLabel = new TreeMap<String, String>();
	}

	@Getter
	@Setter
	public static class Data {
		private List<Title> titles = new ArrayList<ChartDataVO.Title>();
		private String chartTitle;
		private Setting x;
		private Setting y;
		private String axesColor;
		private String labelsColor;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}