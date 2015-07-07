package org.gwtproject.tutorial.client;

import static com.google.gwt.query.client.GQuery.$$;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.query.client.Properties;
import com.google.gwt.query.client.js.JsUtils;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.polymer.elemental.Function;
import com.vaadin.polymer.paper.widget.PaperDialog;
import com.vaadin.polymer.paper.widget.PaperDrawerPanel;
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
        restoreItems();
    }

    @UiField Widget addButton;
    @UiHandler("addButton")
    protected void onAddButtonClick(ClickEvent e) {
        addItemDialog.open();
    }

    @UiField PaperDialog addItemDialog;
    @UiField PaperInput titleInput;
    @UiField PaperTextarea descriptionInput;
    @UiHandler("confirmAddButton")
    protected void onConfirmAddButtonClick(ClickEvent e) {
        if (!titleInput.getValue().isEmpty()) {
            addItem(titleInput.getValue(), descriptionInput.getValue());
            saveItems();
            // clear text fields
            titleInput.setValue("");
            descriptionInput.setValue("");
        }
    }

    @UiField PaperIconItem menuClearAll;
    @UiHandler("menuClearAll")
    protected void menuClearAll(ClickEvent e) {
        closeMenu();
        dialogs.confirm("Do you really want to remove all Items in the list?", new Function() {
            public Object call(Object arg) {
                content.clear();
                saveItems();
                return null;
            }
        });
    }

    @UiField PaperIconItem menuClearDone;
    @UiHandler("menuClearDone")
    protected void menuClearDone(ClickEvent e) {
        closeMenu();
        for (int i = content.getWidgetCount() - 1; i > -1; i--) {
            Item item = (Item)content.getWidget(i);
            if (item.getCheck()) {
                content.remove(item);
                saveItems();
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

    private void saveItems() {
        JsArray<JavaScriptObject> toSave = JsArray.createArray().cast();
        for (int i = 0; i < content.getWidgetCount(); i++) {
            Item item = (Item)content.getWidget(i);
            toSave.push($$().set("title", item.getTitle()).set("notes", item.getDescription()));
        }
        Storage.getLocalStorageIfSupported().setItem("todos", JsUtils.JSON2String(toSave));
    }

    private void restoreItems() {
        String json = Storage.getLocalStorageIfSupported().getItem("todos");
        if (json != null && !json.isEmpty()) {
            JsArray<JavaScriptObject> restored = JsUtils.parseJSON(json).cast();
            for (int i = 0; i < restored.length(); i++) {
                Properties p = restored.get(i).cast();
                addItem(p.get("title"), p.get("notes"));
            }
        }
    }

}
