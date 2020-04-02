package seedu.address.ui;

import java.util.Stack;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;

public class TimePieChart extends UiPart<Region> {

    private static final String FXML = "TimePieChartPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TimePieChart.class);

    @FXML
    private StackPane timePieChartPane;

    public TimePieChart() {
        super(FXML);
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Grapefruit", 13),
                        new PieChart.Data("Oranges", 25),
                        new PieChart.Data("Plums", 10),
                        new PieChart.Data("Pears", 22),
                        new PieChart.Data("Apples", 30));
        final PieChart timePieChart = new PieChart(pieChartData);
        timePieChart.setTitle("Imported Fruits");
        timePieChartPane.getChildren().add(timePieChart);
    }
}
