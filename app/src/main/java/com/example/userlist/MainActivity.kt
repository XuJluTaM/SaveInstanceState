package com.example.userlist

import User
import UserViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var adapter: ArrayAdapter<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameEditText: EditText = findViewById(R.id.name_edit_text)
        val ageEditText: EditText = findViewById(R.id.age_edit_text)
        val saveButton: Button = findViewById(R.id.save_button)
        val listView: ListView = findViewById(R.id.user_list_view)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        userViewModel.userList.observe(this, Observer { users ->
            adapter.clear()
            adapter.addAll(users)
            adapter.notifyDataSetChanged()
        })

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString().toIntOrNull()
            if (name.isNotEmpty() && age != null) {
                val user = User(name, age)
                userViewModel.addUser(user)
                nameEditText.text.clear()
                ageEditText.text.clear()
            }
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val user = adapter.getItem(position)
            user?.let {
                userViewModel.removeUser(it)
            }
        }
    }
}
