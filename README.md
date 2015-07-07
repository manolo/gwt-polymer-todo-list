## GWT-Polymer-TODO

A 'TodoList' GWT application for mobile and desktop using material design.

It relies the UI part in Polymer Paper Elements collection, which is wrapped in java using 
the vaadin gwt-polymer-elements library.

There are two versions of the application, one buils with classic GWT widgets and another
with JsInterop Elements.

## Demos
 Visit our 
   [demo-widgets](http://manolo.github.io/gwt-polymer-todo-list/www/TodoListWidgets.html)
 or
   [demo-elements](http://manolo.github.io/gwt-polymer-todo-list/www/TodoListElements.html)

to see the application in action.

## Building

1. Asuming that you already have a running version of `git` and `maven`, check out the github project:

        $ git clone https://github.com/manolo/gwt-polymer-todo-list.git

2. Change to the new folder and run maven

        $ cd gwt-polymer-todo-list
        $ mvn clean package

3. Run any web server in the `www` folder

        $ serve www

