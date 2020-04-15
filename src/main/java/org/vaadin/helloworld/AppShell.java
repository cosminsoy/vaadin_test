package org.vaadin.helloworld;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;

/**
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */
@PWA(name = "Hello World!", shortName = "Hello World!")
public class AppShell implements AppShellConfigurator {
}
