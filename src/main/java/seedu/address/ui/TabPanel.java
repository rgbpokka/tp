package seedu.address.ui;

import javafx.scene.layout.Region;
import javafx.fxml.FXML;

import java.util.function.Consumer;

public class TabPanel extends UiPart<Region> {
    
    private static final String FXML = "TabPanel.fxml";
    
    private Consumer<String> consumer;
    
    public TabPanel(Consumer<String> consumer) {
        super(FXML);
        this.consumer = consumer;
    }

    @FXML
    private void handleClickVendorTab() {
        consumer.accept(VendorListPanel.TAB_NAME);
    }

    @FXML
    private void handleClickGuestTab() {
        consumer.accept(GuestListPanel.TAB_NAME);
    }
    
}
