package org.gwtproject.tutorial.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.vaadin.polymer.paper.PaperCheckboxElement;

public class Item {

    private final DivElement element;

    interface ItemUiBinder extends UiBinder<DivElement, Item> {
    }

    private static ItemUiBinder ourUiBinder = GWT.create(ItemUiBinder.class);

    @UiField Element title;
    @UiField Element description;
    @UiField PaperCheckboxElement done;

    public Item() {
        element = ourUiBinder.createAndBindUi(this);

        done.addEventListener("iron-change", e -> {
            if (done.getActive()) {
                title.addClassName("done");
            } else {
                title.removeClassName("done");
            }
        });
    }

    public String getTitle() {
        return title.getInnerText();
    }

    public void setTitle(String s) {
        title.setInnerText(s);
    }

    public String getDescription() {
        return description.getInnerText();
    }

    public void setDescription(String s) {
        description.setInnerText(s);
    }

    public boolean isDone() {
        return done.getActive();
    }

    public void setDone(boolean b) {
        done.setActive(b);
    }

    public DivElement getElement() {
        return element;
    }
}
