class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val view = ViewImplementation()

            // The real one in order to do stdin
            val exercise = Exercise(args[0], args[1], view)

            exercise.resolve()
        }
    }

    class ViewImplementation : Exercise.View {

        // Init the interface
        override fun showError(message: String) {
            println(message)
        }

        override fun showMessage(message: String) {
            println(message)

        }

    }


}
