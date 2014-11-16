package se.bettercode.scrum.gui;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import se.bettercode.scrum.backlog.BurnupDay;


public class BurnupChart extends AreaChart<Number, Number> {

    XYChart.Series totalSeries = new XYChart.Series();
    XYChart.Series doneSeries = new XYChart.Series();
    static final double lowerBound = 0;
    static final double yUpperBound = 12;
    static final double tickUnit = 1;

    public BurnupChart() {
        super(new NumberAxis(lowerBound, yUpperBound, tickUnit), new NumberAxis());
        getYAxis().setLabel("Points");
        getYAxis().setTickLength(1);
        getXAxis().setLabel("Days");
        setTitle("Burnup");

        totalSeries.setName("Total");
        doneSeries.setName("Done");

        getData().addAll(doneSeries, totalSeries);

    }

    public void bindBurnupDaysProperty(ListProperty<BurnupDay> burnupDays) {
        burnupDays.get().addListener(new ListChangeListener<BurnupDay>() {
            @Override
            public void onChanged(Change<? extends BurnupDay> c) {
                Platform.runLater(() -> {
                    while (c.next()) {
                        for (BurnupDay burnupDay : c.getAddedSubList()) {
                            getData().get(0).getData().add(makeDoneSeriesData(burnupDay));
                            getData().get(1).getData().add(makeTotalSeriesData(burnupDay));
                        }
                    }
                });
            }
        });
    }

    private Data makeTotalSeriesData(BurnupDay burnupDay) {
        return new Data(burnupDay.getDay(), burnupDay.getTotal());
    }

    private Data makeDoneSeriesData(BurnupDay burnupDay) {
        return new Data(burnupDay.getDay(), burnupDay.getDone());
    }

}
