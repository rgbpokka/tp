package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public abstract class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";

    private Name name;
    private Email email;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        email = personToCopy.getEmail();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public abstract PersonBuilder withName(String name);

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public abstract PersonBuilder withTags(String ... tags);

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public abstract PersonBuilder withEmail(String email);

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public abstract Person build();
}
