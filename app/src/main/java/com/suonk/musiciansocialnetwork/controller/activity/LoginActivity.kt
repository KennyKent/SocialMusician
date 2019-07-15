package com.suonk.musiciansocialnetwork.controller.activity

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle

import com.google.android.material.bottomnavigation.BottomNavigationView

import androidx.appcompat.app.AppCompatActivity

import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.suonk.musiciansocialnetwork.R

//import com.google.firebase.
//import com.google.firebase.database.FirebaseDatabase;

class LoginActivity : AppCompatActivity() {

    //region ========================================== Var or Val ==========================================

    private var bottomNavigationView: BottomNavigationView? = null

    private var login_activity_Email: EditText? = null
    private var login_activity_Password: EditText? = null
    private var login_activity_SignInButton: MaterialButton? = null

    private var mAuth: FirebaseAuth? = null

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT

        //region ========================================== FindViewById ===========================================

        bottomNavigationView = findViewById(R.id.navigation)

        login_activity_SignInButton = findViewById(R.id.login_activity_sign_in_button)
        login_activity_Email = findViewById(R.id.login_activity_email)
        login_activity_Password = findViewById(R.id.login_activity_password)

        //endregion

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()

        //region ========================================= Listener =========================================

        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.login_frame -> {
                }
                R.id.sign_up_frame -> {
                    startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.sign_up_socials_frame -> {
                    startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

        bottomNavigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        login_activity_SignInButton!!.setOnClickListener {
            signInExistingUsers(login_activity_Email!!.toString(), login_activity_Email!!.toString())
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
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

    private fun signUpNewUsers() {
        mAuth!!.createUserWithEmailAndPassword(login_activity_Email!!.toString(), login_activity_Email!!.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = mAuth!!.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this@LoginActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
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


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this@LoginActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                    // ...
                }
    }

    companion object {
        private val TAG = "MainActivity"
    }

    //endregion
}
