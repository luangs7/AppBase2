package br.com.luan.clubeprime.model;

import br.com.luan.clubeprime.R;

/**
 * Created by DevMaker on 17/08/2015.
 */
public class LeftMenu {

    int id;
    String name;
    int icon;
    int icon_active;
    boolean active;
    int backgoundColor;

    public LeftMenu(int id, String name, int icon, int icon_active, boolean active) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.active = active;
        this.icon_active = icon_active;
        if(active) {
            this.backgoundColor = R.color.menuactive;
        }else
            this.backgoundColor = R.color.menudesactive;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        if(active) {
            this.backgoundColor = R.color.menuactive;
        }else
            this.backgoundColor = R.color.menudesactive;
    }

    public int getIcon_active() {
        return icon_active;
    }

    public void setIcon_active(int icon_active) {
        this.icon_active = icon_active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBackgoundColor() {
        return backgoundColor;
    }

    public void setBackgoundColor(int backgoundColor) {
        this.backgoundColor = backgoundColor;
    }
}
