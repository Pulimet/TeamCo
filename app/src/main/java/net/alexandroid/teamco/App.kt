package net.alexandroid.teamco

import android.app.Application
import net.alexandroid.shpref.ShPref
import net.alexandroid.teamco.di.KoinLogs
import net.alexandroid.teamco.di.appModule
import net.alexandroid.utils.mylog.MyLog
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        MyLog.init(this, "QAZ", BuildConfig.DEBUG)
        ShPref.init(this, ShPref.APPLY)
        startKoin(applicationContext, listOf(appModule), logger = KoinLogs())

    }
}