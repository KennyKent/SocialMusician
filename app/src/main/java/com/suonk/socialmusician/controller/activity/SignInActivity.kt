package com.suonk.socialmusician.controller.activity

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.suonk.socialmusician.R
import java.util.*


//import com.google.firebase.
//import com.google.firebase.database.FirebaseDatabase;

class SignInActivity : AppCompatActivity() {

    //region ========================================== Var or Val ==========================================

    private var sign_in_BottomNavigationView: BottomNavigationView? = null

    private var sign_in_activity_Email: AppCompatEditText? = null
    private var sign_in_activity_Password: AppCompatEditText? = null
    private var sign_in_activity_SignInButton: MaterialButton? = null

    private final var RC_SIGN_IN = 123

    private var mAuth: FirebaseAuth? = null

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val height = size.y
        // Nous permet d'avoir un écran adapté a différente taille d'écran
        when {
            height > 2500 -> setContentView(R.layout.activity_sign_in_bigger)
            height in 2000..2499 -> setContentView(R.layout.activity_sign_in_normal)
            height in 1200..2101 -> setContentView(R.layout.activity_sign_in_normal)
            height < 1200 -> setContentView(R.layout.activity_sign_in_normal)
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT

        //region ========================================== FindViewById ===========================================

        sign_in_BottomNavigationView = findViewById(R.id.sign_in_bottom_navigation_view)

        sign_in_activity_SignInButton = findViewById(R.id.sign_in_activity_sign_in_button)
        sign_in_activity_Email = findViewById(R.id.sign_in_activity_email_edit_text)
        sign_in_activity_Password = findViewById(R.id.sign_in_activity_password_edit_text)

        //endregion

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()

        //region ========================================= Listener =========================================

        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.sign_in_frame -> {
                }
                R.id.sign_up_frame -> {
                    startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.sign_up_socials_frame -> {
                    startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

        sign_in_BottomNavigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        sign_in_activity_SignInButton!!.setOnClickListener {
            if (sign_in_activity_Email!!.text.toString().isEmpty() || sign_in_activity_Password!!.text.toString().isEmpty()) {
                Toast.makeText(this@SignInActivity, "Field should not be empty",
                        Toast.LENGTH_SHORT).show()
            } else {
                signInExistingUsers(sign_in_activity_Email!!.text.toString(), sign_in_activity_Password!!.text.toString())

                MaterialAlertDialogBuilder(this)
                        .setTitle(sign_in_activity_Email!!.text.toString())
                        .setMessage(sign_in_activity_Password!!.text.toString())
                        .show()
            }
        }

        //endregion
    }

    //region ========================================== Functions ===========================================

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        var user = user
        user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val name = user.displayName
            val email = user.email
            val uid = user.uid

            val emailVerified = user.isEmailVerified

        }
    }

    private fun hideKeyboard() {
        // Check if no view has focus:
        val view = this.currentFocus

        view?.let { v ->
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    private fun signInExistingUsers(email: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = mAuth!!.currentUser
                        updateUI(user)
                        val sharedDisconnected = getSharedPreferences("isNotConnected", Context.MODE_PRIVATE)
                        val edit = sharedDisconnected.edit()
                        edit.putBoolean("isNotConnected", false)
                        edit.apply()
                        startActivity(Intent(this@SignInActivity, MainActivity::class.java).putExtra("user_id", user!!.uid))
                        finish()

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this@SignInActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                    // ...
                }
    }

//    private fun startSignInActivity() {
//        startActivityForResult(
//                AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setTheme(com.suonk.socialmusician.R.style.LoginTheme)
//                        .setAvailableProviders(
//                                Arrays.asList(AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
//                        .setIsSmartLockEnabled(false, true)
//                        .setLogo(R.drawable.ic_sign_in)
//                        .build(),
//                RC_SIGN_IN)
//    }

    companion object {
        private val TAG = "MainActivity"
    }

    //endregion
}
