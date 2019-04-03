package net.alexandroid.teamco.ui.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import net.alexandroid.teamco.LoginFragmentDirections
import net.alexandroid.teamco.R

class LoginFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnOpenHome.setOnClickListener { v ->
            v.findNavController()
                    //.navigate(R.id.action_loginFragment_to_homeFragment)
                    .navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
          /*          .navigate(R.id.action_loginFragment_to_homeFragment,
                            null,
                            NavOptions.Builder()
                                    .setPopUpTo(R.id.loginFragment, true)
                                    .build())*/
        }
    }
}
