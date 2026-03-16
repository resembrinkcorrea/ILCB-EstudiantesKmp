package pe.lecordonbleu.institutoestudiante

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform