Add functional
===

1.  Create Add Item dialog
    
    * Add the following markup to the Main.ui.xml file
    
    ```xml
    <p:PaperDialog ui:field="addItemDialog" entryAnimation="fade-in-animation" addStyleNames="{style.dialog}" modal="true">
        <h2>Add Item</h2>
        <p:PaperInput ui:field="titleInput" label="Title" required="true" autoValidate="true" errorMessage="required input!"/>
        <div class="textarea-container iron-autogrow-textarea">
            <p:PaperTextarea ui:field="descriptionInput" label="Notes"/>
        </div>
        <div class="buttons">
            <p:PaperButton attributes="dialog-dismiss">Cancel</p:PaperButton>
            <p:PaperButton ui:field="confirmAddButton" attributes="dialog-confirm">OK</p:PaperButton>
        </div>
    </p:PaperDialog>
    ```
    
2.  Add field `addItemDialog` to the Main class
    ```java
    @UiField
    PaperDialog addItemDialog;
    ```
    
3.  Add fab (floating action button) click handler to the Main class
    ```java
    @UiHandler("addButton")
    protected void onAddButtonClick(ClickEvent e) {
        addItemDialog.open();
    }
    ```
4.  Run application

    Now you can open dialog by clicking on round action button in right bottom corner.
    
5.  Add field `titleInput`, `descriptionInput` and `content` to the Main class

    ```java
    @UiField
    HTMLPanel content;

    @UiField
    PaperInput titleInput;
    @UiField
    PaperTextarea descriptionInput;
    ```
    
6.  Create item widget (Item.ui.xml and Item.java)
    
    You can generate these files using your IDE if you have installed GWT plugin. Otherwise you can copy the following snippets.
    
    ```xml
    <ui:UiBinder xmlns:ui='urn:ui:com.google.gwt.uibinder'
    			 xmlns:g='urn:import:com.google.gwt.user.client.ui'
    			 xmlns:p='urn:import:com.vaadin.polymer.paper.widget'>
    
        <g:HTMLPanel addStyleNames="vertical center-justified layout">
            <style>
    	        .title {
    	          padding-left: 20px;
    	          font-size: 150%;
    	          font-weight: normal;
    	        }
    	        .done {
    	          text-decoration: line-through;
    	        }
    	        .paper-checkbox {
    	          top: -2px;
    	        }
            </style>
    		<div class="vertical-section">
    			<h4>
    				<p:PaperCheckbox ui:field="done"/>
    				<span ui:field="title" class='title'>Go to Google</span>
    			</h4>
    			<div ui:field="description"/>
    		</div>
        </g:HTMLPanel>
    </ui:UiBinder>
    ```
    
    ```java
    package com.example.client;
    
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
        
        interface ItemUiBinder extends UiBinder<HTMLPanel, Item> {
        }
    
        private static ItemUiBinder ourUiBinder = GWT.create(ItemUiBinder.class);
        
        @UiField
        Element title;
        @UiField
        Element description;
        @UiField
        PaperCheckbox done;
    
        public Item() {
            initWidget(ourUiBinder.createAndBindUi(this));
        }
        
        @UiHandler("done")
        protected void change(IronChangeEvent ev) {
            if (done.getActive()) {
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
        
        public boolean isDone() {
            return done.getActive();
        }
    
        public void setDone(boolean b) {
            done.setActive(b);
        }
    }
    ```
    
7.  Add dialog "OK" button handler

    ```java
    @UiHandler("confirmAddButton")
    protected void onConfirmAddButtonClick(ClickEvent e) {
        if (!titleInput.getValue().isEmpty()) {
            addItem(titleInput.getValue(), descriptionInput.getValue());
            // clear text fields
            titleInput.setValue("");
            descriptionInput.setValue("");
        }
    }

    private void addItem(String title, String description) {
        Item item = new Item();
        item.setTitle(title);
        item.setDescription(description);
        content.add(item);
    }
    ```
    
    At this point your Main.java should be like this:
    
    ```java
    package com.example.client;
    
    import com.google.gwt.core.client.GWT;
    import com.google.gwt.event.dom.client.ClickEvent;
    import com.google.gwt.uibinder.client.UiBinder;
    import com.google.gwt.uibinder.client.UiField;
    import com.google.gwt.uibinder.client.UiHandler;
    import com.google.gwt.user.client.ui.Composite;
    import com.google.gwt.user.client.ui.HTMLPanel;
    import com.vaadin.polymer.paper.widget.*;
    
    public class Main extends Composite {
        interface MainUiBinder extends UiBinder<HTMLPanel, Main> {
        }
    
        private static MainUiBinder ourUiBinder = GWT.create(MainUiBinder.class);
    
        @UiField
        HTMLPanel content;
    
        @UiField
        PaperDialog addItemDialog;
        @UiField
        PaperInput titleInput;
        @UiField
        PaperTextarea descriptionInput;
    
        public Main() {
            initWidget(ourUiBinder.createAndBindUi(this));
        }
    
        @UiHandler("addButton")
        protected void onAddButtonClick(ClickEvent e) {
            addItemDialog.open();
        }
    
        @UiHandler("confirmAddButton")
        protected void onConfirmAddButtonClick(ClickEvent e) {
            if (!titleInput.getValue().isEmpty()) {
                addItem(titleInput.getValue(), descriptionInput.getValue());
                // clear text fields
                titleInput.setValue("");
                descriptionInput.setValue("");
            }
        }
    
        private void addItem(String title, String description) {
            Item item = new Item();
            item.setTitle(title);
            item.setDescription(description);
            content.add(item);
        }
    }
    ```
    
