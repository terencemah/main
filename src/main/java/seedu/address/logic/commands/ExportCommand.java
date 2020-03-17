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
            + "FILE_PATH";

    public static final String MESSAGE_SUCCESS = "Life exported: %1$s";
    private final String filePath;

    /**
     * Creates an ImportCommand to add the specified {@code Person}
     */
    public ExportCommand(String filePath) {
        requireNonNull(filePath);
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        new ExportFile().exportCsv(String.valueOf(model.getAddressBookFilePath()), filePath);
        return new CommandResult(String.format(MESSAGE_SUCCESS, filePath));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportCommand // instanceof handles nulls
                && filePath.equals(((ExportCommand) other).filePath));
    }
}
