package net.alexandroid.teamco

import android.app.Application
import net.alexandroid.shpref.ShPref
import net.alexandroid.utils.mylog.MyLog

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        MyLog.init(this, "QAZ", BuildConfig.DEBUG)
        ShPref.init(this, ShPref.APPLY)

    }
}