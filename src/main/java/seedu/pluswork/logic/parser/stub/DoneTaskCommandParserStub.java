package seedu.pluswork.logic.parser.stub;

import seedu.pluswork.commons.core.index.Index;
import seedu.pluswork.logic.commands.DoneTaskCommand;
import seedu.pluswork.logic.parser.Parser;
import seedu.pluswork.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class DoneTaskCommandParserStub implements Parser<DoneTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DoneTaskCommand parse(String args) throws ParseException {
        Index stubIndex1 = Index.fromOneBased(3);
        return new DoneTaskCommand(stubIndex1);
    }
}