import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        this.view.addMancalaActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                String name = ((JButton) e.getSource()).getName();
                if(name.equals("firstPlayer")){
                    view.getMancala().get(0).setText(model.getStonesInP2Mancala()+ "");
                }
                else{
                    view.getMancala().get(1).setText(model.getStonesInP1Mancala() + "");
                }
            }
        });


    }
}
  //