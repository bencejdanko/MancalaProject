import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class AbstractStrategy implements BoardStrategy {
    private JFrame frame;
    final Container container;
    final JPanel panelMancala;

    private ArrayList<JButton> firstJButtons = new ArrayList<>();
    private ArrayList<JButton> secondJButtons = new ArrayList<>();
    private ArrayList<JButton> mancala = new ArrayList<>();
    private ArrayList<JLabel> playerLabel = new ArrayList<>();
    private ArrayList<JLabel> pitLabel = new ArrayList<>();
    private ArrayList<JButton> allButtons = new ArrayList<>();
    private JButton undoButton;
    private JButton quitButton;
    private JLabel firstPlayerTurn, SecondPlayerTurn;

    public AbstractStrategy(){
        frame = new JFrame("Mancala Game");
        frame.set
    }

}
