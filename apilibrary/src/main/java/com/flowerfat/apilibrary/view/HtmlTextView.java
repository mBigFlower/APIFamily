package com.flowerfat.apilibrary.view;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 明明大美女 on 2015/10/23.
 */
public class HtmlTextView extends TextView {

    public HtmlTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(Html.fromHtml(text.toString()), type);
    }
}
