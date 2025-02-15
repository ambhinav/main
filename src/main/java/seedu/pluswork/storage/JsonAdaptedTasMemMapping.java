package seedu.pluswork.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.pluswork.commons.exceptions.IllegalValueException;
import seedu.pluswork.model.mapping.TasMemMapping;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTasMemMapping {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Mapping's %s field is missing!";

    private final int taskIndex;
    private final int memberIndex;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTasMemMapping(@JsonProperty("task") int taskIndex,
                                    @JsonProperty("member") int memberIndex) {
        this.taskIndex = taskIndex;
        this.memberIndex = memberIndex;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTasMemMapping(TasMemMapping source) {
        taskIndex = source.getTaskIndex();
        memberIndex = source.getMemberIndex();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public TasMemMapping toModelType() throws IllegalValueException {
        return new TasMemMapping(taskIndex, memberIndex);
    }
}
