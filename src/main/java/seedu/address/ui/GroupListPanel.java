package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.group.Group;

/**
 * Panel containing the list of groups.
 */
public class GroupListPanel extends UiPart<Region> {
    private static final String FXML = "GroupListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GroupListPanel.class);

    @FXML
    private ListView<Group> groupListView;

    public GroupListPanel(ObservableList<Group> groupList) {
        super(FXML);
        groupListView.setItems(groupList);
        groupListView.setCellFactory(listView -> new GroupListViewCell());
    }

    /**
     * Represents a cell to view the Group in a list.
     */
    class GroupListViewCell extends ListCell<Group> {
        @Override
        protected void updateItem(Group group, boolean empty) {
            super.updateItem(group, empty);

            if (empty || group == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new GroupCard(group, group.getGroupId()).getRoot());
            }
        }
    }
}


