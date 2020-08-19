package com.code.test.altimetrik.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.code.test.altimetrik.R;

public class LoadingDialog {

    private Context context;
    private Dialog mDialog;

    public LoadingDialog(Context context) {
        this.context = context;
        setView();
    }

    void setView() {
        View view = View.inflate(context, R.layout.progress_bar, null);
        mDialog = new Dialog(context, R.style.NewDialog);

        mDialog.setCancelable(false);
        mDialog.setContentView(view);
    }

    public void show() {
        try {
            if(!mDialog.isShowing())
                mDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismiss() {
        try {
            if(mDialog.isShowing())
                mDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}