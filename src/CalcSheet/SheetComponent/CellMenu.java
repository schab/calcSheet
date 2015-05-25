package CalcSheet.SheetComponent;

import CalcSheet.Gui.FontChooser;
import CalcSheet.Gui.JXGraph.AxisChoose;
import CalcSheet.Gui.JXGraph.GraphPoints;
import CalcSheet.Gui.LogPanel;
import CalcSheet.Gui.MultiOptionPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a popup-menu
 * used to customize cell appearance.
 *
 *
 * @author  Hubert Swiecioch
 */
public class CellMenu extends JPopupMenu implements ActionListener {

  /**
   * Set this field to true and recompile
   * to get debug traces
   */
  public static final boolean DEBUG = false;

  static private final String _FOREGROUND = "Kolor czcionki";
  static private final String _BACKGROUND = "Kolor tła";
  static private final String _FONT       = "Czcionka";
  static private final String _GRAPH      = "Wygeneruj graf";

  private Object      _targetCells[];
  private JWindow     _colorWindow;
  private SpreadSheet _sp;
  private FontChooser jFontChooser;
  private MultiOptionPane multiOptionPane;

  CellMenu(SpreadSheet parent) {
    
    _sp = parent;


//    setDefaultLightWeightPopupEnabled(true);

    JMenuItem item = new JMenuItem(_FOREGROUND);
    item.addActionListener(this);
    add(item);
    item = new JMenuItem(_BACKGROUND);
    item.addActionListener(this);
    add(item);
    item = new JMenuItem(_FONT);
    item.addActionListener(this);
    add(item);

    item = new JMenuItem(_GRAPH);
    item.addActionListener(this);
    add(item);


    pack();
  }

  Object[] _transqCells;
  public void setTargetCells( Object c[]) { _targetCells = c; }
  public void setTransqCells(Object c[]){ _transqCells = c;}


  public void actionPerformed(ActionEvent ev) {

    if (DEBUG) System.out.println("Size of selection: " + _targetCells.length);

    if (ev.getActionCommand().equals(_FOREGROUND)) {
      setVisible(false);
      if (_colorWindow == null) new JWindow();
      Color col = JColorChooser.showDialog(_colorWindow, "Kolor czcionki", null);
      for (int ii = 0; ii < _targetCells.length; ii++) {
        SheetCell sc = (SheetCell) _targetCells[ii];
        sc.foreground = col;
      }
      LogPanel.setInfo(getClass(),"Kolor czcionki wybranych komórek został zmieniony");
      _sp.repaint();
    } else if (ev.getActionCommand().equals(_BACKGROUND)) {
      setVisible(false);
      if (_colorWindow == null) new JWindow();
      Color col = JColorChooser.showDialog(_colorWindow, "Kolor tła", null);
      for (int ii = 0; ii < _targetCells.length; ii++) {
        SheetCell sc = (SheetCell) _targetCells[ii];
        sc.background = col;
      }
      LogPanel.setInfo(getClass(),"Kolor tła wybranych komórek został zmieniony");

      _sp.repaint();
    } else if (ev.getActionCommand().equals(_FONT)) {
      setVisible(false);
      if (_colorWindow == null) new JWindow();
      {
        jFontChooser = new FontChooser();
        int result = jFontChooser.showDialog(jFontChooser.getParent());
        if (result == FontChooser.OK_OPTION) {
          Font font = jFontChooser.getSelectedFont();
          for (int ii = 0; ii < _targetCells.length; ii++) {
            SheetCell sc = (SheetCell) _targetCells[ii];
            sc.setFont(jFontChooser.getSelectedFont());
          }
          _sp.repaint();
          LogPanel.setInfo(getClass(),"Czcionka wybranych komórek została zmieniona");
        }
      }
    } else if (ev.getActionCommand().equals(_GRAPH)) {
      setVisible(false);


      try {
        GraphPoints<Integer, Integer, Double> graphPoints;
        List<GraphPoints<Integer, Integer, Double>> points = new ArrayList<>();

        int colMax = 0, rowMax = 0;
        int columnM = 0;
        int col = 0, row = 0;
        int rowM = 0;
        for (int ii = 0; ii < _targetCells.length; ii++) {

          SheetCell sc = (SheetCell) _targetCells[ii];

          if (ii == 0) {
            colMax = sc.column;
            rowMax = sc.row;
          }

          col = sc.column - colMax;
          row = sc.row - rowMax;

          columnM = (columnM < col) ? col : columnM;
          rowM = (rowM < row) ? row : rowM;
          graphPoints = new GraphPoints<>();
          graphPoints.setAll(col, row, new Double(sc.value.toString()));
          points.add(graphPoints);
        }
        columnM += 1;
        rowM += 1;
        AxisChoose axisChoose = new AxisChoose(points,columnM,rowM);
        LogPanel.setInfo(getClass(), "Utworzenie grafu");

      }catch(NullPointerException e){
        multiOptionPane = new MultiOptionPane();
        multiOptionPane.showErrorPane("Wybrane pole jest puste !", "Błąd");
        LogPanel.setInfo(getClass(), "Wybrane pole jest puste !");

      }
    }
  }

}
