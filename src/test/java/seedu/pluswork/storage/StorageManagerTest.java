package seedu.pluswork.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.pluswork.commons.core.GuiSettings;
import seedu.pluswork.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonProjectDashboardStorage projectDashboardStorage = new JsonProjectDashboardStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(projectDashboardStorage, userPrefsStorage, storageManager);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    /*@Test
    public void projectDashboardReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonProjectDashboardStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonProjectDashboardStorageTest} class.
         */
        /*ProjectDashboard original = getTypicalProjectDashboard();
        storageManager.saveProjectDashboard(original);
        ReadOnlyProjectDashboard retrieved = storageManager.readProjectDashBoard().get();
        assertEquals(original, new ProjectDashboard(retrieved));
    }*/

    @Test
    public void getProjectDashboardFilePath() {
        assertNotNull(storageManager.getProjectDashboardFilePath());
    }

}
