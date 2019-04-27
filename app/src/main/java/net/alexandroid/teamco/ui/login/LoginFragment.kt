package net.alexandroid.teamco.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.fragment_login.*
import net.alexandroid.teamco.R
import net.alexandroid.teamco.ui.base.BaseFragment
import net.alexandroid.utils.mylog.MyLog
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    @Suppress("PrivatePropertyName")
    private val REQUEST_CODE_SIGN_IN = 0

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        activity?.let { mGoogleSignInClient = GoogleSignIn.getClient(it, gso) }
    }

    override fun onStart() {
        super.onStart()
        auth.currentUser?.apply {
            MyLog.d("FirebaseAuth currentUser: $displayName")
            loginViewModel.onUserLoggedIn()
        }

        // For tests
        val account = GoogleSignIn.getLastSignedInAccount(activity)
        account?.apply {
            MyLog.d("GoogleSignIn currentUser: $displayName")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.getProgressBar().observe(this, Observer {
           progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
        loginViewModel.getNavigate().observe(this, Observer {navigateToHomeFragment()})

        setGoogleSignInButton()
    }

    private fun setGoogleSignInButton() {
        signInButton.setSize(SignInButton.SIZE_WIDE)
        signInButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            MyLog.d("Signed in, display name: " + account?.displayName)
            fireBaseAuthWithGoogle(account!!)
        } catch (e: ApiException) {
            // TODO
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            MyLog.e("signInResult:failed code=" + e.statusCode)
        }

    }

    private fun fireBaseAuthWithGoogle(acct: GoogleSignInAccount) {
        loginViewModel.onStartFireBaseLogin()

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                MyLog.d("firebaseAuth signInWithCredential:success, display name" + user?.displayName)
                loginViewModel.onUserLoggedIn()
            } else {
                //TODO
                MyLog.w("firebaseAuth signInWithCredential:failure: " + task.exception)
            }
            loginViewModel.onFinishedFireBaseLogin()
        }
    }

    private fun navigateToHomeFragment() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        mGoogleSignInClient.signOut().addOnCompleteListener {
            MyLog.d("GoogleSignIn signed out")
        }
    }

    private fun revokeAccess() {
        mGoogleSignInClient.revokeAccess()
        /*.addOnCompleteListener(this, OnCompleteListener<Void> {
            // ...
        })*/
    }

}

