package CalcSheet.Gui.JXGraph;

/**
 * Created by Kacper on 2015-05-24.
 */
public class GraphPoints<E,F,G>{
    public E column;
    public F row;
    public G value;

    public void setColumn(E col){ this.column = col;}
    public void setRow(F row){this.row = row;}
    public void setValue(G value){ this.value = value;}

    public E getColumn(){return column;}
    public F getRow(){return row;}
    public G getValue(){return value;}

    public void setAll(E col,F row,G value){
        this.column = col;
        this.row    = row;
        this.value  = value;
    }

    public void PrintAll(){ System.out.println("COL:"+column+" ROW:"+row+" value:"+value);}

}
