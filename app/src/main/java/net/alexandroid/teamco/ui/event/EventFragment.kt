package net.alexandroid.teamco.ui.event


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_event.*
import net.alexandroid.teamco.R

class EventFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnOpenTeamList2.setOnClickListener { v ->
            v.findNavController()
                    .navigate(R.id.action_eventFragment_to_teamListFragment)
        }
        btnOpenCreateTeam.setOnClickListener { v ->
            v.findNavController()
                    .navigate(R.id.action_eventFragment_to_createTeamFragment)
        }
    }
}
