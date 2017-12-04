package vladyagl.regexp

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main(args: Array<String>) {
    val files = args.filter { it[0] != '-' }.map(::File)

    files.forEach { file ->
        BufferedReader(FileReader(file)).use { reader ->
            val parser = Parser(reader)
            val tree = parser.parse()
            println()
            println(tree)

            Visualizator().visualize(tree)
        }
    }
}

