package com.muhammad_ammar.tentwenty.extensions

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.muhammad_ammar.tentwenty.R
import com.muhammad_ammar.tentwenty.util.Event
import com.muhammad_ammar.tentwenty.util.MaterialDialogHelper
import com.muhammad_ammar.tentwenty.view.activities.base.BaseActivity
import com.muhammad_ammar.tentwenty.view.fragments.base.BaseFragment


fun BaseFragment.setupProgressDialog(
    showHideProgress: LiveData<Event<Boolean>>,
    dialogHelper: MaterialDialogHelper
) {
    var mDialog: MaterialDialog? = null
    showHideProgress.observe(this, Observer {
        if (!it.consumed) it.consume()?.let { flag ->
            if (flag)
                mDialog?.show() ?: dialogHelper.showSimpleProgress(requireContext())
                    .also { dialog ->
                        mDialog = dialog
                    }
            else mDialog?.dismiss()
        }
    })
}

fun BaseActivity.setupProgressDialog(
    showHideProgress: LiveData<Event<Boolean>>,
    dialogHelper: MaterialDialogHelper
) {
    var mDialog: MaterialDialog? = null
    showHideProgress.observe(this, Observer {
        if (!it.consumed) it.consume()?.let { flag ->
            if (flag)
                mDialog?.show() ?: dialogHelper.showSimpleProgress(this)
                    .also { dialog ->
                        mDialog = dialog
                    }
            else mDialog?.dismiss()
        }
    })
}

/**
 * just for the custom dialog
 */
fun Context.showCustomLayoutDialog() {
    val dialog: AlertDialog
    val builder = AlertDialog.Builder(this)
    val inflater =
        this.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val v = inflater.inflate(R.layout.sample_dialog, null)
    /*val twitter: TextView
    val twitter1: TextView
    val twitter2: TextView
    val insta: TextView
    val insta1: TextView
    val insta2: TextView
    val snap: TextView
    val snap1: TextView
    val snap2: TextView
    twitter = v.findViewById(R.id.twitter)
    twitter1 = v.findViewById(R.id.twitter1)
    twitter2 = v.findViewById(R.id.twitter2)
    insta = v.findViewById(R.id.insta)
    insta1 = v.findViewById(R.id.insta1)
    insta2 = v.findViewById(R.id.insta2)
    snap = v.findViewById(R.id.snap)
    snap1 = v.findViewById(R.id.snap1)
    snap2 = v.findViewById(R.id.snap2)*/
    builder.setView(v)
    //update the text here or click listner here
    dialog = builder.create()
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.window!!.setDimAmount(0.2f)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE) //as the keyboard Open move the dialog up to th ekeyboard
    dialog.show()
}