package com.example.android.recyclerview;

/**
 * @author hendrawd on 6/16/17
 */

public class GridViewWithHeaderItem {
    private boolean isHeader;
    private String text;

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
