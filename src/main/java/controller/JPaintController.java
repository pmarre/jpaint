package controller;

import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import view.EventName;
import view.interfaces.IEventCallback;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.UNDO, () -> UndoHandler());
        uiModule.addEvent(EventName.REDO, () -> RedoHandler());
    }
    public void UndoHandler() {
        ICommand undo = new UndoCommand();
        undo.execute();

    }

    public void RedoHandler() {
        ICommand redo = new RedoCommand();
        redo.execute();

    }

}


