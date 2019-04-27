package net.alexandroid.teamco.di

import net.alexandroid.teamco.SHOW_KOIN_LOGS
import net.alexandroid.utils.mylog.MyLog
import org.koin.log.Logger

/**
 * Created on 04/27/2019 by Alexey Korolev.
 */
@Suppress("ConstantConditionIf")
class KoinLogs : Logger {
    override fun err(msg: String) {
        MyLog.e(msg)
    }

    override fun info(msg: String) {
        if (SHOW_KOIN_LOGS) MyLog.i(msg)
    }

    override fun debug(msg: String) {
        if (SHOW_KOIN_LOGS) MyLog.d(msg)
    }
}