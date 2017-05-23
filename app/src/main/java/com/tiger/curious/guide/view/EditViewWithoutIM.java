package com.tiger.curious.guide.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by bkang016 on 5/20/17.
 */

public class EditViewWithoutIM extends EditText {


    public EditViewWithoutIM(Context context) {
        super(context);
    }

    public EditViewWithoutIM(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditViewWithoutIM(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onCheckIsTextEditor() {
        return true;
    }

    @Override
    public boolean isTextSelectable() {
        return true;
    }
}
