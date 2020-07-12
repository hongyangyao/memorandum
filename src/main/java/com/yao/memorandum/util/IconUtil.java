package com.yao.memorandum.util;

import com.yao.memorandum.file.Path;

import javax.swing.*;
import java.net.URL;

public class IconUtil {

    private static final URL def = Path.getClassPath("default.png");
    private static final URL top = Path.getClassPath(  "top.png");
    private static final URL finish = Path.getClassPath(  "finish.png");

    private static final ImageIcon DEF_ICON = new ImageIcon(def);
    private static final ImageIcon TOP_ICON = new ImageIcon(top);
    private static final ImageIcon FINISH_ICON = new ImageIcon(finish);

    public static ImageIcon getIcon(Icon icon) {
        return icon.icon;
    }

    public enum Icon {
        DEFAULT(def, DEF_ICON),
        TOP(top, TOP_ICON),
        FINISH(finish, FINISH_ICON);
        private URL path;
        private ImageIcon icon;

        Icon(URL path, ImageIcon icon) {
            this.path = path;
            this.icon = icon;
        }
    }
}
