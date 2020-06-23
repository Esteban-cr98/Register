package com.example.register

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var fecha: String
    private var cal= Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateSetListener = object:DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format= "MM/dd/yyyy"
                val simpleDateFormat = SimpleDateFormat(format, Locale.US)
                fecha = simpleDateFormat.format(cal.time).toString()

                TV_fecha_nacimiento.text= fecha
            }
        }

        IB_calendar.setOnClickListener{
            DatePickerDialog(this ,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        bt_accion.setOnClickListener{
            val nombre = ET_name.text.toString()
            val email = ET_email.text.toString()
            val tel = ET_telefono.text.toString()
            val pass= ET_contrasena.text.toString()
            val confpass = ET_confircontrasena.text.toString()
            val genero = if(RB_fem.isChecked) "Masculino" else "Femenino"
            var hob = ""
            val lugar=SP_city.selectedItem.toString()

            if(CB_caminar.isChecked) hob = "$hob Caminar"
            if(CB_cine.isChecked) hob = "$hob Cine"
            if(CB_series.isChecked) hob = "$hob Series"
            if(CB_video.isChecked) hob= " $hob Video Juegos"


            if(nombre.isEmpty() || email.isEmpty() || tel.isEmpty() || pass.isEmpty() ||
                confpass.isEmpty() || hob.isEmpty()){

                TV_resultado.text="Complete todos los campos."

            }else {
                if(pass==confpass){
                    TV_resultado.text="Nombre: $nombre \nCorreo: $email \nTeléfono: $tel \n" +
                            "Género: $genero \nHobbies:$hob \n" +
                            "Fecha de nacimiento: $fecha \nLugar de nacimiento: $lugar"

                } else{
                    TV_resultado.text="Las contraseñas no coinciden."
                }
            }


        }
    }
}