package org.gwtproject.tutorial.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.polymer.elemental.Function;
import com.vaadin.polymer.paper.widget.PaperButton;
import com.vaadin.polymer.paper.widget.PaperDialog;

public class Dialogs extends Composite {
    
    public static DialogsUiBinder getOurUiBinder() {
        return ourUiBinder;
    }

    public static void setOurUiBinder(DialogsUiBinder ourUiBinder) {
        Dialogs.ourUiBinder = ourUiBinder;
    }

    interface DialogsUiBinder extends UiBinder<HTMLPanel, Dialogs> {
    }

    private static DialogsUiBinder ourUiBinder = GWT.create(DialogsUiBinder.class);
    
    public Dialogs() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
    
    @UiField PaperDialog alert;
    @UiField DivElement alertContent;
    @UiField PaperButton alertClose;
    @UiHandler("alertClose")
    protected void alertClose(ClickEvent e) {
        alert.close();
    }
    
    @UiField PaperDialog confirm;
    @UiField Element confirmCaption;
    @UiField HTMLPanel confirmContent;
    @UiField PaperButton confirmCancel;
    @UiField PaperButton confirmOk;
    @UiHandler("confirmCancel")
    protected void confirmCancel(ClickEvent e) {
        confirm.close();
    }
    @UiHandler("confirmOk")
    protected void confirmOk(ClickEvent e) {
        if (okFunction != null) {
            okFunction.call(null);
        }
        confirm.close();
    }
    
    private Function<?, ?> okFunction;
    
    public void confirm(String message, Function<?, ?> fnc) {
        confirm("Confirm", new Label(message), fnc);
    }
    
    public void alert(String message) {
        alertContent.setInnerText(message);
        alert.open();
    }
    
    public void confirm(String header, Widget content, Function<?, ?> fnc) {
        okFunction = fnc;
        confirmCaption.setInnerText(header);
        confirmContent.clear();
        confirmContent.add(content);
        confirm.open();
    }
}
