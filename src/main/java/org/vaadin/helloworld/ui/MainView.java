package org.vaadin.helloworld.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.vaadin.helloworld.backend.entity.Company;
import org.vaadin.helloworld.backend.entity.Contact;
import org.vaadin.helloworld.backend.service.ContactService;

@Route("") // Entry point in the
public class MainView extends VerticalLayout {

    Grid<Contact> grid = new Grid<>(Contact.class);
    /*passing in contact class here Vaadin knows what type of object to expect and
    it will automatically configure some of the columns for us */

    TextField filterText = new TextField(); //Creates a field for the TextField

    private ContactService contactService;

    public MainView(ContactService contactService) {
        this.contactService = contactService; //Saves ContactService in a field, so you have easy access to it later.

        addClassName("list-view"); //class name is a CSS class name that we can use for CSS styling later on

        setSizeFull();  //make MainView take up the full size of the browser window.

        configureFilter();  //Calls the configureFilter() method to configure what the filter should do
        configureGrid(); //Splits the grid configuration into a separate method.
        // We will add more components to the class later on and this helps to keep things easy to understand.

       //configureFilter(); //configure what the filter should do.

        add(filterText, grid); //Adds the grid to the main layout
        updateList(); // fetches all contacts from the service, and passes them to the grid.
    }

    private void configureFilter() {
        filterText.setPlaceholder("Filter by name..."); //text string that shows in the filter when it's empty

        // Sets the clear button to visible so users can easily clear the filter.
        filterText.setClearButtonVisible(true);

        //value change mode to LAZY so the text field will notify you of changes automatically after a short timeout in typing.
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        //Calls the updateList method whenever the value changes.
        filterText.addValueChangeListener(valueCange -> updateList());

    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("company");

        //Defines the properties of a Contact to shown using the grid.setColumns(..) method.
        grid.setColumns("firstName", "lastName", "email", "status");

        grid.addColumn(contact -> getCompanyName(contact)).setHeader("Company"); //setHeader defines the column header for the custom column.

        //Automatic width needs to be turned on for each column separately. The easiest way to do it is to call grid.getColumns() and then use forEach to loop over all of them.
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() {
        grid.setItems(contactService.findAll(filterText.getValue()));
    }

    //Returns how it should be shown in the grid.  We show the company name, or a dash if it’s empty.
    public static String getCompanyName(Contact contact) {
        Company company = contact.getCompany();
        return company == null ? "--" : company.getName();
    }

}
