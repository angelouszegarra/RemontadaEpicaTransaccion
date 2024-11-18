package com.example.remontadaepicatransaccion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializamos FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Configuramos el botón para enviar el código SMS
        val btnSendCode = findViewById<Button>(R.id.btnSendCode)
        btnSendCode.setOnClickListener {
            val phoneNumber = findViewById<EditText>(R.id.etPhoneNumber).text.toString().trim()

            // Validar que el número no esté vacío y tenga un formato adecuado
            if (phoneNumber.isNotEmpty() && phoneNumber.length >= 10) {
                sendVerificationCode(phoneNumber)
            } else {
                // Mostrar un mensaje de error si el número está vacío o es inválido
                Toast.makeText(this, "Por favor ingrese un número de teléfono válido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Método para enviar el código SMS
    private fun sendVerificationCode(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Número de teléfono a verificar
            .setTimeout(60L, TimeUnit.SECONDS) // Tiempo de espera para la verificación
            .setActivity(this)                 // Contexto de la actividad
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // Verificación completada automáticamente
                    signInWithPhoneAuthCredential(credential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    // Error al verificar el número de teléfono
                    Toast.makeText(this@RegisterActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }

                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(verificationId, token)
                    // Aquí puedes guardar el verificationId para usarlo más tarde en la verificación
                    // En este caso, pasamos el ID a la siguiente actividad para que el usuario ingrese el código
                    val intent = Intent(this@RegisterActivity, VerifyCodeActivity::class.java)
                    intent.putExtra("verificationId", verificationId) // Guardar verificationId
                    startActivity(intent)
                }
            })
            .build()

        // Enviamos la verificación al número de teléfono
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    // Método para iniciar sesión con el código verificado
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Usuario autenticado correctamente
                    val user = task.result?.user
                    // Redirigir a la siguiente pantalla (HomeActivity)
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish() // Finaliza esta actividad
                } else {
                    // Error de autenticación
                    Toast.makeText(this, "Error de autenticación: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
