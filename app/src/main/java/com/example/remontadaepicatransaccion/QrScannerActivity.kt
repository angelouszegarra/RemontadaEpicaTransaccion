package com.example.remontadaepicatransaccion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class QrScannerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scanner)
        // Implementación del escáner QR usando ML Kit
    }
}
