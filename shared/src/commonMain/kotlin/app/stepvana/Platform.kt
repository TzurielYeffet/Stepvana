package app.stepvana

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform