public class Controller {
    private int counter;
    private Model model;
    private BoardStrategy view;


    public Controller(final Model model, final AbstractStrategy view){
        this.model = model;
        this.view = view;
        counter = 0;
        view.setRowAPits(model.getPitStones(0));
        view.setRowBPits(mode.get);
    }
}
  //