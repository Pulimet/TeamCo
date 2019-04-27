package net.alexandroid.teamco.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_home.*
import net.alexandroid.teamco.R
import net.alexandroid.teamco.ui.base.BaseFragment

class HomeFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnOpenCreateEvent.setOnClickListener { v ->
            v.findNavController()
                    .navigate(R.id.action_homeFragment_to_createEventFragment)
        }
        btnOpenEditEvent.setOnClickListener { v ->
            v.findNavController()
                    .navigate(R.id.action_homeFragment_to_editEventFragment)
        }
        btnOpenEvent.setOnClickListener { v ->
            v.findNavController()
                    .navigate(R.id.action_homeFragment_to_eventFragment)
        }
    }
}
