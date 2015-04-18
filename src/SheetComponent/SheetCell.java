package SheetComponent;

import Functions.Fonts;

import java.awt.*;
import java.util.*;

/**
 * This class specifies the
 * cell format.
 *
 * @version 1.0 July-2002
 * @author  Hubert Swiecioch
 */
class SheetCell {

  /**
   * Set this field to true and recompile
   * to get debug traces
   */
  public static final boolean DEBUG = false;

  
  static final int UNDEFINED = 0;
  static final int EDITED    = 1;
  static final int UPDATED   = 2;
  static final boolean USER_EDITION = true;
  static final boolean UPDATE_EVENT = false;
  
  Object value;
  String formula;
  int    state;
  Vector listeners;
  Vector listenees;
  Color  background;
  Color  foreground;
  Font font;
  int    row;
  int    column;

  private Interpreter interpreter;

  SheetCell(int r, int c){
    row        = r;
    column     = c;
    value      = null;
    formula    = null;
    state      = UNDEFINED;
    listeners  = new Vector();
    listenees  = new Vector();
    background = Color.white;
    foreground = Color.black;
    font       = Fonts.CalibriSmall.font();
  }


  SheetCell(int r, int c, String value, String formula) {
    this(r,c);
    this.value   = value;
    this.formula = formula;
  }

  void userUpdate() {

    // The user has edited the cell. The dependencies
    // on other cells may have changed:
    // clear the links to the listeners. They will be
    // resseted during interpretation
    for (int ii=0; ii<listenees.size(); ii++) {
	SheetCell c = (SheetCell)listenees.get(ii);
	c.listeners.remove(this);
    }
    listenees.clear();

    interpreter.interpret(this, USER_EDITION);
    updateListeners();
    state = UPDATED;
  }

  void updateListeners() {
    for (int ii=0; ii<listeners.size(); ii++) {
      SheetCell cell = (SheetCell)listeners.get(ii);
      interpreter.interpret(cell, UPDATE_EVENT);
      if (DEBUG) System.out.println("Listener updated.");
      cell.updateListeners();
    }
  }

  public void setInterpreter(Interpreter interpreter){
    this.interpreter = interpreter;
  }

  public String toString() {
      if (state==EDITED && formula!=null)
	return formula;
      else if (value!=null)
	return value.toString();
      else
	return null;
  }
  
}
