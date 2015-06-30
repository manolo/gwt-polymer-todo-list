Create
===

At this point, you've downloaded the most recent distribution of Google Web Toolkit.

In this section, you'll create the Todo List project using the GWT command-line utility webAppCreator.

### Creating the Todo List application using webAppCreator

[webAppCreator](http://www.gwtproject.org/doc/latest/RefCommandLineTools.html#webAppCreator) is a command-line tool included in the GWT download that generates the project subdirectories and files you need to get started. It creates a starter application, which you can run to ensure that all the components have been created and are hooked together correctly. As you develop your application, you'll replace the code for the starter application with your code.

For the Todo List project, you will run webAppCreator with the following parameters.

| Parameter  | Definition                                                                                                                                                                                                | Example |
| ---------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------- |
| -maven     | Generate pom.xml.                                                                                                                                                               |  |
| -out       | The directory to place the generated files.                                                                                                                                                               | TodoList |
| moduleName | The name of the GWT module you want to create.                                                                                                                                                            | com.example.TodoList |

1.  Create the StockWatcher application.
    *  At the command line, run webAppCreator.

       `webAppCreator -maven -out TodoListApp com.example.TodoList`

    **Tip:** If you include the GWT command-line tools in your PATH environment variable, you won't have to invoke them by specifying their full path.

2.  GWT webAppCreator generates the project subdirectories and files you need to get started.

   ```
   'maven' template is being generated removing 'eclipse' template from generated output.
   'maven' template is being generated removing 'ant' template from generated output.
   Generating from templates: [maven, readme, _sample-test, sample]
   Created directory TodoListApp
   Created directory TodoListApp/src/test/java
   Created directory TodoListApp/src/test/java/com/example
   Created directory TodoListApp/src/test/java/com/example/client
   Created directory TodoListApp/src/main/java
   Created directory TodoListApp/src/main/java/com/example
   Created directory TodoListApp/src/main/java/com/example/client
   Created directory TodoListApp/src/main/java/com/example/server
   Created directory TodoListApp/src/main/java/com/example/shared
   Created directory TodoListApp/src/main/webapp
   Created directory TodoListApp/src/main/webapp/WEB-INF
   Created file TodoListApp/pom.xml
   Created file TodoListApp/README.txt
   Created file TodoListApp/src/test/java/com/example/TodoListJUnit.gwt.xml
   Created file TodoListApp/src/test/java/com/example/client/TodoListTest.java
   Created file TodoListApp/src/main/java/com/example/TodoList.gwt.xml
   Created file TodoListApp/src/main/java/com/example/client/GreetingService.java
   Created file TodoListApp/src/main/java/com/example/client/GreetingServiceAsync.java
   Created file TodoListApp/src/main/java/com/example/client/TodoList.java
   Created file TodoListApp/src/main/java/com/example/server/GreetingServiceImpl.java
   Created file TodoListApp/src/main/java/com/example/shared/FieldVerifier.java
   Created file TodoListApp/src/main/webapp/WEB-INF/web.xml
   Created file TodoListApp/src/main/webapp/TodoList.css
   Created file TodoListApp/src/main/webapp/TodoList.html
   Created file TodoListApp/src/main/webapp/favicon.ico
   ```

3.  Run the Todo List application

   To check that all the project components were created, run the starter application.
   
      *  At the command line, run
       
          `mvn gwt:run`
           
      *  In opened window press the “Launch Default Browser” button to launch our application using your default browser. Or, you can click “Copy to Clipboard” to copy the launch URL and paste it into the browser of your choice.

4.  Add dependencies to pom.xml

   ```xml
   <dependency>
     <groupId>com.vaadin.polymer</groupId>
     <artifactId>gwt-polymer-elements</artifactId>
     <version>${gwtPolymerVersion}</version>
     <scope>provided</scope>
   </dependency>
   <dependency>
     <groupId>com.googlecode.gwtquery</groupId>
     <artifactId>gwtquery</artifactId>
     <version>1.4.4-SNAPSHOT</version>
     <scope>provided</scope>
   </dependency>
   ```

   The gwt-polymer-elements contains set of widgets and the gwtquery provides useful utils (it is necessary until we wait for Java 8 support in GWT at least).
   
   **Note:** Replace the `${gwtPolymerVersion}` placeholder with actual version or add correspondent property in pom.xml

5.  Update gwt-maven-plugin configuration
    *  Add line `<jsInteropMode>JS</jsInteropMode>`
    
6.  Update gwt module xml file
    *  Add line `<inherits name="com.vaadin.polymer.Elements"/>`
    *  Add line `<inherits name="com.google.gwt.query.Query"/>`

7.  Update TodoList.html

    *  Change title
   ```html
   <title>Todo List</title>
   ```
    *  Add WebComponents polyfill script
   ```html
   <script src="todolist/bower_components/webcomponentsjs/webcomponents.js"></script>
   ```
    *  Remove body tag content

   Your TodoList.html should look like following:
   
   ```html
   <!doctype html>
   <!-- The DOCTYPE declaration above will set the     -->
   <!-- browser's rendering engine into                -->
   <!-- "Standards Mode". Replacing this declaration   -->
   <!-- with a "Quirks Mode" doctype is not supported. -->
   
   <html>
     <head>
       <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   
       <!--                                                               -->
       <!-- Consider inlining CSS to reduce the number of requested files -->
       <!--                                                               -->
       <link type="text/css" rel="stylesheet" href="TodoList.css">
   
       <!--                                           -->
       <!-- Any title is fine                         -->
       <!--                                           -->
       <title>Todo List</title>
   
       <!--                                           -->
       <!-- WebComponents polyfill which is necessary -->
       <!-- if you are going to use any browser       -->
       <!-- besides Chrome                            -->
       <!--                                           -->
       <script src="todolist/bower_components/webcomponentsjs/webcomponents.js"></script>
       
       <!--                                           -->
       <!-- This script loads your compiled module.   -->
       <!-- If you add any GWT meta tags, they must   -->
       <!-- be added before this line.                -->
       <!--                                           -->
       <script type="text/javascript" language="javascript" src="todolist/todolist.nocache.js"></script>
     </head>
   
     <!--                                           -->
     <!-- The body can have arbitrary html, or      -->
     <!-- you can leave the body empty if you want  -->
     <!-- to create a completely dynamic UI.        -->
     <!--                                           -->
     <body>
     </body>
   </html>
   ```

8.  Change WEB-INF/web-xml

    *  Remove greetServlet and it's mapping.


9.  Remove unnecessary files
    *  Remove folders `server` and `shared`
    *  Remove `GreetingService.java` and `GreetingServiceAsync.java`


10.  Update entry point

   Replace content of TodoList.java with

   ```java
   package com.example.client;
   
   import com.google.gwt.core.client.EntryPoint;
   import com.google.gwt.user.client.ui.RootPanel;
   import com.vaadin.polymer.paper.widget.PaperButton;
   
   public class TodoList implements EntryPoint {
   
       public void onModuleLoad() {
           PaperButton button = new PaperButton("Press me!");
           button.setRaised(true);
           RootPanel.get().add(button);
       }
   }
   ```
   Take attention that we use the PaperButton widget here.

11.  Run the application again
   
   You should see a page with single button in your browser.

   **Tip:** If you get ClassCastException in browser console, ensure that you use `-XjsInteropMode JS` parameter
