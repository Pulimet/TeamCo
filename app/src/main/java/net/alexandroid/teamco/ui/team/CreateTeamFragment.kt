package net.alexandroid.teamco.ui.team


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_create_team.*
import net.alexandroid.teamco.R

class CreateTeamFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnOpenTeamInfo2.setOnClickListener { v ->
            v.findNavController()
                    .navigate(R.id.action_createTeamFragment_to_teamInfoFragment)
        }
    }
}
