package com.example.alertdialogapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var userListView: ListView    //
    lateinit var addButton: FloatingActionButton //floting button action
    var userArrayList = arrayListOf("Kundan", "Sunni", "Nikhil")  //data store in array

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userListView = findViewById(R.id.userListView)
        addButton = findViewById(R.id.addButton)
        userListView.adapter = ArrayAdapter(this, R.layout.item_list, userArrayList)
        userListView.setOnItemClickListener { adapterView, view, i, l ->
            showEditAlert(i)
        }

        userListView.setOnItemLongClickListener { adapterView, view, i, l ->
           showDeleteAlert(i)
            true
        }
        addButton.setOnClickListener {
            showAddAlert()
        }

    }
    fun showEditAlert(i: Int) {
        val alert=AlertDialog.Builder(this).create()
        alert.window?.setBackgroundDrawable(getDrawable(R.drawable.alert_shape))
        alert.window?.setGravity(Gravity.TOP)
        val layout=LayoutInflater.from(this).inflate(R.layout.custom_layoutdialog,null,false)
        val saveBtn=layout.findViewById<Button>(R.id.saveBtn)
        val nameEditText=layout.findViewById<EditText>(R.id.nameEditText)

        saveBtn.setOnClickListener {

            userArrayList[i] = "${nameEditText.text}"
            userListView.adapter = ArrayAdapter(this, R.layout.item_list, userArrayList)

            alert.dismiss()
            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show()
        }
        alert.setView(layout)
        alert.show()
        alert.setCancelable(false)
    }

    fun showAddAlert() {
        val alert=AlertDialog.Builder(this).create()//creat
        alert.window?.setBackgroundDrawable(getDrawable(R.drawable.alert_shape))
        alert.window?.setGravity(Gravity.TOP)
        val layout=LayoutInflater.from(this).inflate(R.layout.custom_layoutdialog,null,false)
        val saveBtn=layout.findViewById<Button>(R.id.saveBtn)
        val nameEditText=layout.findViewById<EditText>(R.id.nameEditText)
        saveBtn.setOnClickListener {
//            userArrayList[i] =  "${nameEditText.text}"
            userArrayList.add("${nameEditText.text}") // adding value in array
            userListView.adapter = ArrayAdapter(this, R.layout.item_list, userArrayList)

            alert.dismiss()
            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show()
        }
        alert.setView(layout)
        alert.show()
        alert.setCancelable(false)
    }

    fun showDeleteAlert(i:Int) {
        val alert=AlertDialog.Builder(this)
        alert.setCancelable(false)
        alert.setTitle("Delete Item")
        alert.setMessage("Are you sure you want to delete this user?")
        alert.setPositiveButton("Yes"){a,b->
            userArrayList.removeAt(i)
            userListView.adapter = ArrayAdapter(this, R.layout.item_list, userArrayList)

            Toast.makeText(this, "Delete Item", Toast.LENGTH_SHORT).show()
        }
        alert.setNegativeButton("No"){a,b->

            Toast.makeText(this, "Item not Delete", Toast.LENGTH_SHORT).show()
        }
        alert.show()

    }
}