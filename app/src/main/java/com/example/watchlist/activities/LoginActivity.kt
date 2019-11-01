package com.example.watchlist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.watchlist.R
import com.example.watchlist.database.LocalDb
import com.example.watchlist.utils.EXTRA_USER_ID
import com.example.watchlist.utils.hasLoginData

class LoginActivity : AppCompatActivity() {

    private val TAG = LoginActivity::class.java.simpleName

    // declare everything that's needed (for a lack of a butter knife)
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var loginButton: Button
    private lateinit var localDb: LocalDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.login_email)
        passwordEditText = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.btn_enter)

        loginButton.setOnClickListener{
            var email: String? = emailEditText.text.toString()
            var password: String? = passwordEditText.text.toString()

            if (hasLoginData(email, password)) {
                localDb = LocalDb.getInstance(this)
                // go to DB; find or create user; pass id to new activity
                Toast.makeText(this, "$email and $password", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Input email and pass", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun changeActivity(id: Int) {
        val intent = Intent(this, ListsActivity::class.java).apply {
            putExtra(EXTRA_USER_ID, id)
        }
        startActivity(intent)
    }
}
