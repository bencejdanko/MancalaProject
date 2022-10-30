import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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

    public void firstPlayerOption(boolean setter){
        firstPlayerTurn.setVisible(setter);
    }
    public void secondPlayerOption(boolean setter){
        secondPlayerTurn.setVisible(setter);
    }

    private void CreateMancala() {
    }

    private void CreateLabels() {
    }

    private void CreateRowAPits() {
        int x = 200;
        for (int i = 0; i < 6; i++) {
            firstJButtons.add(new JButton());
            firstJButtons.get(i).setBounds(x, 260, 100, 40);
            x += 100;
            panelMancala.add(firstJButtons.get(i));
            String bNumber = "A" + (i + 1);
            firstJButtons.get(i).setName(bNumber);
        }
    }

    public void setRowAPits(int stoneQuantity){
        for (int i = 0; i < 6; i++)
        {
            int totalStones = (stoneQuantity);
            String buttonNumber = setStones(totalStones);
            firstJButtons.get(i).setText(buttonNumber);
        }
    }

    private void CreateRowBPits() {
            int value = 700;
            for (int i = 0; i < 6; i++) {
                secondJButtons.add(new JButton());
                secondJButtons.get(i).setBounds(value, 220, 100, 40);
                value = value- 100;
                String buttonNumber = "B" + (i + 1);
                secondJButtons.get(i).setName(buttonNumber);
                //BJButtons.get(i).setText(buttonNumber);
                panelMancala.add(secondJButtons.get(i));
            }
        }

        public void setRowBPits(int stoneQuantity){
            for (int i = 0; i < 6; i++)
            {
                int totalStones= (stoneQuantity);
                String buttonNumber = setStones(totalStones);
                secondJButtons.get(i).setText(buttonNumber);
            }
        }

    private void insertButtons() {
        for (int i = 0; i < firstJButtons.size(); i++) {
            allButtons.add(firstJButtons.get(i));
        }
        for (int i = 0; i < secondJButtons.size(); i++) {
            allButtons.add(secondJButtons.get(i));
        }
    }

    private void CreateLabelsForPlayerA() {
        for (int i = 0; i < 6; i++) {
            final JLabel aLabel = new JLabel("A" + (i + 1));
            aLabel.setForeground(Color.red);
            pitLabel.add(aLabel);

            int x = firstJButtons.get(i).getX() + firstJButtons.get(i).getWidth() / 2;
            int y= firstJButtons.get(i).getY() + firstJButtons.get(i).getHeight();
            aLabel.setBounds(x, y, firstJButtons.get(i).getWidth(), firstJButtons.get(i).getHeight() / 2);
        }

        // Creates labels for Player B's pits
        for (int i = 0; i < 6; i++) {
            final JLabel bLabel = new JLabel("B" + (i + 1));
            pitLabel.add(bLabel);
            bLabel.setForeground(Color.blue);
            int xCoord = secondJButtons.get(i).getX() + secondJButtons.get(i).getWidth() / 2;
            int yCoord = secondJButtons.get(i).getY() - secondJButtons.get(i).getHeight() / 2;
            bLabel.setBounds(xCoord, yCoord, secondJButtons.get(i).getWidth(), secondJButtons.get(i).getHeight() / 2);
        }
        for (JLabel exactLbl : pitLabel) {
            panelMancala.add(exactLbl);
        }


        panelMancala.setBounds(0, 0, 1200, 600);
        panelMancala.setBorder(BorderFactory.createTitledBorder("Mancala"));
        container.add(panelMancala);
        frame.setResizable(true);
        frame.setVisible(true);
    }
    public void addPitActionListener(ActionListener myListener){
        for(JButton b1: mancala){
            b1.addActionListener(myListener);
        }
    }
    public void addMancalaActionListener(ActionListener myListener){
        for(JButton b2: mancala ){
            b2.addActionListener(myListener);
        }
    }


    public void addQuitActionListener(ActionListener myListener)
    {
        quitButton.addActionListener(myListener);
    }

    public void addUndoActionListener(ActionListener myListener)
    {
        undoButton.addActionListener(myListener);
    }

    public ArrayList<JButton> getMancala()
    {
        return mancala;
    }
    public ArrayList<JLabel> getPlayerLabel()
    {
        return playerLabel;
    }
    public ArrayList<JButton> getTotalButtons(){
        return allButtons;
    }

    public String setStones(int value){
        String numberOfStones = "";
        for(int i =0; i < value; i++){
            numberOfStones += "*";
        }
        return numberOfStones;
    }
    private void addPlayerTurns() {
        firstPlayerTurn.setForeground(Color.red);
        secondPlayerTurn.setForeground(Color.blue);
        firstPlayerTurn.setBounds(450, 300, 100, 100);
        secondPlayerTurn.setBounds(450, 100, 100, 100);
        panelMancala.add(firstPlayerTurn);
        panelMancala.add(secondPlayerTurn);
        secondPlayerTurn.setVisible(false);
    }

    public JFrame getFrame(){
        return frame;
    }

}
