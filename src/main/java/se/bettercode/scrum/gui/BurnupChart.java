package se.bettercode.scrum.gui;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class BurnupChart extends AreaChart<Number, Number> {

    public BurnupChart() {
        super(new NumberAxis(1, 31, 1), new NumberAxis());
        setTitle("Burnup");

        XYChart.Series totalSeries= new XYChart.Series();
        XYChart.Series doneSeries= new XYChart.Series();
        totalSeries.setName("Total");
        doneSeries.setName("Done");

        doneSeries.getData().add(new XYChart.Data(1, 4));
        doneSeries.getData().add(new XYChart.Data(3, 10));
        doneSeries.getData().add(new XYChart.Data(6, 15));
        doneSeries.getData().add(new XYChart.Data(9, 8));
        doneSeries.getData().add(new XYChart.Data(12, 5));
        doneSeries.getData().add(new XYChart.Data(15, 18));
        doneSeries.getData().add(new XYChart.Data(18, 15));
        doneSeries.getData().add(new XYChart.Data(21, 13));
        doneSeries.getData().add(new XYChart.Data(24, 19));
        doneSeries.getData().add(new XYChart.Data(27, 21));
        doneSeries.getData().add(new XYChart.Data(30, 21));

        totalSeries.getData().add(new Data(1, 22));
        totalSeries.getData().add(new Data(3, 22));
        totalSeries.getData().add(new Data(6, 22));
        totalSeries.getData().add(new Data(9, 22));
        totalSeries.getData().add(new Data(12, 22));
        totalSeries.getData().add(new Data(15, 22));
        totalSeries.getData().add(new Data(18, 22));
        totalSeries.getData().add(new Data(21, 22));
        totalSeries.getData().add(new Data(24, 22));
        totalSeries.getData().add(new Data(27, 22));
        totalSeries.getData().add(new Data(30, 22));

        getData().addAll(doneSeries, totalSeries);

    }

}
