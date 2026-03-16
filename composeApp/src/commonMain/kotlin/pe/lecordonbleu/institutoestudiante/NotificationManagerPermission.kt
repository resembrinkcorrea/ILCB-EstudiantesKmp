package pe.lecordonbleu.institutoestudiante

expect class NotificationManagerPermission(
    platformContext: Any?
) {
    fun requestPermission(enable: Boolean)
}
