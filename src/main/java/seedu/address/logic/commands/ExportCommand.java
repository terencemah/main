package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.ExportFile;

/**
 * Exports application Life to CSV.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports application Life to CSV File specified. "
            + "Parameters: "
            + "g/GROUP_FILE_PATH"
            + " or l/LIFE_FILE_PATH";

    public static final String MESSAGE_SUCCESS = "Files exported:"
            + "\n"
            + "%1$s \n"
            + "%2$s";
    private final String groupFilePath;
    private final String lifeFilePath;

    /**
     * Creates an ImportCommand to add the specified {@code Person}
     */
    public ExportCommand(String lifeFilePath, String groupFilePath) {
        this.lifeFilePath = lifeFilePath;
        this.groupFilePath = groupFilePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (groupFilePath.isEmpty()) {
            new ExportFile().exportCsv(String.valueOf(model.getAddressBookFilePath()), lifeFilePath);
        } else if (lifeFilePath.isEmpty()) {
            new ExportFile().exportGroupCsv(String.valueOf(model.getAddressBookFilePath()), groupFilePath);
        } else {
            new ExportFile().exportCsv(String.valueOf(model.getAddressBookFilePath()), lifeFilePath);
            new ExportFile().exportGroupCsv(String.valueOf(model.getAddressBookFilePath()), groupFilePath);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, lifeFilePath, groupFilePath));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportCommand // instanceof handles nulls
                && lifeFilePath.equals(((ExportCommand) other).lifeFilePath));
    }
}
