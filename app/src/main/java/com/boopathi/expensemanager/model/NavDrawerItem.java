package com.boopathi.expensemanager.model;

/**
 * Created by Boopathi on 10-03-2016.
 */
public class NavDrawerItem {
    private boolean showNotify;
    private String title;
    private int titleIcon;

    public int getTitleIcon() {
        return titleIcon;
    }

    public void setTitleIcon(int titleIcon) {
        this.titleIcon = titleIcon;
    }

    public NavDrawerItem() {
    }

    public NavDrawerItem(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
