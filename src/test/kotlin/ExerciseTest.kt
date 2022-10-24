import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import java.io.File

internal class ExerciseTest {

    private lateinit var testClass: Exercise

    @MockK
    private lateinit var view: Exercise.View

    @BeforeEach
    fun setUp() {

        MockKAnnotations.init(this)
        view = mock()
    }


//    private const val INVALID_MINUTE_TIME = "23:60"
//    private const val INVALID_MINUTE_NOT_INT_TIME = "ab:10"

    @Test
    fun `when the file is in the right format but time is not return error`() {
        // GIVEN
        val file = File(CORRECT_FILE).readText()
        var lines = file.lines()
        testClass = Exercise(lines, INVALID_HHMM_TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showError(any())
    }

    @Test
    fun `when the file is in the right format but time has invalid hour return error`() {
        // GIVEN
        val file = File(CORRECT_FILE).readText()
        var lines = file.lines()
        testClass = Exercise(lines, INVALID_HOUR_TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showError(any())
    }

    @Test
    fun `when the file is in the right format but time has hour not as int return error`() {
        // GIVEN
        val file = File(CORRECT_FILE).readText()
        var lines = file.lines()
        testClass = Exercise(lines, INVALID_HOUR_NOT_INT_TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showError(any())
    }

    @Test
    fun `when the file is in the right format but time has invalid minute return error`() {
        // GIVEN
        val file = File(CORRECT_FILE).readText()
        var lines = file.lines()
        testClass = Exercise(lines, INVALID_MINUTE_TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showError(any())
    }

    @Test
    fun `when the file is in the right format but time has minutes not as int return error`() {
        // GIVEN
        val file = File(CORRECT_FILE).readText()
        var lines = file.lines()
        testClass = Exercise(lines, INVALID_MINUTE_NOT_INT_TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showError(any())
    }


    @Test
    fun `when the path is correct but file is in the wrong format return error`() {
        // GIVEN
        val file = File(WRONG_FILE).readText()
        var lines = file.lines()
        testClass = Exercise(lines, CORREC_TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showError(any())
    }

    @Test
    fun `when path and file are correct show results`() {
        // GIVEN
        val file = File(CORRECT_FILE).readText()
        var lines = file.lines()
        testClass = Exercise(lines, CORREC_TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view, times(4)).showMessage(any())
    }

    @Test
    fun `when entry has no asterisk show correct format of time`() {
        // GIVEN
        val file = File(CORRECT_FILE_ASTERISK_NONE).readText()
        var lines = file.lines()
        testClass = Exercise(lines, CORREC_TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showMessage("1:30 tomorrow - /bin/run_me_daily")
    }

    @Test
    fun `when entry has asterisk on minute show correct format of time`() {
        // GIVEN
        val file = File(CORRECT_FILE_ASTERISK_MINUTES).readText()
        var lines = file.lines()
        testClass = Exercise(lines, CORREC_TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showMessage("19:00 today - /bin/run_me_sixty_times")
    }

    @Test
    fun `when entry has asterisk on hour show correct format of time`() {
        // GIVEN
        val file = File(CORRECT_FILE_ASTERISK_HOUR).readText()
        var lines = file.lines()
        testClass = Exercise(lines, CORREC_TIME, view)
        // WHEN
        testClass.resolve()
        // THEN
        verify(view).showMessage("16:45 today - /bin/run_me_hourly")
    }

    @Test
    fun `when entry has asterisk on both minutes and hours show correct format of time`() {
        // GIVEN
        val file = File(CORRECT_FILE_ASTERISK_BOTH).readText()
        var lines = file.lines()
        testClass = Exercise(lines, CORREC_TIME, view)
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
        private const val CORREC_TIME = "16:10"
        private const val INVALID_HHMM_TIME = "16:10:12"
        private const val INVALID_HOUR_TIME = "24:10"
        private const val INVALID_HOUR_NOT_INT_TIME = "ab:10"
        private const val INVALID_MINUTE_TIME = "23:60"
        private const val INVALID_MINUTE_NOT_INT_TIME = "ab:10"
    }
}