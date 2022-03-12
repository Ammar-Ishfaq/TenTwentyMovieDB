package com.muhammad_ammar.tentwenty.util;

import android.app.ProgressDialog;

/**
 *
 */
class Utils {
    public void progress(ProgressDialog progressDialog, boolean isShow) {
        progressDialog.setTitle("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        if (isShow)
            progressDialog.show();
        else
            progressDialog.dismiss();
    }
}
