package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.TempPlace;

/**
 * Panel containing the list of places.
 */
public class ObservablePlaceListPanel extends UiPart<Region> {
    private static final String FXML = "ObservablePlaceListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ObservablePlaceListPanel.class);

    @FXML
    private TableView<TempPlace> frequencyTable;

    @FXML
    private TableColumn<TempPlace, String> placeColumn;

    @FXML
    private TableColumn<TempPlace, String> frequencyColumn;

    public ObservablePlaceListPanel(ObservableList<TempPlace> xs) {
        super(FXML);
        placeColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        frequencyColumn.setCellValueFactory(cellData -> cellData.getValue().getTimesProperty());
        frequencyTable.setItems(xs);
    }
}
