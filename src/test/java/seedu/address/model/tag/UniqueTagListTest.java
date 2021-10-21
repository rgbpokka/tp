package seedu.address.model.tag;

import org.junit.jupiter.api.Test;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

import java.util.List;

import static seedu.address.testutil.Assert.assertThrows;

public class UniqueTagListTest {

    @Test
    public void add_duplicateTag_throwsDuplicateTagException() {
        UniqueTagList tagList = new UniqueTagList();
        tagList.add(new Tag("Tag"));
        assertThrows(DuplicateTagException.class, () -> tagList.add(new Tag("Tag")));
    }

    @Test
    public void setTags_duplicateTags_throwsDuplicateTagException() {
        UniqueTagList tagList = new UniqueTagList();
        assertThrows(DuplicateTagException.class, () -> tagList.setTags(List.of(new Tag("Tag"), new Tag("Tag"))));
    }

    @Test
    public void remove_nonExistingTag_throwsTagNotFoundException() {
        UniqueTagList tagList = new UniqueTagList();
        assertThrows(TagNotFoundException.class, () -> tagList.remove(new Tag("Tag")));
    }

    @Test
    public void setTag() {
        UniqueTagList tagList = new UniqueTagList();
        Tag tag = new Tag("Tag");
        Tag secondTag = new Tag("Tag2");
        tagList.add(tag);
        tagList.add(secondTag);

        assertThrows(TagNotFoundException.class, () -> tagList.setTag(new Tag("newTag"), tag));
        assertThrows(DuplicateTagException.class, () -> tagList.setTag(tag, secondTag));
        
    }

}
