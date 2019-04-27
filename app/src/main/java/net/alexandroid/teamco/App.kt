package net.alexandroid.teamco

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.alexandroid.shpref.ShPref
import net.alexandroid.teamco.di.KoinLogs
import net.alexandroid.teamco.di.appModule
import net.alexandroid.utils.mylog.MyLog
import org.koin.android.ext.android.startKoin
import kotlin.coroutines.CoroutineContext

class App : Application(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun onCreate() {
        super.onCreate()
        setLogger()
        launch {
            ShPref.init(applicationContext, ShPref.APPLY)
            startKoin(applicationContext, listOf(appModule), logger = KoinLogs())
        }
    }

    private fun setLogger() {
        MyLog.init(applicationContext, "QAZ", BuildConfig.DEBUG)
        MyLog.setThreadIdVisibility(true)
        MyLog.e("============ Application Created ==============")
    }

}