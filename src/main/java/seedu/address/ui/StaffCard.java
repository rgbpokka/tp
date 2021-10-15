package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Staff;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class StaffCard extends UiPart<Region> {

    private static final String FXML = "StaffCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Staff staff;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label staffId;
    @FXML
    private ImageView addressIcon;
    @FXML
    private ImageView emailIcon;
    @FXML
    private ImageView phoneIcon;
    @FXML
    private ImageView staffIdIcon;

    /**
     * Creates a {@code StaffCode} with the given {@code Staff} and index to display.
     */
    public StaffCard(Staff staff) {
        super(FXML);
        this.staff = staff;
        staffId.setText("[" + staff.getStaffId().value + "]");
        name.setText(staff.getName().fullName);
        email.setText(" " + staff.getEmail().value);
        address.setText(" " + staff.getAddress().value);
        phone.setText(" " + staff.getPhone().value);

        staffIdIcon.setImage(new Image(getClass().getResourceAsStream("/images/staff_id_icon.png")));
        emailIcon.setImage(new Image(getClass().getResourceAsStream("/images/email_icon.png")));
        addressIcon.setImage(new Image(getClass().getResourceAsStream("/images/address_icon.png")));
        phoneIcon.setImage(new Image(getClass().getResourceAsStream("/images/phone_icon.png")));

        staff.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StaffCard)) {
            return false;
        }

        // state check
        StaffCard card = (StaffCard) other;
        return staffId.getText().equals(card.staffId.getText())
                && staff.equals(card.staff);
    }
}
