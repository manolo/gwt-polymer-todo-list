package org.gwtproject.tutorial.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.PolymerWidget.TapEvent;
import com.vaadin.polymer.paper.widget.PaperButton;
import com.vaadin.polymer.paper.widget.PaperDialog;
import com.vaadin.polymer.paper.widget.PaperDrawerPanel;
import com.vaadin.polymer.paper.widget.PaperFab;
import com.vaadin.polymer.paper.widget.PaperIconButton;
import com.vaadin.polymer.paper.widget.PaperIconItem;
import com.vaadin.polymer.paper.widget.PaperInput;
import com.vaadin.polymer.paper.widget.PaperTextarea;

public class Main extends Composite {

    interface MainUiBinder extends UiBinder<HTMLPanel, Main> {
    }

    private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);

    @UiField PaperDrawerPanel drawerPanel;
    @UiField HTMLPanel content;
    @UiField PaperIconButton menu;
    @UiField Dialogs dialogs;

    public Main() {
        initWidget(uiBinder.createAndBindUi(this));
        Polymer.endLoading(this.getElement(), addButton.getElement());
    }

    @UiField PaperFab addButton;
    @UiHandler("addButton")
    protected void onAddButtonClick(TapEvent e) {
        addItemDialog.open();
    }

    @UiField PaperDialog addItemDialog;
    @UiField PaperInput titleInput;
    @UiField PaperTextarea descriptionInput;
    @UiField PaperButton confirmAddButton;
    @UiHandler("confirmAddButton")
    protected void onConfirmAddButtonTap(TapEvent e) {
        if (!titleInput.getValue().isEmpty()) {
            addItem(titleInput.getValue(), descriptionInput.getValue());
            // clear text fields
            titleInput.setValue("");
            descriptionInput.setValue("");
        }
    }

    @UiField PaperIconItem menuClearAll;
    @UiHandler("menuClearAll")
    protected void menuClearAll(TapEvent e) {
        closeMenu();
        if (content.getWidgetCount() > 0) {
            dialogs.confirm("Do you really want to remove all Items in the list?", arg -> {
                  content.clear();
                  return null;
            });
        }
    }

    @UiField PaperIconItem menuClearDone;
    @UiHandler("menuClearDone")
    protected void menuClearDone(TapEvent e) {
        closeMenu();
        for (int i = content.getWidgetCount() - 1; i > -1; i--) {
            Item item = (Item)content.getWidget(i);
            if (item.getCheck()) {
                content.remove(item);
            }
        }
    }

    @UiField PaperIconItem menuSettings;
    @UiField PaperIconItem menuAbout;
    @UiHandler({"menuSettings", "menuAbout"})
    protected void norImplemented(ClickEvent e) {
        closeMenu();
        dialogs.alert("Not implemented yet");
    }

    private void closeMenu() {
        if (drawerPanel.getNarrow()) {
            drawerPanel.closeDrawer();
        }
    }

    private void addItem(String title, String description) {
        Item i = new Item();
        i.setTitle(title);
        i.setDescription(description);
        content.add(i);
    }

}
