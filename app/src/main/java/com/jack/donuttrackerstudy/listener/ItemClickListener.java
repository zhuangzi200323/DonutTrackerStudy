package com.jack.donuttrackerstudy.listener;

import com.jack.donuttrackerstudy.model.Donut;

public interface ItemClickListener {
    void delete(Donut donut);
    void edit(Donut donut);
}
