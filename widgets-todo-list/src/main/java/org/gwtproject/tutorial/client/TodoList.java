package org.gwtproject.tutorial.client;

import java.util.Arrays;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.elemental.Function;

public class TodoList implements EntryPoint {

    public void onModuleLoad() {
        // Show a loading message
        Polymer.startLoading();

        // Paper applications must always import paper-styles
        Polymer.importHref("paper-styles");
        // Styles for paper examples, we use it for this app as well
        Polymer.importHref("paper-styles/demo-pages.html");

        // We have to load icon sets before run Application
        Polymer.importHref(Arrays.asList(
                "iron-icons/iron-icons.html",
                "iron-icons/communication-icons.html",
                "neon-animation/neon-animations.html"
                ), new Function() {
            public Object call(Object arg) {
                // The app is executed when all imports succeed.
                startApplication();
                return null;
            }
        });
    }

    private void startApplication() {
        RootPanel.get().add(new Main());
    }
}
