package seedu.address.storage;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Converts CSV files to be added to current Life.
 */
public class ImportFile {

    /**
     * Reads a CSV File and returns a list of person to be added to the current address book.
     *
     * @param fileName path of CSV file to be imported.
     * @return List of person to be imported.
     * @throws CommandException if person in CSV file does not conform to format.
     */
    public List<Person> importCsv(String fileName) throws CommandException {
        try {
            File csvFile = new File(fileName);
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = CsvSchema.emptySchema().withHeader();
            MappingIterator<Map<String, String>> it =
                    mapper.readerFor(Map.class).with(schema).readValues(csvFile);
            List<Person> people = new ArrayList<>();
            while (it.hasNext()) {
                Map<String, String> onePerson = it.next();
                String oneName = onePerson.get("name");
                String onePhone = onePerson.get("phone");
                String oneEmail = onePerson.get("email");
                String oneAddress = onePerson.get("address");
                String oneTagged = onePerson.get("tagged").strip();
                String[] tags = oneTagged.split(",");

                Set<Tag> tag;
                if (oneTagged.isEmpty()) {
                    tag = ParserUtil.parseTags(new ArrayList<>());
                } else {
                    tag = ParserUtil.parseTags(Arrays.asList(tags));
                }
                Name name = ParserUtil.parseName(oneName);
                Phone phone = ParserUtil.parsePhone(onePhone);
                Email email = ParserUtil.parseEmail(oneEmail);
                Address address = ParserUtil.parseAddress(oneAddress);

                Person person = new Person(name, phone, email, address, tag);
                people.add(person);
            }
            return people;
        } catch (IOException | ParseException ioe) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT));
        }
    }
}
