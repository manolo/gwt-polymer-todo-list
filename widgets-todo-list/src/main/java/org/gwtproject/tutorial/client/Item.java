package org.gwtproject.tutorial.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.vaadin.polymer.iron.widget.event.IronChangeEvent;
import com.vaadin.polymer.paper.widget.PaperCheckbox;

public class Item extends Composite {
    
    public static ItemUiBinder getOurUiBinder() {
        return ourUiBinder;
    }

    public static void setOurUiBinder(ItemUiBinder ourUiBinder) {
        Item.ourUiBinder = ourUiBinder;
    }

    interface ItemUiBinder extends UiBinder<HTMLPanel, Item> {
    }

    private static ItemUiBinder ourUiBinder = GWT.create(ItemUiBinder.class);
    
    @UiField Element title;
    @UiField Element description;
    @UiField PaperCheckbox check;

    public Item() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
    
    @UiHandler("check")
    protected void change(IronChangeEvent ev) {
        if (check.getActive()) {
            title.addClassName("done");
        } else {
            title.removeClassName("done");
        }
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
    
    public boolean getCheck() {
        return check.getActive();
    }

    public void setCheck(boolean b) {
        check.setActive(b);
    }
}
