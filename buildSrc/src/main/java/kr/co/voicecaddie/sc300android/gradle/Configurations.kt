package kr.co.voicecaddie.sc300android.gradle

object Configurations {
    // 개발 중 인 경우 : true
    // 스토어 배포하는 경우 : false
    const val isDevelopMode = true

    const val targetSdk = 32
    const val minSdk = 26
    const val compileSdk = 32
    const val versionCode = 1
    const val majorVersion = 0
    const val minorVersion = 0
    const val patchVersion = 0
    const val developVersion = 1


    @JvmStatic
    fun getVersionName(): String {
        return if (isDevelopMode) {
            "$majorVersion.$minorVersion.$patchVersion.$developVersion"
        } else {
            "$majorVersion.$minorVersion.$patchVersion"
        }
    }
}