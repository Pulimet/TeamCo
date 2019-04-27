@file:Suppress("ConstantConditionIf")

package net.alexandroid.teamco.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import net.alexandroid.teamco.SHOW_FRAGMENTS_LOGS
import net.alexandroid.utils.mylog.MyLog

/**
 * Created on 1/7/2019 by Alexey Korolev.
 */
abstract class BaseFragment : Fragment() {
    private val job = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + job)
    val bgScope = CoroutineScope(Dispatchers.IO + job)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(SHOW_FRAGMENTS_LOGS) MyLog.i(object {}.javaClass.enclosingMethod?.name + ": " + javaClass.simpleName)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO Maybe a bug of navigation component cause double fragment creation
        if(SHOW_FRAGMENTS_LOGS) MyLog.i(object {}.javaClass.enclosingMethod?.name + ": " + javaClass.simpleName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job.cancel()
        if(SHOW_FRAGMENTS_LOGS) MyLog.i(object {}.javaClass.enclosingMethod?.name + ": " + javaClass.simpleName)
    }

    override fun onDetach() {
        super.onDetach()
        if(SHOW_FRAGMENTS_LOGS) MyLog.i(object {}.javaClass.enclosingMethod?.name + ": " + javaClass.simpleName)
    }
}