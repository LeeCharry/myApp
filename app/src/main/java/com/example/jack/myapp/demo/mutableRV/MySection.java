package com.example.jack.myapp.demo.mutableRV;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by XPERIA on 2018/8/2.
 */

public class MySection extends SectionEntity<myitem> {
    private boolean isMore;

    public MySection(boolean isHeader, String header, boolean isMroe) {
        super(isHeader, header);
        this.isMore = isMroe;
    }

    public MySection(myitem t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }
}
