package com.mx.keyboardlibrary;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by hww on 2016/5/26.
 */
public class KeyboardTouchListener  implements View.OnTouchListener {
    private KeyboardUtil keyboardUtil;
    private int keyboardType = 1;
    private int scrollTo = -1;

    public KeyboardTouchListener(KeyboardUtil util, int keyboardType, int scrollTo) {
        this.keyboardUtil = util;
        this.keyboardType = keyboardType;
        this.scrollTo = scrollTo;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (keyboardUtil != null && keyboardUtil.getEd() != null && v.getId() != keyboardUtil.getEd().getId())
                keyboardUtil.showKeyBoardLayout((EditText) v, keyboardType, scrollTo);
            else if (keyboardUtil != null && keyboardUtil.getEd() == null) {
                keyboardUtil.showKeyBoardLayout((EditText) v, keyboardType, scrollTo);
            } else {
//                Log.d("KeyboardTouchListener", "v.getId():" + v.getId());
//                Log.d("KeyboardTouchListener", "keyboardUtil.getEd().getId():" + keyboardUtil.getEd().getId());
                if (keyboardUtil != null) {
                    keyboardUtil.setKeyBoardCursorNew((EditText) v);
                }
            }
        }
        return false;
    }
}