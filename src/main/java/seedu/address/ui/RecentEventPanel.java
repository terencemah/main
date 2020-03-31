package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.RecentEvent;

public class RecentEventPanel extends UiPart<Region> {
    private static final String FXML = "RecentEventPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PlaceListPanel.class);

    @javafx.fxml.FXML
    private TableView<RecentEvent> eventTable;

    @FXML
    private TableColumn<RecentEvent, String> placeColumn;

    @FXML
    private TableColumn<RecentEvent, String> activityColumn;

    public RecentEventPanel(ObservableList<RecentEvent> list) {
        super(FXML);
        placeColumn.setCellValueFactory(cellData -> cellData.getValue().placeProperty());
        activityColumn.setCellValueFactory(cellData -> cellData.getValue().activityProperty());
        eventTable.setItems(list);
    }
}