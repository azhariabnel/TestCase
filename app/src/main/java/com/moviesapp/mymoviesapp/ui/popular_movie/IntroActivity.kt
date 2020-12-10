package com.moviesapp.mymoviesapp.ui.popular_movie

import android.R.attr.password
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.moviesapp.mymoviesapp.R
import com.moviesapp.mymoviesapp.utils.KeyboardUtils
import com.moviesapp.mymoviesapp.utils.gone
import com.moviesapp.mymoviesapp.utils.visible
import kotlinx.android.synthetic.main.activity_intro.*


class IntroActivity : AppCompatActivity() {

    private var nickName: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        initListener()
    }

    private fun initListener() {
        etNickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                nickName = s.toString()
                if (nickName != null) {
                    setNickname()
                } else {
                    tvErrorName.text = applicationContext.getString(R.string.tidak_boleh_kosong)
                    tvErrorName.visible()
                    etNickname.requestFocus()
                }
            }

        })
    }

    private fun setNickname() {
        btPick.setOnClickListener {
            nickName = etNickname.text.toString()
            val preferences = getSharedPreferences("Users", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("Nickname", nickName)
            editor.apply()
            KeyboardUtils.hideKeyboard(this)
            if (nickName!!.length < 6) {
                tvErrorName.text = applicationContext.getString(R.string.harus_lebih)
                tvErrorName.visible()
                etNickname.requestFocus()
            } else {
                llNavigation1.gone()
                llNavigation3.gone()
                llNavigation2.visible()
                tvNickIntro.text = "Nice to meet you $nickName"
                btContinue.setOnClickListener {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }
        }
    }
}