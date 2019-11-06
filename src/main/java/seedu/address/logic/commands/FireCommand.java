package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.mapping.TasMemMapping;
import seedu.address.model.mapping.exceptions.MappingNotFoundException;
import seedu.address.model.member.Member;
import seedu.address.model.member.MemberId;
import seedu.address.model.task.Task;

public class FireCommand extends Command {
    public static final String COMMAND_WORD = "fire";
    public static final String PREFIX_USAGE = "ti/ mi/";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a member indicated "
            + "by the index number used in the displayed task list, from the task indicated "
            + "by the task index. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TASK_INDEX + "TASK_INDEX"
            + PREFIX_MEMBER_ID + "MEMBER_ID \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_INDEX + " 2 "
            + PREFIX_MEMBER_ID + " JD";

    public static final String MESSAGE_REMOVE_TASK_SUCCESS = "Task removed from member";
    public static final String MESSAGE_NO_MAPPING = "This task does not exist under member.";

    private final Index taskId;
    private final MemberId memberId;

    /**
     * @param taskId   of the task in the filtered task list to be added to member
     * @param memberId of the member involved
     */
    public FireCommand(Index taskId, MemberId memberId) {
        requireNonNull(memberId);

        this.taskId = taskId;
        this.memberId = memberId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTasksList();
        List<Member> lastShownMemberList = model.getFilteredMembersList();

        if (taskId.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        boolean contains = false;
        Integer memberIndex = null;
        Member memberToRemove = null;

        for (int i = 0; i < lastShownMemberList.size(); i++) {
            if (lastShownMemberList.get(i).getId().equals(memberId)) {
                contains = true;
                memberIndex = i;
                memberToRemove = lastShownMemberList.get(i);
                break;
            }
        }

        if (!contains) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_ID);
        }

        Task involvedTask = lastShownTaskList.get(taskId.getZeroBased());
        TasMemMapping mappingToRemove = createMapping(taskId.getZeroBased(), memberIndex);

        try {
            model.deleteMapping(mappingToRemove);
        } catch (MappingNotFoundException e) {
            throw new CommandException(MESSAGE_NO_MAPPING);
        }

        return new CommandResult(String.format(MESSAGE_REMOVE_TASK_SUCCESS));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToUpdate}
     * where TaskStatus is updated to 'In Progress".
     */
    private static TasMemMapping createMapping(int taskIndex, int memberIndex) {
        return new TasMemMapping(taskIndex, memberIndex);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FireCommand)) {
            return false;
        }

        // state check
        FireCommand e = (FireCommand) other;
        return memberId.equals(e.memberId)
                && taskId.equals(e.taskId);
    }
}
