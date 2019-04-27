package net.alexandroid.teamco.ui.event


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_create_event.*
import net.alexandroid.teamco.R
import net.alexandroid.teamco.ui.base.BaseFragment

class CreateEventFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnOpenEventCreated.setOnClickListener { v ->
            v.findNavController()
                    .navigate(R.id.action_createEventFragment_to_eventCreatedFragment)
        }
    }
}
