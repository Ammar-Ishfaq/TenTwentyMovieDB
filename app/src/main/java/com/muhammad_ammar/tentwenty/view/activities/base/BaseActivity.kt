package com.muhammad_ammar.tentwenty.view.activities.base

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.muhammad_ammar.tentwenty.util.AlertMessageDialog

open class BaseActivity : AppCompatActivity() {

    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun showAlertDialog(
        msg: String,
        positiveButtonCallback: AlertMessageDialog.PositiveButtonCallback? = null
    ) {
        AlertMessageDialog.newInstance(msg, positiveButtonCallback)
            .show(this.supportFragmentManager, AlertMessageDialog.TAG)
    }

}