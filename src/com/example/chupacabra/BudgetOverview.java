package com.example.chupacabra;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class BudgetOverview extends Activity{

	Context context;
	GraphicalView myPieChart;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.budgetoverview);

		double[] values = new double[] { 12, 14, 11, 10, 19 };
		int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN };
		DefaultRenderer renderer = buildCategoryRenderer(colors);
		//renderer.setZoomButtonsVisible(true);
		//renderer.setZoomEnabled(true);
		renderer.setChartTitleTextSize(20);
		renderer.setBackgroundColor(Color.WHITE);
		renderer.setApplyBackgroundColor(true);
		renderer.setLabelsColor(Color.BLACK);
		//renderer.setFitLegend(true);
		renderer.setShowLegend(false);
		renderer.setScale((float) 0.5);
		renderer.setStartAngle((float) 0.5);
		TableLayout layout = (TableLayout) findViewById(R.id.tableLayout);
		myPieChart=ChartFactory.getPieChartView(context, buildCategoryDataset("Project budget", values),
				renderer);
		layout.addView(myPieChart, new LayoutParams
				(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		layout.refreshDrawableState();


	}

	//OnResume to fix for datachanges later

	protected CategorySeries buildCategoryDataset(String title, double[] values) {
		CategorySeries series = new CategorySeries(title);
		int k = 0;
		String[] categories = {"Entertainment", "Bills", "Groceries", "Junk Food", "Gas"}; 
		for (double value : values) {
			series.add(categories[k++ % categories.length], value);
		}

		return series;
	}


	protected DefaultRenderer buildCategoryRenderer(int[] colors) {
		DefaultRenderer renderer = new DefaultRenderer();
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setMargins(new int[] { 20, 30, 15, 0 });
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}
		return renderer;
	}

}
