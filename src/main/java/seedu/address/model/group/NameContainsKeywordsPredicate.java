package seedu.address.model.group;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
/**
 * Checks if a {@code Group}'s {@code Name} matches any of the given keywords.
 */
public class NameContainsKeywordsPredicate implements Predicate<Group> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Group group) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(group.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        NameContainsKeywordsPredicate nameContainsKeywordsPredicate = (NameContainsKeywordsPredicate) other;
        return this.keywords.equals(nameContainsKeywordsPredicate.keywords);
    }
}
