package org.gwtproject.tutorial.client;

import java.util.Arrays;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.paper.PaperButtonElement;
import com.vaadin.polymer.paper.PaperCheckboxElement;
import com.vaadin.polymer.paper.PaperDialogElement;
import com.vaadin.polymer.paper.PaperDrawerPanelElement;
import com.vaadin.polymer.paper.PaperFabElement;
import com.vaadin.polymer.paper.PaperHeaderPanelElement;
import com.vaadin.polymer.paper.PaperIconButtonElement;
import com.vaadin.polymer.paper.PaperIconItemElement;
import com.vaadin.polymer.paper.PaperInputElement;
import com.vaadin.polymer.paper.PaperRippleElement;
import com.vaadin.polymer.paper.PaperTextareaElement;
import com.vaadin.polymer.paper.PaperToolbarElement;

public class TodoList implements EntryPoint {

    public void onModuleLoad() {
        Polymer.startLoading();

        Polymer.importHref("paper-styles/demo-pages.html");

        Polymer.importHref(Arrays.asList(
            // We have to load icon sets
            "iron-icons/iron-icons.html",
            // And we have to load all web components in our application
            // before using them as custom elements.
            PaperDrawerPanelElement.SRC,
            PaperHeaderPanelElement.SRC,
            PaperToolbarElement.SRC,
            PaperIconItemElement.SRC,
            PaperRippleElement.SRC,
            PaperIconButtonElement.SRC,
            PaperFabElement.SRC,
            PaperDialogElement.SRC,
            PaperInputElement.SRC,
            PaperTextareaElement.SRC,
            PaperCheckboxElement.SRC,
            PaperButtonElement.SRC), arg -> {
            // The app is executed when all imports succeed.
            startApplication();
            return null;
        });
    }

    private void startApplication() {
        RootPanel.get().add(new Main());
    }
}
