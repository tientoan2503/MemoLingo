package com.tientoan.memolingo

fun logHighlight(message: String) {
    val yellow = "\u001b[33m"
    val reset = "\u001b[0m"
    val border = "=".repeat(message.length + 10)

    println(
        """
        $yellow
        $border
        🚀 [LOG]: $message
        $border
        $reset
        """.trimIndent(),
    )
}
