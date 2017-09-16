## GWT-Polymer-TODO

[0]: https://www.polymer-project.org/1.0/
[2]: http://www.google.es/design/spec/material-design/introduction.html
[3]: https://github.com/vaadin/gwt-polymer-elements
[4]: https://github.com/vaadin/gwt-api-generator
[5]: http://manolo.github.io/gwt-polymer-todo-list/demo/TodoListWidgets.html
[6]: http://manolo.github.io/gwt-polymer-todo-list/demo/TodoListElements.html

A **TodoList** GWT application for mobile and desktop using [Material Design][2] specification.

It relies the UI part on [Polymer][0], Paper Elements [collections][5], and a the [gwt-polymer-elements][3] wrapper.

In this repository, you have two versions of the **TodoList** application, one is built using classic GWT widgets, and another with modern JsInterop Elements.

## Demos
 Try our 
   [demo-widgets][5]
 or
   [demo-elements][6]

to see the application in action.

## Building

1. Asuming that you already have a running version of `git` and `maven`, check out the github project:

        $ git clone https://github.com/manolo/gwt-polymer-todo-list.git

2. Change to the new folder and run maven

        $ cd gwt-polymer-todo-list
        $ mvn clean package

3. Run any web server in the `www` folder

        $ serve www

