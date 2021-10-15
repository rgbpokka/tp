package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a stats page
 */
public class StatisticsWindow extends UiPart<Stage> {

    public static final String STATS_MESSAGE = "To be implemented.";

    private static final Logger logger = LogsCenter.getLogger(StatisticsWindow.class);
    private static final String FXML = "StatisticsWindow.fxml";

    @FXML
    private Label statsMessage;

    /**
     * Creates a new StatisticsWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public StatisticsWindow(Stage root) {
        super(FXML, root);
        statsMessage.setText(STATS_MESSAGE);
    }

    /**
     * Creates a new StatisticsWindow.
     */
    public StatisticsWindow() {
        this(new Stage());
    }

    /**
     * Shows the stats window.
     */
    public void show() {
        logger.fine("Showing stats page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the stats window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the stats window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the stats window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
