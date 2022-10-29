import java.awt.event.ActionListener;

public interface BoardStrategy {
    void createMancala();
    void createLabels();
    void createRowAPits();
    void createRowBPits();
    void insertButton();
    void createLabelsForFirstPlayer();
    void addPitActionListener(ActionListener actionListener);
    void addUndoActionListener(ActionListener actionListener);

}
