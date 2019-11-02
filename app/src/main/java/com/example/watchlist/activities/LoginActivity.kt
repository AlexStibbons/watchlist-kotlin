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
import com.example.watchlist.database.models.User
import com.example.watchlist.utils.EXTRA_USER_ID
import com.example.watchlist.utils.hasLoginData
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {

    private val TAG = LoginActivity::class.java.simpleName

    // declare everything that's needed (for a lack of a butter knife)
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var loginButton: Button
    private lateinit var localDb: LocalDb

    // coroutines
    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)
    // .Main for quick jobs; will communicate with main
    // .IO for network or local db -> on success, stays on background!
    // to return to main, must make new coroutine scope using main!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.login_email)
        passwordEditText = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.btn_enter)

        loginButton.setOnClickListener{
            var email: String? = emailEditText.text.toString()
            var password: String? = passwordEditText.text.toString()

            // when (hasLoginData) seems like overdoing it
            if (hasLoginData(email, password)) {
                localDb = LocalDb.getInstance(this)
                Toast.makeText(this, "$email and $password", Toast.LENGTH_LONG).show()

                // works, but not written in kotlin-ish
                coroutineScope.launch {
                    var userId: Int? = localDb.userDao().getUserIdByEmail(emailEditText.text.toString())

                    if (userId != null) {
                        changeActivity(userId)
                    } else {
                        userId = localDb.userDao().addUser(User(null, emailEditText.text.toString(),passwordEditText.text.toString())).toInt()
                        changeActivity(userId)
                    }
                }
            } else {
                Toast.makeText(this, "Input email and pass", Toast.LENGTH_LONG).show()
            }
        }
    }

    suspend fun changeActivity(id: Int?) {

        val intent = Intent(this, Testy::class.java).apply {
            putExtra(EXTRA_USER_ID, id)
        }
        withContext(Dispatchers.Main) {
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
