package com.tientoan.memolingo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform