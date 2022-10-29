import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class AbstractStrategy implements BoardStrategy {
    private JFrame frame;
   Container container ;
     JPanel panelMancala;

    private ArrayList<JButton> firstJButtons = new ArrayList<>();
    private ArrayList<JButton> secondJButtons = new ArrayList<>();
    private ArrayList<JButton> mancala = new ArrayList<>();
    private ArrayList<JLabel> playerLabel = new ArrayList<>();
    private ArrayList<JLabel> pitLabel = new ArrayList<>();
    private ArrayList<JButton> allButtons = new ArrayList<>();
    private JButton undoButton;
    private JButton quitButton;
    private JLabel firstPlayerTurn, secondPlayerTurn;

    public AbstractStrategy()
    {
        //prepare frame
        frame = new JFrame("Mancala Board");
        frame.setSize(1200, 600);
       container = frame.getContentPane();
        container.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelMancala = new JPanel(null);
        CreateMancala();
        CreateLabels();
        CreateRowAPits();
        CreateRowBPits();
        insertButtons();
        CreateLabelsForPlayerA();
        undoButton = new JButton("UNDO");
        quitButton = new JButton("QUIT");
        undoButton.setBounds(10, 500, 75, 75);
        quitButton.setBounds(1100, 500, 75, 75);
        panelMancala.add(undoButton);
        panelMancala.add(quitButton);
        firstPlayerTurn = new JLabel("Player A -->");
        secondPlayerTurn = new JLabel("<-- Player B ");
        addPlayerTurns();

    }

    private void CreateMancala() {
    }

    private void CreateLabels() {
    }

    private void CreateRowAPits() {
    }

    private void CreateRowBPits() {
    }

    private void insertButtons() {
    }

    private void CreateLabelsForPlayerA() {
    }

    private void addPlayerTurns() {
    }

}
