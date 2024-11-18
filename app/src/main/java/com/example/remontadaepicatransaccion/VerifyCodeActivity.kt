package com.example.remontadaepicatransaccion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class VerifyCodeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_code)

        auth = FirebaseAuth.getInstance()

        // Obtener el verificationId pasado desde RegisterActivity
        val verificationId = intent.getStringExtra("verificationId") ?: ""

        val etCode = findViewById<EditText>(R.id.etCode)
        val btnVerify = findViewById<Button>(R.id.btnVerify)

        btnVerify.setOnClickListener {
            val code = etCode.text.toString().trim()

            if (code.isNotEmpty()) {
                // Crear el credential con el código y el verificationId
                val credential = PhoneAuthProvider.getCredential(verificationId, code)
                signInWithPhoneAuthCredential(credential)
            } else {
                Toast.makeText(this, "Por favor ingrese el código", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Usuario autenticado correctamente
                    val user = task.result?.user
                    // Redirigir a la pantalla principal (HomeActivity)
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish() // Finaliza esta actividad
                } else {
                    Toast.makeText(this, "Error de autenticación: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
