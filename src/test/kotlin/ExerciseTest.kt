import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify

internal class ExerciseTest {

    private lateinit var testClass: Exercise

    @MockK
    private lateinit var view: Exercise.View

    @BeforeEach
    fun setUp() {

        MockKAnnotations.init(this)
        view = mock()
    }

    @Test
    fun `when the path is not correct return error`() {
        // GIVEN
        testClass = Exercise(WRONG_PATH, TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showError(any())
    }

    @Test
    fun `when the path is correct but file is in the wrong format return error`() {
        // GIVEN
        testClass = Exercise(WRONG_FILE, TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showError(any())
    }

    @Test
    fun `when path and file are correct show results`() {
        // GIVEN
        testClass = Exercise(CORRECT_FILE, TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view, times(4)).showMessage(any())
    }

    @Test
    fun `when entry has no asterisk show correct format of time`() {
        // GIVEN
        testClass = Exercise(CORRECT_FILE_ASTERISK_NONE, TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showMessage("1:30 tomorrow - /bin/run_me_daily")
    }

    @Test
    fun `when entry has asterisk on minute show correct format of time`() {
        // GIVEN
        testClass = Exercise(CORRECT_FILE_ASTERISK_MINUTES, TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showMessage("19:00 today - /bin/run_me_sixty_times")
    }

    @Test
    fun `when entry has asterisk on hour show correct format of time`() {
        // GIVEN
        testClass = Exercise(CORRECT_FILE_ASTERISK_HOUR, TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showMessage("16:45 today - /bin/run_me_hourly")
    }

    @Test
    fun `when entry has asterisk on both minutes and hours show correct format of time`() {
        // GIVEN
        testClass = Exercise(CORRECT_FILE_ASTERISK_BOTH, TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showMessage("16:10 today - /bin/run_me_every_minute")

    }


    companion object {
        private const val CORRECT_FILE = "src/main/resources/input.txt"
        private const val CORRECT_FILE_ASTERISK_NONE = "src/main/resources/input_asterisk_none.txt"
        private const val CORRECT_FILE_ASTERISK_HOUR = "src/main/resources/input_asterisk_hour.txt"
        private const val CORRECT_FILE_ASTERISK_MINUTES = "src/main/resources/input_asterisk_minutes.txt"
        private const val CORRECT_FILE_ASTERISK_BOTH = "src/main/resources/input_asterisk_both.txt"
        private const val WRONG_FILE = "src/main/resources/input_wrong_data.txt"
        private const val WRONG_PATH = "something/something/none.txt"
        private const val TIME = "16:10"
    }
}