Let's create more complex application. In this tutorial we are going to create the Todo List application.

1.  Create `Main.java` and `Main.ui.xml`.

    You can generate these files using your IDE if you have installed GWT plugin. Otherwise you can copy the following snippets.
    
    ```java
    package com.example.client;
    
    import com.google.gwt.core.client.GWT;
    import com.google.gwt.uibinder.client.UiBinder;
    import com.google.gwt.user.client.ui.Composite;
    import com.google.gwt.user.client.ui.HTMLPanel;
    
    public class Main extends Composite {
        interface MainUiBinder extends UiBinder<HTMLPanel, Main> {
        }
    
        private static MainUiBinder ourUiBinder = GWT.create(MainUiBinder.class);
    
        public Main() {
            initWidget(ourUiBinder.createAndBindUi(this));
        }
    }
    ```
    
    ```xml
    <ui:UiBinder xmlns:ui='urn:ui:com.google.gwt.uibinder'
                 xmlns:g='urn:import:com.google.gwt.user.client.ui'>
    
        <g:HTMLPanel>
        
        </g:HTMLPanel>
    </ui:UiBinder>
    ```
    
2.  Add menu items

    Update `Main.ui.xml`.

    ```java
    <ui:UiBinder xmlns:ui='urn:ui:com.google.gwt.uibinder'
                 xmlns:g='urn:import:com.google.gwt.user.client.ui'
                 xmlns:p='urn:import:com.vaadin.polymer.paper.widget'
                 xmlns:i='urn:import:com.vaadin.polymer.iron.widget'>
    
        <g:HTMLPanel>
            <p:PaperIconItem ui:field="menuClearAll">
                <i:IronIcon icon="delete"/>
                <div>Clear All</div>
            </p:PaperIconItem>
            <p:PaperIconItem ui:field="menuClearDone">
                <i:IronIcon icon="clear"/>
                <div>Clear Done</div>
            </p:PaperIconItem>
            <p:PaperIconItem ui:field="menuSettings">
                <i:IronIcon icon="settings"/>
                <div>Settings</div>
            </p:PaperIconItem>
            <p:PaperIconItem ui:field="menuAbout">
                <i:IronIcon icon="help"/>
                <div>About</div>
            </p:PaperIconItem>
        </g:HTMLPanel>
    </ui:UiBinder>
    ```
    
    In this step we added necessary imports for paper and iron widget packages and four menu items. 
    [Here](https://elements.polymer-project.org/elements/paper-item?view=demo:demo/index.html) you can find paper icon item demos and documentation if you are interested in details.
    
3.  Update the entry point

    Replace PaperButton with our Main composite.

    ```java
    package com.example.client;
    
    import com.google.gwt.core.client.EntryPoint;
    import com.google.gwt.user.client.ui.RootPanel;
    
    public class TodoList implements EntryPoint {
    
        public void onModuleLoad() {
            RootPanel.get().add(new Main());
        }
    }
    ```
    
4.  Run the application

    You should see four menu items in browser now. Also you can notice that icons are missing.
    
5.  Fix icons

    We have to update our entry point a little.
    
    ```java
    package com.example.client;
    
    import com.google.gwt.core.client.EntryPoint;
    import com.google.gwt.user.client.ui.RootPanel;
    import com.vaadin.polymer.Polymer;
    import com.vaadin.polymer.elemental.Function;
    
    public class TodoList implements EntryPoint {
    
        public void onModuleLoad() {
            // We have to load icon sets before run application
            Polymer.importHref("iron-icons/iron-icons.html", new Function() {
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
    ```
    
    The goal is to import and load icon set before application is running.
    
6.  Run the application

    You should see icons in browser now.
    
7.  Add ripple effect

    It is nice if user gets feedback when clicking UI elements. Read [material design documentation about that](http://www.google.com.ua/design/spec/animation/responsive-interaction.html#responsive-interaction-radial-action).
    
    * Add `<p:PaperRipple/>` to each paper icon item.
    * Add styles (TODO: Why?)
    
    See updated `Main.ui.xml`.
    
    ```xml
    <ui:UiBinder xmlns:ui='urn:ui:com.google.gwt.uibinder'
                 xmlns:g='urn:import:com.google.gwt.user.client.ui'
                 xmlns:p='urn:import:com.vaadin.polymer.paper.widget'
                 xmlns:i='urn:import:com.vaadin.polymer.iron.widget'>
    
        <ui:style>
            paper-icon-item {
                position: relative;
                overflow: hidden;
            }
            .header {
                font-size: 200%;
                margin-left: 50px;
            }
        </ui:style>
    
        <g:HTMLPanel>
            <p:PaperIconItem ui:field="menuClearAll">
                <i:IronIcon icon="delete"/>
                <div>Clear All</div>
                <p:PaperRipple/>
            </p:PaperIconItem>
            <p:PaperIconItem ui:field="menuClearDone">
                <i:IronIcon icon="clear"/>
                <div>Clear Done</div>
                <p:PaperRipple/>
            </p:PaperIconItem>
            <p:PaperIconItem ui:field="menuSettings">
                <i:IronIcon icon="settings"/>
                <div>Settings</div>
                <p:PaperRipple/>
            </p:PaperIconItem>
            <p:PaperIconItem ui:field="menuAbout">
                <i:IronIcon icon="help"/>
                <div>About</div>
                <p:PaperRipple/>
            </p:PaperIconItem>
        </g:HTMLPanel>
    </ui:UiBinder>
    ```
    
8.  Run the application

    Compare click reaction before and after adding the PaperRipple.
    
9.  Add draw panel

    ```java
    <ui:UiBinder xmlns:ui='urn:ui:com.google.gwt.uibinder'
                 xmlns:g='urn:import:com.google.gwt.user.client.ui'
                 xmlns:p='urn:import:com.vaadin.polymer.paper.widget'
                 xmlns:i='urn:import:com.vaadin.polymer.iron.widget'>
    
        <ui:style>
            paper-icon-item {
                position: relative;
                overflow: hidden;
            }
            .header {
                font-size: 200%;
                margin-left: 50px;
            }
        </ui:style>
    
        <g:HTMLPanel>
            <p:PaperDrawerPanel ui:field="drawerPanel">
                <div drawer="">
                    <p:PaperHeaderPanel mode="seamed">
                        <p:PaperToolbar/>
                        <p:PaperIconItem ui:field="menuClearAll">
                            <i:IronIcon icon="delete"/>
                            <div>Clear All</div>
                            <p:PaperRipple/>
                        </p:PaperIconItem>
                        <p:PaperIconItem ui:field="menuClearDone">
                            <i:IronIcon icon="clear"/>
                            <div>Clear Done</div>
                            <p:PaperRipple/>
                        </p:PaperIconItem>
                        <p:PaperIconItem ui:field="menuSettings">
                            <i:IronIcon icon="settings"/>
                            <div>Settings</div>
                            <p:PaperRipple/>
                        </p:PaperIconItem>
                        <p:PaperIconItem ui:field="menuAbout">
                            <i:IronIcon icon="help"/>
                            <div>About</div>
                            <p:PaperRipple/>
                        </p:PaperIconItem>
                    </p:PaperHeaderPanel>
                </div>
                <div main="">
                    <p:PaperHeaderPanel mode="seamed">
                        <p:PaperToolbar addStyleNames="toolbar">
                            <p:PaperIconButton ui:field="menu" icon="more-vert" attributes="paper-drawer-toggle"/>
                            <span class="header">Todo List</span>
                        </p:PaperToolbar>
                    </p:PaperHeaderPanel>
                </div>
            </p:PaperDrawerPanel>
        </g:HTMLPanel>
    </ui:UiBinder>
    ```
    
    Now we have the menu panel and main panel where we will add some content soon. Both panels have toolbars.
    [Here](https://elements.polymer-project.org/elements/paper-drawer-panel?view=demo:demo/index.html) you can find drawer panel demos and documentation if you are interested in details.
    
    Also we added some styles for the heading text.
    
10. Run the application

    Now the application should look a bit better. Try to resize browser window. If you make it narrower then 640 pixels you can see that draw panel hides the menu.
    
11. Style toolbars

    TBC
    
12. Add content panel

    TBC

13. Add floating action button

    TBC
    
14. Run the application.

    TBC
