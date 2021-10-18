package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Tag> filteredTags;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTags = new FilteredList<>(this.addressBook.getTagList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        deleteTagAssociatedToPerson(target);
        addressBook.removePerson(target);
    }

    private void deleteTagAssociatedToPerson(Person target) {
        Set<Tag> tags = target.getTags();

        for (Tag tag : tags) {
            tag.removePerson(target);
            if (tag.noTaggedPerson()) {
                this.deleteTag(tag);
            }
        }
    }

    @Override
    public void addPerson(Person person) {
        addTagAssociatedToPerson(person);
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }
    
    private void addTagAssociatedToPerson(Person toAdd) {
        Set<Tag> tags = toAdd.getTags();
        Set<Tag> newTags = new HashSet<>();

        for (Tag tag : tags) {
            if (!addressBook.hasTag(tag)) {
                addressBook.addTag(tag);
                newTags.add(tag);
            } else {
                newTags.add(addressBook.getTag(tag));
            }
        }

        toAdd.setTags(newTags);

        for (Tag tag : newTags) {
            tag.addPerson(toAdd);
        }
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        setTagsAssociatedToPerson(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }
    
    private void setTagsAssociatedToPerson(Person personToEdit, Person editedPerson) {
        Set<Tag> tags = editedPerson.getTags();
        Set<Tag> newTags = new HashSet<>();

        for (Tag tag : tags) {
            if (!addressBook.hasTag(tag)) {
                addressBook.addTag(tag);
                newTags.add(tag);
            } else {
                newTags.add(addressBook.getTag(tag));
            }
        }

        for (Tag tag : newTags) {
            tag.addPerson(editedPerson);
        }

        editedPerson.setTags(newTags);

        Set<Tag> deletedTags = personToEdit.getTags();

        for (Tag tag : deletedTags) {
            tag.removePerson(personToEdit);
            if (tag.noTaggedPerson()) {
                addressBook.removeTag(tag);
            }
        }
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return addressBook.hasTag(tag);
    }

    @Override
    public void deleteTag(Tag target) {
        addressBook.removeTag(target);
    }

    @Override
    public void addTag(Tag tag) {
        addressBook.addTag(tag);
        updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
    }

    @Override
    public Tag getTag(Tag tag) {
        return addressBook.getTag(tag);
    }

    @Override
    public void setTag(Tag target, Tag editedTag) {
        requireAllNonNull(target, editedTag);
        addressBook.setTag(target, editedTag);
    }

    //=========== Filtered Person/Tag List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public ObservableList<Tag> getFilteredTagList() {
        return filteredTags;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredTagList(Predicate<Tag> predicate) {
        requireNonNull(predicate);
        filteredTags.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;

        System.out.println(addressBook.equals(other.addressBook));
        System.out.println(userPrefs.equals(other.userPrefs));
        System.out.println(filteredPersons.equals(other.filteredPersons));
        System.out.println(filteredTags.equals(other.filteredTags));
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredTags.equals(other.filteredTags);
    }

}
