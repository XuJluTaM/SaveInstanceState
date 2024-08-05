package com.example.userlist

import android.app.AlertDialog
import android.content.Context

class MyDialog(private val context: Context, private val onConfirm: () -> Unit) {

    fun show() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Подтверждение удаления")
        builder.setMessage("Вы уверены, что хотите удалить этого пользователя?")

        builder.setPositiveButton("Да") { _, _ ->
            onConfirm()
        }

        builder.setNegativeButton("Нет") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}
