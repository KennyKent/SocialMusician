package com.suonk.socialmusician.controller.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.view.MenuItem
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.textfield.TextInputLayout
import com.suonk.socialmusician.R
import com.suonk.socialmusician.controller.CircularImageView
import com.suonk.socialmusician.model.MusicStyle
import com.suonk.socialmusician.model.User
import java.io.ByteArrayOutputStream
import java.util.*

class EditMyProfileActivity : AppCompatActivity() {

    //region ========================================== Functions ===========================================

    private var edit_my_profile_DescriptionLimitChar: TextView? = null
    private var length: Int = 0

    private var descriptionLimitChar = 500

    private var edit_my_profile_ProfileImage: CircularImageView? = null
    private var edit_my_profile_Username: TextInputLayout? = null
    private var edit_my_profile_UserAge: Spinner? = null
    private var edit_my_profile_UserLocalisation: TextInputLayout? = null
    private var edit_my_profile_UserJob: TextInputLayout? = null
    private var edit_my_profile_UserDescription: TextInputLayout? = null
    private var edit_my_profile_UserFormation: TextInputLayout? = null

    var imageUri: Uri? = null
    private val IMAGE_CAPTURE_CODE = 1001

    //private var REQUEST_CAMERA: Int? = 1
    private var SELECT_FILE: Int? = 0


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)

        //region ========================================= Toolbar ==========================================

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar!!
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setHomeAsUpIndicator(R.drawable.ic_left_arrow)
        actionbar.title = "Edit my profile"

        //endregion

        //region ====================================== FindViewById ========================================

        //endregion

        if (edit_my_profile_UserDescription!!.editText!!.text.toString() == "") {
            edit_my_profile_DescriptionLimitChar!!.text = "" + descriptionLimitChar
        } else {
            descriptionLimitChar += (length - edit_my_profile_UserDescription!!.editText!!.text.toString().length)
            edit_my_profile_DescriptionLimitChar!!.text = "" + descriptionLimitChar
        }

        //region ================================== Listener =============================================


        edit_my_profile_ProfileImage!!.setOnClickListener {
            selectImage()
        }

        edit_my_profile_UserDescription!!.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                length = edit_my_profile_UserDescription!!.editText!!.text.toString().length
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if (edit_my_profile_UserDescription!!.editText!!.text.toString().length > length) {
                    descriptionLimitChar -= (edit_my_profile_UserDescription!!.editText!!.text.toString().length - length)
                } else {
                    descriptionLimitChar += (length - edit_my_profile_UserDescription!!.editText!!.text.toString().length)
                }
                edit_my_profile_DescriptionLimitChar!!.text = "" + descriptionLimitChar
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        //endregion
    }

    private fun selectImage() {

        val items = arrayOf<CharSequence>("Camera", "Gallery", "Cancel")
        //            ActionBar.DisplayOptions[]

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Image")
        builder.setItems(items) { dialog, i ->
            if (items[i] == "Camera") {
                openCamera()

            } else if (items[i] == "Gallery") {

                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE!!)

            } else if (items[i] == "Cancel") {
                dialog.dismiss()
            }
        }
        builder.show()
    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    private fun getRealPathFromUri(context: Context, contentUri: Uri): String {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(column_index)
        } finally {
            if (cursor != null) {
                cursor.close()
            }
        }
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val imageBytes = baos.toByteArray()

        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private fun base64ToBitmap(base64: String): Bitmap {
        val imageBytes = Base64.decode(base64, 0)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    //endregion
}
