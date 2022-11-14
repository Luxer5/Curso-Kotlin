package com.cursokotlin.userssp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.userssp.databinding.ActivityMainBinding
import java.text.FieldPosition

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var  linearLayoutManager: RecyclerView.LayoutManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply{
            setHasFixedSize(true)
            layoutManager=linearLayoutManager
            adapter=userAdapter
        }
    }



    private fun getUsers() : MutableList<User>{
        val users= mutableListOf<User>()
        val alain=User(1, "Alain", "Nicolas", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Rojos.png/250px-Rojos.png")
        val samantha = User(2, "Samantha", "Mesa", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/db/Tipos_de_verde.png/250px-Tipos_de_verde.png")
        val javier = User(3,"Javier", "Gomez", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Tipos_de_azules.png/250px-Tipos_de_azules.png")
        val emma = User(4, "Emma", "Cruz", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5b/Morado.png/250px-Morado.png")

        users.add(alain)
        users.add(samantha)
        users.add(javier)
        users.add(emma)
        users.add(alain)
        users.add(samantha)
        users.add(javier)
        users.add(emma)
        users.add(alain)
        users.add(samantha)
        users.add(javier)
        users.add(emma)

        return users
    }

    override fun onClick(user: User, position: Int) {
        Toast.makeText(this, "$position: ${user.getfullName()}" , Toast.LENGTH_SHORT).show()
    }
}