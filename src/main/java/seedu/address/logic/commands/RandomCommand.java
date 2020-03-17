package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import java.util.Random;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.UniquePersonList;

/**
 *
 */
public class RandomCommand extends Command {



    public static final String COMMAND_WORD = "random";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": ";

    public static final String MESSAGE_SUCCESS = "Random person selected.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_SUCCESS);
    }
}
