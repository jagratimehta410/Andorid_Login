package com.example.mynewactivity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.InternalHelpers
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    lateinit var username: EditText;
    lateinit var pass: EditText;
    lateinit var button: Button;

//    lateinit var firebaseDatabase: FirebaseDatabase
    //from this acces all class and methods of firebaseauth

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var firebaseDatabseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.username)
        pass = findViewById(R.id.pass)
        button = findViewById(R.id.button)

        firebaseDatabase =FirebaseDatabase.getInstance() //method which return object // refrence variable
        firebaseDatabseRef =firebaseDatabase.getReference().child("Users") // url tak pocha deti haii - firebase
        // child node create krta haiii // child is used to create node


        //hashmap in java key value pair
        val data = HashMap<String, String>()

        button.setOnClickListener {
            firebaseAuth = FirebaseAuth.getInstance()

            val email: String = username.text.toString().trim()
            val pass: String = pass.text.toString().trim()
            data.put("Email", email)
            data.put("password", pass)

            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener {
                Toast.makeText(this@MainActivity, "user register", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity, " error ", Toast.LENGTH_LONG).show()
            }

            firebaseDatabseRef.push().setValue(data).addOnSuccessListener {
                Toast.makeText(this@MainActivity, "data entered", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity, "Error Occured", Toast.LENGTH_LONG).show()
            }



        }


    }
}

