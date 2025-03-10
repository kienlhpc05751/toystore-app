package com.toystore.event;

import com.toystore.model.store.product;
import java.awt.Component;

public interface EventItem {

    public void itemClick(Component com, product item);
}
