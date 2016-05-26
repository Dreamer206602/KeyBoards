package com.mx.keyboards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mx.keyboardlibrary.KeyboardTouchListener;
import com.mx.keyboardlibrary.KeyboardUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.normal_ed)
    EditText mNormalEd;
    @Bind(R.id.special_ed)
    EditText mSpecialEd;
    @Bind(R.id.other_test)
    TextView mOtherTest;
    @Bind(R.id.all_ed)
    LinearLayout mAllEd;
    @Bind(R.id.sv_main)
    ScrollView mSvMain;
    @Bind(R.id.root_view)
    LinearLayout mRootView;

    private KeyboardUtil mKeyboardUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mKeyboardUtil=new KeyboardUtil(this,mRootView,mSvMain);

        mKeyboardUtil.setOtherEdittext(mNormalEd);

        // monitor the KeyBoard state
        mKeyboardUtil.setKeyBoardStateChangeListener(new KeyBoardStateListener());

        //monitor the finish or next Key
        mKeyboardUtil.setInputOverListener(new InputListener());

        mSpecialEd.setOnTouchListener(new KeyboardTouchListener(mKeyboardUtil,KeyboardUtil.INPUTTYPE_ABC,-1));



    }

    //状态监听
    class  KeyBoardStateListener implements  KeyboardUtil.KeyBoardStateChangeListener{

        @Override
        public void KeyBoardStateChange(int state, EditText editText) {

            switch (state){
                case KeyboardUtil.KEYBOARD_SHOW:
                    Log.d("Main",KeyboardUtil.KEYBOARD_SHOW+"");

                    mKeyboardUtil.showKeyboard();
                    break;
                case KeyboardUtil.KEYBOARD_HIDE:
                    mKeyboardUtil.hideAllKeyBoard();
                    Log.d("Main",KeyboardUtil.KEYBOARD_HIDE+"");
                    break;
            }


        }
    }

    //输入监听
    class InputListener implements  KeyboardUtil.InputFinishListener{

        @Override
        public void inputHasOver(int onclickType, EditText editText) {

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mKeyboardUtil.hideAllKeyBoard();
    }
}
