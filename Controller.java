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

        this.view.addPitActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String pitName = ((JButton) e.getSource()).getName(); //pitName A5, B1, etc

                //need to create an if statement to check if game is over
                if (model.checkGameOver()) {
                    if (model.getStonesInP1Mancala() > model.getStonesInP2Mancala())
                        JOptionPane.showMessageDialog(view.getFrame(), "First Player Wins.");
                    else if (model.getStonesInP2Mancala() > model.getStonesInP1Mancala())
                        JOptionPane.showMessageDialog(view.getFrame(), "Second Player Wins.");
                    else
                        JOptionPane.showMessageDialog(view.getFrame(), "Match Tie");
                } else if (!model.checkIfValid(pitName)) {
                    JOptionPane.showMessageDialog(view.getFrame(), "Incorrect Move Please Choose another option.");
                } else {
                    model.pickPitNumber(pitName);
                    model.checkWinner();

                    for (int i = 0; i < view.getAllButtons().size(); i++) {
                        view.getAllButtons().get(i).setText(setStones(model.getStonesInPit(i)));
                    }

                    view.getMancala().get(0).setText(setStones(model.getStonesInP2Mancala())); //updating mancala1
                    view.getMancala().get(1).setText(setStones(model.getStonesInP1Mancala()));
                    if (model.getCounter() % 2 == 0) {
                        view.secondPlayerOption(false);
                        view.firstPlayerOption(true);
                    } else {
                        view.firstPlayerOption(false);
                        view.secondPlayerOption(true);
                    }
                    if (model.checkGameOver()) {
                        if (model.getStonesInP1Mancala() > model.getStonesInP2Mancala())
                            JOptionPane.showMessageDialog(view.getFrame(), "Game Over! First Player Wins");
                        else if (model.getStonesInP2Mancala() > model.getStonesInP1Mancala())
                            JOptionPane.showMessageDialog(view.getFrame(), "Game Over! Second Player Wins");
                        else
                            JOptionPane.showMessageDialog(view.getFrame(), "Match Tie");
                    }
                }
            }
        });


    }
}
  //