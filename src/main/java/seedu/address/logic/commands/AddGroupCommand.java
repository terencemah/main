package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
//
//import java.util.ArrayList;
//import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
//import seedu.address.model.person.Person;

/**
 * Represents the command to add a new group to CoderLifeInsights.
 */
public class AddGroupCommand extends Command {

    public static final String COMMAND_WORD = "add_group";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD
                    + ": Adds a new social group with given name and "
                    + "given (if any) person IDs as members"
                    + PREFIX_NAME
                    + "Name: "
                    + "["
                    + PREFIX_MEMBER
                    + " Members]...\n"
                    + "Example: "
                    + COMMAND_WORD
                    + " "
                    + PREFIX_NAME
                    + "SoC Friends "
                    + PREFIX_MEMBER
                    + "1 "
                    + PREFIX_MEMBER
                    + "3 "
                    + PREFIX_MEMBER
                    + "7";

    public static final String MESSAGE_SUCCESS = "New group added: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUP =
            "Group with given name already exists. " + "Please try again with another name";
    public static final String MESSAGE_PERSON_DOES_NOT_EXIST = "Person(s) with given Id does not exist";
    private static final Logger logger = LogsCenter.getLogger(AddGroupCommand.class);
    private final Group toAdd;

    public AddGroupCommand(Group group) {
        requireNonNull(group);
        this.toAdd = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasGroup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

//        List<Person> lastShownList = model.getFilteredPersonList();
//        ArrayList<Integer> members = toAdd.getMembers();
//        for (int i = 0; i < members.size(); i++) {
//            logger.info("current member being checked is" + members.get(i));
//            if (!lastShownList.contains(members.get(i))) {
//                throw new CommandException(MESSAGE_PERSON_DOES_NOT_EXIST);
//            }
//        }

        model.addGroup(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), ViewType.GROUPS);
    }
}
