package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.CsvFile;

/**
 * Imports application csv to the CoderLifeInsights.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD
                    + ": Imports application csv to the CoderLifeInsights. "
                    + "Parameters: "
                    + "FILE_PATH";

    public static final String MESSAGE_SUCCESS = "CSV Imported: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON =
            "Person already exists in the address book. "
                    + "Please delete duplicate in your import file/current address book.";
    private final String filePath;

    /**
     * Creates an ImportCommand to add the specified {@code Person}
     */
    public ImportCommand(String filePath) {
        requireNonNull(filePath);
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> importedPeople = new CsvFile().importCsv(filePath);
        if (model.hasPersons(importedPeople)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        model.importCsvToAddressBook(importedPeople);
        return new CommandResult(String.format(MESSAGE_SUCCESS, filePath));
    }

    //    @Override
    //    public boolean equals(Object other) {
    //        return other == this // short circuit if same object
    //                || (other instanceof AddCommand // instanceof handles nulls
    //                && toAdd.equals(((AddCommand) other).toAdd));
    //    }
}
