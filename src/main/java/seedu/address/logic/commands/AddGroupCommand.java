package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

public class AddGroupCommand extends Command {

    public static final String COMMAND_WORD = "add_group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new social group with given name and "
            + "given (if any) person IDs as members"
            + PREFIX_NAME + "Name: "
            + "[" + PREFIX_MEMBER + " Members]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "SoC Friends "
            + PREFIX_MEMBER + "1"
            + PREFIX_MEMBER + "3"
            + PREFIX_MEMBER + "7";

    public static final String MESSAGE_SUCCESS = "New group added: %1$";
    public static final String MESSAGE_DUPLICATE_GROUP = "Group with given name already exists. " +
            "Please try again with another name";

    private final Group toAdd;

    public AddGroupCommand(Group group) {
        requireNonNull(group);
        this.toAdd = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if(model.hasGroup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.addGroup(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

}
