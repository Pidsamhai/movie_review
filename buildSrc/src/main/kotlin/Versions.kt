import java.time.LocalDateTime
import java.time.ZoneId

object Versions {
    const val Compose = "1.0.1"
    const val Hilt = "2.36"
    const val HiltNavigationCompose = "1.0.0-alpha03"
    const val CoreKtx = "1.6.0"
    const val AppCompat = "1.3.1"

    const val Material = "1.3.0"
    const val Lifecycle = "2.3.1"
    const val ActivityCompose = "1.3.1"
    const val Junit = "4.13.2"
    const val AndroidxJunit = "1.1.2"
    const val Espresso = "3.3.0"
    const val NavigationCompose = "2.4.0-alpha04"
    const val Accompanist = "0.16.0"

    const val Ktor = "1.6.0"
    const val KotlinxSerialization = "1.2.1"
    const val Okhttp = "4.7.2"
    const val GitVersion = "0.4.13"
    const val ObjectBox = "2.9.2-RC3"

    const val Timber = "4.7.1"
    const val Kotlin = "1.5.21"
    const val AGT = "7.1.0-alpha08"
    const val ComposePaging = "1.0.0-alpha12"
    const val LottieCompose = "4.0.0"
    const val ConstraintLayoutCompose = "1.0.0-beta02"
    const val KotlinxDatetime = "0.2.1"
    const val Coil = "1.3.2"

    fun versionCode(): Int {
        val now = LocalDateTime.now(ZoneId.of("Asia/Bangkok"))
        return "%s%02d%02d%02d".format(
            now.year,
            now.monthValue,
            now.hour,
            now.minute
        ).toInt()
    }
}
