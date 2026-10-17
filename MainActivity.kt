package com.axom.nexaproxy

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private lateinit var status: TextView
    private lateinit var keyInput: EditText
    private var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.rgb(12, 15, 22)
        window.navigationBarColor = Color.rgb(12, 15, 22)
        window.decorView.setBackgroundColor(Color.rgb(12, 15, 22))

        val scroll = ScrollView(this)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 32, 24, 28)
        }
        scroll.addView(root)
        setContentView(scroll)

        fun text(value: String, size: Float, color: Int = Color.WHITE, bold: Boolean = false): TextView {
            return TextView(this).apply {
                text = value
                textSize = size
                setTextColor(color)
                if (bold) setTypeface(null, android.graphics.Typeface.BOLD)
                setPadding(0, 8, 0, 8)
            }
        }
        fun button(value: String, action: () -> Unit): Button {
            return Button(this).apply {
                text = value
                isAllCaps = false
                setOnClickListener { action() }
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply { topMargin = 8; bottomMargin = 8 }
            }
        }

        root.addView(text("NEXA PROXY", 28f, Color.rgb(167, 139, 250), true))
        root.addView(text("Starter • ADB Wi-Fi • Keys", 14f, Color.LTGRAY))
        root.addView(text("Chave de acesso", 16f, Color.WHITE, true))
        keyInput = EditText(this).apply {
            hint = "Digite sua key"
            setSingleLine(true)
            setTextColor(Color.WHITE)
            setHintTextColor(Color.GRAY)
            setPadding(16, 12, 16, 12)
            setBackgroundColor(Color.rgb(31, 36, 48))
        }
        root.addView(keyInput, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ))
        root.addView(button("Validar key (modo demonstração)") {
            val key = keyInput.text.toString().trim()
            if (key.isBlank()) {
                Toast.makeText(this, "Digite uma key primeiro.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Validação real não configurada. Conecte seu backend HTTPS.", Toast.LENGTH_LONG).show()
                status.text = "Key digitada; nenhuma validação foi feita. Configure um backend HTTPS para validar com segurança."
            }
        })

        root.addView(text("ADB Wi-Fi", 20f, Color.WHITE, true))
        root.addView(text(
            "1. Ative Opções do desenvolvedor > Depuração sem fio.\n" +
            "2. Faça o pareamento pelo fluxo oficial do Android/ADB.\n" +
            "3. Este app não inicia nem controla o ADB automaticamente; Android não permite isso a um app comum sem uma implementação autorizada específica.",
            14f, Color.LTGRAY
        ))
        root.addView(button("Abrir opções do desenvolvedor") {
            try {
                startActivity(android.content.Intent(android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS))
            } catch (_: Exception) {
                Toast.makeText(this, "Abra Configurações > Sistema > Opções do desenvolvedor.", Toast.LENGTH_LONG).show()
            }
        })

        root.addView(text("Proxy", 20f, Color.WHITE, true))
        status = text("Status: parado. Nenhuma conexão de proxy foi iniciada.", 14f, Color.rgb(253, 186, 116))
        root.addView(status)
        root.addView(button("Iniciar proxy (placeholder)") {
            running = true
            status.text = "Status: demonstração ligada. O motor de proxy ainda não está implementado."
            status.setTextColor(Color.rgb(134, 239, 172))
        })
        root.addView(button("Parar proxy") {
            running = false
            status.text = "Status: parado. O motor de proxy ainda não está implementado."
            status.setTextColor(Color.rgb(253, 186, 116))
        })

        root.addView(text("Importante", 18f, Color.WHITE, true))
        root.addView(text(
            "Este projeto é uma base de interface, não uma proxy funcional para Free Fire. " +
            "Para tornar a proxy real, defina o tipo de tráfego, o servidor autorizado e o método de integração. " +
            "Não coloque segredos ou chaves privadas dentro do APK.",
            13f, Color.LTGRAY
        ))
    }
}
