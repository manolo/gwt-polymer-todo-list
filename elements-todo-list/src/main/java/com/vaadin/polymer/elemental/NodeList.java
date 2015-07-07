package com.vaadin.polymer.elemental;

import com.google.gwt.core.client.js.JsProperty;
import com.google.gwt.core.client.js.JsType;

@JsType
public interface NodeList<T extends Node> extends EventTarget {

    @JsProperty
    int getLength();

    T item(int index);
}
