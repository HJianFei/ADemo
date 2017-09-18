package com.apace.ydimall.widget.pinyin;


import com.apace.ydimall.bean.FriendBean;

import java.util.Comparator;

/**
 * @author
 */
public class PinyinComparator implements Comparator<FriendBean> {


    public static PinyinComparator instance = null;

    public static PinyinComparator getInstance() {
        if (instance == null) {
            instance = new PinyinComparator();
        }
        return instance;
    }

    public int compare(FriendBean o1, FriendBean o2) {
        if (o1.letters.equals("@")
                || o2.letters.equals("#")) {
            return -1;
        } else if (o1.letters.equals("#")
                || o2.letters.equals("@")) {
            return 1;
        } else {
            return o1.letters.compareTo(o2.letters);
        }
    }

}