8.  Run application

    Now you can add Todo items and mark them as done using checkboxes.
    
9.  Add "Clear All" menu item handler

    * Add field for drawer panel
    ```java
    @UiField
    PaperDrawerPanel drawerPanel;
    ```
    
    * Add click handler
    ```java
    @UiHandler("menuClearAll")
    protected void menuClearAll(ClickEvent e) {
        closeMenu();
        content.clear();
    }
    
    private void closeMenu() {
        if (drawerPanel.getNarrow()) {
            drawerPanel.closeDrawer();
        }
    }
    ```
    
    The closeMenu() method is only useful if you open application on mobile device or make your browser window narrow enough to collapse side menu. We have to hide menu after click.
    
10. Add "Clear Done" menu item handler

    ```java
    @UiHandler("menuClearDone")
    protected void menuClearDone(ClickEvent e) {
        closeMenu();
        for (int i = content.getWidgetCount() - 1; i > -1; i--) {
            Item item = (Item)content.getWidget(i);
            if (item.isDone()) {
                content.remove(item);
            }
        }
    }
    ```
    
    Final Main.java should be like this
    
    ```java
    package com.example.client;
    
    import com.google.gwt.core.client.GWT;
    import com.google.gwt.event.dom.client.ClickEvent;
    import com.google.gwt.uibinder.client.UiBinder;
    import com.google.gwt.uibinder.client.UiField;
    import com.google.gwt.uibinder.client.UiHandler;
    import com.google.gwt.user.client.ui.Composite;
    import com.google.gwt.user.client.ui.HTMLPanel;
    import com.vaadin.polymer.paper.widget.PaperDialog;
    import com.vaadin.polymer.paper.widget.PaperDrawerPanel;
    import com.vaadin.polymer.paper.widget.PaperInput;
    import com.vaadin.polymer.paper.widget.PaperTextarea;
    
    public class Main extends Composite {
        interface MainUiBinder extends UiBinder<HTMLPanel, Main> {
        }
    
        private static MainUiBinder ourUiBinder = GWT.create(MainUiBinder.class);
    
        @UiField
        PaperDrawerPanel drawerPanel;
        @UiField
        HTMLPanel content;
    
        @UiField
        PaperDialog addItemDialog;
        @UiField
        PaperInput titleInput;
        @UiField
        PaperTextarea descriptionInput;
    
        public Main() {
            initWidget(ourUiBinder.createAndBindUi(this));
        }
    
        @UiHandler("addButton")
        protected void onAddButtonClick(ClickEvent e) {
            addItemDialog.open();
        }
    
        @UiHandler("confirmAddButton")
        protected void onConfirmAddButtonClick(ClickEvent e) {
            if (!titleInput.getValue().isEmpty()) {
                addItem(titleInput.getValue(), descriptionInput.getValue());
                // clear text fields
                titleInput.setValue("");
                descriptionInput.setValue("");
            }
        }
    
        private void addItem(String title, String description) {
            Item item = new Item();
            item.setTitle(title);
            item.setDescription(description);
            content.add(item);
        }
    
        @UiHandler("menuClearAll")
        protected void menuClearAll(ClickEvent e) {
            closeMenu();
            content.clear();
        }
        
        private void closeMenu() {
            if (drawerPanel.getNarrow()) {
                drawerPanel.closeDrawer();
            }
        }
    
        @UiHandler("menuClearDone")
        protected void menuClearDone(ClickEvent e) {
            closeMenu();
            for (int i = content.getWidgetCount() - 1; i > -1; i--) {
                Item item = (Item)content.getWidget(i);
                if (item.isDone()) {
                    content.remove(item);
                }
            }
        }
    }
    ```
    
11. Run application

    * Add several items
    * Mark some of them as done
    * Click on "Clear Done" menu item
    * Click on "Clear All" menu item