package net.alexandroid.teamco.ui.users


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.alexandroid.teamco.R
import net.alexandroid.teamco.ui.base.BaseFragment

class UserListFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*        btn.setOnClickListener { v ->
            v.findNavController()
                    .navigate(R.id.xxx)
        }*/
    }
}
