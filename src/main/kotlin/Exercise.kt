import java.io.FileNotFoundException

class Exercise(
    private val lines: List<String>,
    private val time: String,
    private val view: View
) {

    fun resolve() {
        if (isFileValid() && isTimeValid()) {
            defineSolution()
        }
    }

    private fun isTimeValid(): Boolean {
        val chunks = time.split(":")
        if (chunks.count() != 2) {
            view.showError("Error while parsing time. Is it in HH:mm format? ")
            return false
        }
        try {
            if (chunks[0].toIntOrNull()!! > 23 || chunks[0].toIntOrNull()!! < 0) {
                view.showError("Error while parsing time. Invalid hour parsed")
                return false
            }
            if (chunks[1].toIntOrNull()!! > 59 || chunks[1].toIntOrNull()!! < 0) {
                view.showError("Error while parsing time. Invalid minute parsed")
                return false
            }
        } catch (e: NumberFormatException) {
            view.showError("Error while parsing time. One of the value is not a valid time value ")
            return false
        } catch (exception: NullPointerException) {
            view.showError("Error while parsing time. Value provided not a int")
            return false
        }

        return true
    }

    //    Check if the file exist and is in the right format
    //    (3 strings separated by a white space)
    private fun isFileValid(): Boolean {
        try {

            lines.forEach { line ->
                if (!line.isNullOrEmpty()) {
                    val chunks = line.split(" ")
                    if (chunks.count() != 3) {
                        view.showError("Error while parsing file, one of the lines is not in the correct format")
                        return false
                    }
                }
            }
            return true

        } catch (exception: NullPointerException) {
            view.showError("File null: " + exception.message.toString())
            return false
        } catch (exception: FileNotFoundException) {
            view.showError("Error on getting file: " + exception.message.toString())
            return false
        }
    }

    // Read each line from the file, format it (if any asterisk is present) and then send the formatted string
    private fun defineSolution() {

        lines.forEach {
            if (!it.isNullOrEmpty()) {
                val chunks = it.split(" ")
                val stdinTime = time.split(":")
                val hour = formatFileHour(chunks[1], stdinTime[0])
                val minute = formatFileMinutes(chunks[0], chunks[1], stdinTime[1])
                val solution = calculateWhen(hour, minute, time)
                view.showMessage("$hour:$minute $solution - ${chunks[2]}")
            }
        }
    }

    // Format the hour given from the file
    // if is ASTERISK then return closest hour otherwise return value
    private fun formatFileHour(value: String, time: String): String {
        return if (value == ASTERISK) time else value
    }

    //     Format the minutes given form the file
//     if both hour and minutes are ASTERISK then the stdin time is the closest one
//     if only minutes are ASTERISK then return 00
//     else return the minutes  value from the file
    private fun formatFileMinutes(minutes: String, hours: String, time: String): String {
        return if (minutes == ASTERISK && hours == ASTERISK) {
            time
        } else if (minutes == ASTERISK && hours != ASTERISK) {
            DOUBLE_ZERO
        } else {
            minutes
        }
    }

    private fun calculateWhen(hour: String, minute: String, time: String): String {
        val givenTime = time.split(":")
        return if (givenTime[0].toInt() > hour.toInt()) {
            TOMORROW
        } else if (givenTime[0].toInt() == hour.toInt()) {
            if (givenTime[1].toInt() > minute.toInt()) {
                TOMORROW
            } else {
                TODAY
            }
        } else {
            TODAY
        }

    }

    interface View {
        fun showError(message: String)
        fun showMessage(message: String)
    }

    companion object {
        const val TOMORROW = "tomorrow"
        const val TODAY = "today"
        const val ASTERISK = "*"
        const val DOUBLE_ZERO = "00"
    }


}