package com.cursokotlin.tony

import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment.getExternalStorageDirectory
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Consultas si el usuario ya concedió el permiso
            try{
                //crear carpeta en la raíz del almacenamiento interno
                //crear carpeta en la raíz del almacenamiento interno
                val carpeta = File(getExternalStorageDirectory().toString() + "/miAppFelipe")

                //comprobar si la carpeta no existe, entonces crearla

                //comprobar si la carpeta no existe, entonces crearla
                if (!carpeta.exists()) {
                    //carpeta.mkdir() creará la carpeta en la ruta indicada al inicializar el objeto File

                    Toast.makeText(
                        applicationContext,
                        "Carpeta creada : " + carpeta.absolutePath,
                        Toast.LENGTH_SHORT
                    ).show()

                    if (carpeta.mkdir()) {
                        Toast.makeText(
                            applicationContext,
                            "Carpeta creada : " + carpeta.absolutePath,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    //se ha creado la carpeta;
                } else {
                    //la carpeta ya existe
                    Toast.makeText(
                        applicationContext,
                        "Carpeta existente : " + carpeta.absolutePath,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                Log.d("carpeta creada", carpeta.absolutePath)
            }catch (ex: Exception){
                Log.i("TAG","-------------------------- " + ex.message )
            }

    }
}