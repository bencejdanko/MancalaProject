public class Controller {
    Model model;
    View view;

    Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        view.addController(this);
    }

    public void removeAlert() {
        model.removeAlert();
    }

    public boolean detectAlert() {
        return (model.getAlert() != null);
    }

    public String getAlert() {
        return model.getAlert();
    }

    public void undo() {
        model.undo();
    }

    public void newGame() {
        model = new Model();
        view = new View(model);
    }

    public String getGameOverAlertCode()  {
        return Model.ALERT_GAME_OVER;
    }
}
