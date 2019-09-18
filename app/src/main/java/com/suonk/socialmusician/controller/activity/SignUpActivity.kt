package com.suonk.socialmusician.controller.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.suonk.socialmusician.R

class SignUpActivity : AppCompatActivity() {

    //region ========================================== Var or Val ==========================================

    private var sign_up_BottomNavigationView: BottomNavigationView? = null

    private var sign_up_activity_FirstName: AppCompatEditText? = null
    private var sign_up_activity_LastName: AppCompatEditText? = null
    private var sign_up_activity_MailAddress: AppCompatEditText? = null
    private var sign_up_activity_Password: AppCompatEditText? = null
    private var sign_up_activity_PasswordConfirmed: AppCompatEditText? = null

    private var sign_up_activity_SignUpButton: MaterialButton? = null

    private var mAuth: FirebaseAuth? = null

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT

        //region ========================================== FindViewById ===========================================

        sign_up_BottomNavigationView = findViewById(R.id.sign_up_bottom_navigation_view)

        sign_up_activity_FirstName = findViewById(R.id.sign_up_activity_first_name)
        sign_up_activity_LastName = findViewById(R.id.sign_up_activity_last_name)
        sign_up_activity_MailAddress = findViewById(R.id.sign_up_activity_mail_address)
        sign_up_activity_Password = findViewById(R.id.sign_up_activity_password)
        sign_up_activity_PasswordConfirmed = findViewById(R.id.sign_up_activity_password_confirmed)

        sign_up_activity_SignUpButton = findViewById(R.id.sign_in_activity_sign_in_button)

        //endregion

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()

        //region ========================================= Listener =========================================

        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.sign_in_frame -> {
                    startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.sign_up_frame -> {
                }
                R.id.sign_up_socials_frame -> {
                }
            }
            false
        }

        sign_up_BottomNavigationView!!.menu.getItem(1).isChecked = true
        sign_up_BottomNavigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        sign_up_activity_SignUpButton!!.setOnClickListener {
            if (sign_up_activity_FirstName!!.text.toString().isEmpty() || sign_up_activity_LastName!!.text.toString().isEmpty() ||
                    sign_up_activity_MailAddress!!.text.toString().isEmpty() || sign_up_activity_Password!!.text.toString().isEmpty() ||
                    sign_up_activity_PasswordConfirmed!!.text.toString().isEmpty()) {
                Toast.makeText(this@SignUpActivity, "Field should not be empty",
                        Toast.LENGTH_SHORT).show()
            } else {
                if (sign_up_activity_Password!!.text.toString() != sign_up_activity_PasswordConfirmed!!.text.toString()) {
                    Toast.makeText(this@SignUpActivity, "Password were not the same",
                            Toast.LENGTH_SHORT).show()
                } else {
                    signUpNewUsers(sign_up_activity_MailAddress!!.text.toString(), sign_up_activity_Password!!.toString())
                }
            }

        }

        //endregion
    }

    //region ========================================= Functions =========================================

    private fun checkIfEmptyTextField(editText: AppCompatEditText): Boolean {

        if (editText.text.toString() == "") {
            return false
        }

        return true
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

    private fun signUpNewUsers(email: String, password: String) {
        mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = mAuth!!.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                    // ...
                }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser)
    }

    companion object {
        private val TAG = "MainActivity"
    }

    //endregion
}
