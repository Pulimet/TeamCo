@file:Suppress("ConstantConditionIf")

package net.alexandroid.teamco.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import net.alexandroid.teamco.SHOW_VIEW_MODEL_LOGS
import net.alexandroid.utils.mylog.MyLog
import org.koin.standalone.KoinComponent
import kotlin.coroutines.CoroutineContext
/**
 * Created on 4/27/2019 by Alexey Korolev.
 */
abstract class BaseViewModel : ViewModel(), CoroutineScope, KoinComponent {

    init {
        if (SHOW_VIEW_MODEL_LOGS) MyLog.w("${javaClass.simpleName} - init")
    }
    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun onCleared() {
        if (SHOW_VIEW_MODEL_LOGS)  MyLog.w("${javaClass.simpleName} - onCleared")
        coroutineContext.cancel()
    }
}

