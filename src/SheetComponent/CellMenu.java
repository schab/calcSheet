package SheetComponent;

import Gui.FontChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

  private Object      _targetCells[];
  private JWindow     _colorWindow;
  private SpreadSheet _sp;
  private FontChooser jFontChooser;
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

    pack();
  }

  public void setTargetCells( Object c[]) { _targetCells = c; }

  public void actionPerformed(ActionEvent ev) {

    if (DEBUG) System.out.println("Size of selection: "+_targetCells.length);

    if (ev.getActionCommand().equals(_FOREGROUND)) {
      setVisible(false);
      if (_colorWindow==null) new JWindow();
      Color col = JColorChooser.showDialog(_colorWindow,"Kolor czcionki",null);
      for (int ii=0; ii<_targetCells.length; ii++) {
	SheetCell sc = (SheetCell)_targetCells[ii];
	sc.foreground = col;
      }
      _sp.repaint();
    } else if (ev.getActionCommand().equals(_BACKGROUND)) {
      setVisible(false);
      if (_colorWindow==null) new JWindow();
      Color col = JColorChooser.showDialog(_colorWindow,"Kolor tła",null);
      for (int ii=0; ii<_targetCells.length; ii++) {
	SheetCell sc = (SheetCell)_targetCells[ii];
	sc.background = col;
      }
      _sp.repaint();
    } else if (ev.getActionCommand().equals(_FONT)) {
      setVisible(false);
      if (_colorWindow==null) new JWindow();{
        jFontChooser = new FontChooser();
          int result = jFontChooser.showDialog(jFontChooser.getParent());
          if(result == FontChooser.OK_OPTION)
          {
            Font font = jFontChooser.getSelectedFont();
            for (int ii=0; ii<_targetCells.length; ii++) {
              SheetCell sc = (SheetCell)_targetCells[ii];
              sc.font = jFontChooser.getSelectedFont();
            }
            _sp.repaint();
          }
      }
    }




  }


  
}
