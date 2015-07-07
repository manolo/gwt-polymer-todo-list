# Create

In this section, you'll create the **TodoList** project using the GWT command-line utility [webAppCreator](http://www.gwtproject.org/doc/latest/RefCommandLineTools.html#webAppCreator).

We assume that you've already downloaded the most recent distribution of GWT, and you have [maven](https://maven.apache.org/) installed in your system.

## Using webAppCreator

The `webAppCreator` is a command-line tool included in the GWT SDK that generates the project subdirectories and files you need to get started. It creates a starter application, which you can run to ensure that all the components have been created and are hooked together correctly. As you develop your application, you'll replace the code for the starter application with your code.

For the **TodoList** project, you have to run `webAppCreator` with the following parameters.

| Parameter  | Definition                                     | Example      |
| ---------- | -----------------------------------------------| ------------ |
| -templates | Comma separated templates to use               | maven,sample |
| -out       | The directory to place the generated files.    | TodoList     |
| moduleName | The name of the GWT module you want to create. | org.gwtproject.tutorial.TodoList |


## Setting up a new project.

1. Create the **TodoList** application.

      GWT webAppCreator generates the project subdirectories and files you need to get

        $ /full_path_to_gwt_sdk/webAppCreator \
            -templates maven,sample \
            -out TodoListApp \
            org.gwtproject.tutorial.TodoList

      _**Tip**: If you include the GWT SDK folder in your PATH environment variable, you won't have to invoke them by specifying their full path._


2. Run the application in **SuperDevMode**.

     To check that all the project components were created start the new created app in SuperDevMode.

        $ mvn gwt:run

      _**Tip**: You might import the maven project in your IDE and use GWT plugin to run_

3. Launch your Browser.

     In opened window press the “Launch Default Browser” button to launch our application using your default browser. Or, you can click “Copy to Clipboard” to copy the launch URL and paste it into the browser of your choice.

     If you change something in the code you can recompile the application just reloading the page in your browser. Otherwise if you change some configuration files like `pom.xml`, `webapp` static content, etc, you would have to stop SuperDevMode pushing `Ctrl-C` and running again `mvn gwt:run`.

4. Add vaadin polymer elements dependency to your pom.xml

        <dependency>
         <groupId>com.vaadin.polymer</groupId>
         <artifactId>gwt-polymer-elements</artifactId>
         <version>${gwtPolymerVersion}</version>
         <scope>provided</scope>
        </dependency>
     _**Note**: Replace the `${gwtPolymerVersion}` placeholder with current version or add the correspondent property in your pom.xml_

5. Update the gwt-maven-plugin configuration to support the experimental `JsInterop` feature.

        <plugin>
          <groupId>org.codehaus.mojo</groupId>
          <artifactId>gwt-maven-plugin</artifactId>
          ...
          <configuration>
            <jsInteropMode>JS</jsInteropMode>
            ...
          </configuration>
        </plugin>

6. Update `Todo.gwt.xml` file

        <module rename-to="todolist">
          ...
          <inherits name="com.vaadin.polymer.Elements"/>
          ...
        </module>

7. Update `TodoList.html`
    * Configure `<meta>` viewport to deal with mobile screens.
    * Import the polyfill `<script>` for non web-component capable browsers.
    * Remove all the content from `<body>`.

            <!doctype html>
            <html>
            <head>
             <meta name="viewport"
                 content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1" />
             <script src="todo/bower_components/webcomponentsjs/webcomponents.js"></script>
             <script type="text/javascript" src="todo/todo.nocache.js"></script>
            </head>
            <body>
            </body>
            </html>

8. Remove `greetServlet` and it's mapping from `WEB-INF/web-xml`

        <web-app>
        </web-app>

9. Remove all unnecessary files.

    *  Remove folders `server` and `shared` under `src/main/java/org/gwtproject/tutorial` folder
    *  Remove `GreetingService.java` and `GreetingServiceAsync.java` classes fron the client package as well.
    *  Remove example tests `src/main/test`

10. Update the **EntryPoint**

    Replace the content of `TodoList.java` with

        package org.gwtproject.tutorial;

        import com.google.gwt.core.client.EntryPoint;
        import com.google.gwt.user.client.ui.RootPanel;
        import com.vaadin.polymer.paper.widget.PaperButton;

        public class TodoList implements EntryPoint {
          public void onModuleLoad() {
            // Use Widget API to Create a <paper-button>
            PaperButton button = new PaperButton("Press me!");
            button.setRaised(true);
            RootPanel.get().add(button);
        
        
            // Use the Element API to create a <paper-button>
            PaperButtonElement buttonElement = 
               Polymer.createElement(PaperButtonElement.TAG);
            buttonElement.setTextContent("Click me!");
            Document.get().getBody().appendChild((Element)buttonElement);            
          }
        }

    _**Note**: that we have added the PaperButton element here, but using different API's: the classic GWT Widget approach and Elements._

11. Run the application again.

    You should see a page with a material design button in your browser.

    _**Tip**: If you get ClassCastException in browser console, ensure that you use `-XjsInteropMode JS` parameter_

## What's next

In this lesson we have learnt

- How to create a new maven project
- Running our project in SuperDevMode
- Adding external dependencies to our application
- Configure our project to use experimental `JsInterop` mode.
- Replacing starter code by our own.

Now you are prepared to create the UI of the application.

But you have two options, you can continue the Tutorial using Classic GWT widgets or the Elements API, which is the modern tendency in GWT.

[Step 2a: Building the User Interface using Widgets](widgets-buildui.html)

[Step 2b: Building the User Interface using Elements](elements-buildui.html)
