package CalcSheet.SheetComponent;

import javax.swing.table.AbstractTableModel;

/**
 * This class specifies the data format
 * for the SheetComponent.SheetComponent JTable
 *
 *
 * @author  Hubert Swiecioch
 */
public class SpreadSheetModel extends AbstractTableModel {
    
    final private SpreadSheet _dpyTable;

    private Interpreter interpreter;

    static final boolean DEBUG = false;

    private int        _nbRow;
    private int        _nbColumn;

    protected SheetCell[][] cells;

    /**
     * Create a nbRow by nbColumn SheetComponent.SpreadSheetModel.
     *
     * @param cells[][] The cell array
     * @param table     The associated SheetComponent.SheetComponent
     */
    SpreadSheetModel(SheetCell[][] cells, SpreadSheet table) {
	_dpyTable   = table;
	_nbRow      = cells.length;
	_nbColumn   = cells[0].length;
	this.cells  = cells;

        interpreter = Interpreter.getInstance(this);

        for(SheetCell[] c : cells)
            for(SheetCell e : c)
                e.setInterpreter(interpreter);

    }
	
    private void clean() {
	_nbRow  = _nbColumn = 0;
	cells = null;
    }

    public int     getRowCount()    {return _nbRow;}
    public int     getColumnCount() {return _nbColumn;}

    public boolean isCellEditable(int row, int col) { return true; }

    public Object  getValueAt(int row, int column) { return cells[row][column]; }
    /**
     * Mark the corresponding cell
     * as being edited. Its formula
     * must be displayed instead of 
     * its result
     */
    void setEditMode(int row, int column)    { cells[row][column].state=SheetCell.EDITED; }

    void setDisplayMode(int row, int column) { cells[row][column].state=SheetCell.UPDATED; }


    public void setValueAt(Object value, int row, int column) {

	String input = (String)value;

	cells[row][column].formula = input;
	cells[row][column].userUpdate();
	_dpyTable.repaint();
	
    }
   
}

 
