package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INVENTORY_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INVENTORY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditInventoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditInventoryCommand object
 */
public class EditInventoryCommandParser implements Parser<EditInventoryCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditInventoryCommand
     * and returns an EditInventoryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditInventoryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INVENTORY_NAME, PREFIX_INVENTORY_PRICE,
                                                PREFIX_TASK_INDEX, PREFIX_MEMBER_ID);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                        EditInventoryCommand.MESSAGE_USAGE), pe);
        }

        EditInventoryCommand.EditInventoryDescriptor editInventoryDescriptor =
                                                new EditInventoryCommand.EditInventoryDescriptor();

        if (argMultimap.getValue(PREFIX_INVENTORY_NAME).isPresent()) {
            editInventoryDescriptor
                    .setName(ParserUtil.parseInvName(argMultimap.getValue(PREFIX_INVENTORY_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_INVENTORY_PRICE).isPresent()) {
            editInventoryDescriptor
                    .setPrice(ParserUtil.parsePrice((argMultimap.getValue(PREFIX_INVENTORY_PRICE).get())));
        }
        if (argMultimap.getValue(PREFIX_TASK_INDEX).isPresent()) {
            editInventoryDescriptor
                    .setTaskId(ParserUtil.parseIndex((argMultimap.getValue(PREFIX_TASK_INDEX).get())));
        }
        if (argMultimap.getValue(PREFIX_MEMBER_ID).isPresent()) {
            editInventoryDescriptor
                    .setMemId(ParserUtil.parseMemberId((argMultimap.getValue(PREFIX_MEMBER_ID).get())));
        }


        if (!editInventoryDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditInventoryCommand.MESSAGE_NOT_EDITED);
        }

        return new EditInventoryCommand(index, editInventoryDescriptor);
    }

}