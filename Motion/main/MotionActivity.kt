package com.example.materialtest

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

// Test Forward and backward
class MotionActivity : AppCompatActivity()  {
    private lateinit var topAppBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        topAppBar = findViewById(R.id.topAppBar)
        setSupportActionBar(topAppBar)
    }

    @SuppressLint("Recycle")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)

        val menuItem = menu?.findItem(R.id.custom_bar)

        val actionView = menuItem?.actionView

        val button1 = actionView?.findViewById<ImageButton>(R.id.button1)
        val button2 = actionView?.findViewById<ImageButton>(R.id.button2)
        val button3 = actionView?.findViewById<ImageButton>(R.id.button3)

        // 淡入淡出测试
        val fadeOut = ObjectAnimator.ofFloat(button1, "alpha", 1f, 0f)
        fadeOut.duration = 150

        val fadeIn = ObjectAnimator.ofFloat(button1, "alpha", 0f, 1f)
        fadeIn.duration = 150

        fadeOut.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // 在淡出动画结束后，切换样式
                button1?.backgroundTintList = ColorStateList.valueOf(Color.RED)// 更换为新样式
                fadeIn.start()
            }
        })

        button1?.setOnClickListener {
            fadeOut.start()
            Toast.makeText(this, "Button 1 clicked", Toast.LENGTH_SHORT).show()
        }

        button2?.setOnClickListener {
            Toast.makeText(this, "Button 2 clicked", Toast.LENGTH_SHORT).show()
        }

        button3?.setOnClickListener {
            Toast.makeText(this, "Button 3 clicked", Toast.LENGTH_SHORT).show()
        }

        return true
    }
}