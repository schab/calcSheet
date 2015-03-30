import javax.swing.*;

/**
 * Created by Kacper on 2015-03-30.
 */
public class TabbedTable extends JTabbedPane{


    private SpreadSheet[] spreadSheets;
    private int spreadCount;

    public TabbedTable(){
        this.spreadCount = 3;
        spreadSheets = new SpreadSheet[spreadCount];

    }

    public void AddNewSheet(int col,int row){
        spreadCount +=1;
        SpreadSheet[] tempSheet = new SpreadSheet[spreadCount];
        for(int i = 0; i<tempSheet.length;i++)
            tempSheet[i] = (i == spreadCount - 2) ? new SpreadSheet(row, col) : spreadSheets[i];

        for(int i=0;i<tempSheet.length;i++)
            spreadSheets[i]=tempSheet[i];


    }

    public void ResizeSheet(int col,int row){
        spreadSheets[this.getSelectedIndex()]=new SpreadSheet(row,col);
    }


}
