class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {


            val view = ViewImplementation()

            val input = generateSequence(::readLine)
            val lines = input.toList()

            val exercise = Exercise(lines, args[0], view)
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
