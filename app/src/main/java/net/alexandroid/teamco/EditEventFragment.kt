package net.alexandroid.teamco


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_edit_event.*

class EditEventFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnOpenTeamList.setOnClickListener { v ->
            v.findNavController()
                    .navigate(R.id.action_editEventFragment_to_teamListFragment)
        }
        btnOpenUserList.setOnClickListener { v ->
            v.findNavController()
                    .navigate(R.id.action_editEventFragment_to_userListFragment)
        }
    }
}
