package edu.ean.feriaempresarial.views;

import edu.ean.feriaempresarial.model.AppState;

/**
 * Represents a screen in the application.
 * Implementations of this interface define how a screen is displayed and updated.
 */
public interface IScreen {

    /**
     * Displays the screen.
     * Implementations should define the logic to present the screen to the user.
     */
    void show();

    /**
     * Updates the screen based on the current application state.
     *
     * @param appState The current state of the application.
     */
    void update(AppState appState);
}
