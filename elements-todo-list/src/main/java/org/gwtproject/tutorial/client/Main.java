package org.gwtproject.tutorial.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.elemental.HTMLElement;
import com.vaadin.polymer.paper.PaperButtonElement;
import com.vaadin.polymer.paper.PaperDialogElement;
import com.vaadin.polymer.paper.PaperDrawerPanelElement;
import com.vaadin.polymer.paper.PaperFabElement;
import com.vaadin.polymer.paper.PaperIconItemElement;
import com.vaadin.polymer.paper.PaperInputElement;
import com.vaadin.polymer.paper.PaperTextareaElement;

public class Main extends Composite {
    interface MainUiBinder extends UiBinder<HTMLPanel, Main> {
    }

    private static MainUiBinder ourUiBinder = GWT.create(MainUiBinder.class);

    @UiField PaperDrawerPanelElement drawerPanel;

    @UiField PaperIconItemElement menuClearAll;
    @UiField PaperIconItemElement menuClearDone;

    @UiField HTMLElement content;
    @UiField PaperFabElement addButton;

    @UiField PaperDialogElement addItemDialog;
    @UiField PaperInputElement titleInput;
    @UiField PaperTextareaElement descriptionInput;
    @UiField PaperButtonElement confirmAddButton;

    // Our data base is just an array of items in memory
    private List<Item> items = new ArrayList<>();

    public Main() {
        initWidget(ourUiBinder.createAndBindUi(this));
        Polymer.endLoading(this.getElement(), (Element)addButton);

        addButton.addEventListener("tap", e -> addItemDialog.open());

        confirmAddButton.addEventListener("tap", e -> {
            if (!titleInput.getValue().isEmpty()) {
                addItem(titleInput.getValue(), descriptionInput.getValue());
                // clear text fields
                titleInput.setValue("");
                descriptionInput.setValue("");
            }
        });

        menuClearAll.addEventListener("tap", e -> {
            closeMenu();
            // remove all child elements
            while (content.hasChildNodes()) {
                content.removeChild(content.getFirstChild());
            }
        });

        menuClearDone.addEventListener("tap", e -> {
            closeMenu();
            for (Item item : items) {
                if (item.isDone()) {
                    content.removeChild(item.getElement());
                    items.remove(item);
                }
            }
        });
    }

    private void addItem(String title, String description) {
        Item item = new Item();
        item.setTitle(title);
        item.setDescription(description);
        content.appendChild(item.getElement());
        items.add(item);
    }

    private void closeMenu() {
        if (drawerPanel.getNarrow()) {
            drawerPanel.closeDrawer();
        }
    }
}
