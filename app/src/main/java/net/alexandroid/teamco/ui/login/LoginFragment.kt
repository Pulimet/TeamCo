package net.alexandroid.teamco.ui.login


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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


class LoginFragment : BaseFragment() {

    private val REQUEST_CODE_SIGN_IN = 0

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

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
            // TODO Notify ViewModel -> user is logged in.
            // TODO Remove the code below when previous TODO are done
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
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

        signInButton.setSize(SignInButton.SIZE_WIDE)
        signInButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN)
        }

        // TODO Remove button for tests below
        btnOpenHome.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
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
            // Signed in successfully, show authenticated UI.
            firebaseAuthWithGoogle(account!!)
        } catch (e: ApiException) {
            // TODO
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            MyLog.e("signInResult:failed code=" + e.statusCode)
        }

    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        // TODO Show progress dialog
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                MyLog.d("firebaseAuth signInWithCredential:success, display name" + user?.displayName)
                // TODO Notify ViewModel -> user is logged in.
                // TODO Remove the code below when previous TODO are done
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            } else {
                //TODO
                MyLog.w("firebaseAuth signInWithCredential:failure: " + task.exception)
            }
            // TODO Hide progress dialog
        }
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

