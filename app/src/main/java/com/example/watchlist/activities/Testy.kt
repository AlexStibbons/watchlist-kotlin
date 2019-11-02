package com.example.watchlist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.watchlist.R
import com.example.watchlist.utils.EXTRA_USER_ID

class Testy : AppCompatActivity() {

    private var id: Int = -3
    lateinit var textId: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testy)

        textId = findViewById(R.id.idViewTest)

        var i = intent
        id = i.getIntExtra(EXTRA_USER_ID, -1)
        textId.setText("User Id is $id")

    }
}
