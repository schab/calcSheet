package CalcSheet.Gui;

import javax.swing.*;

/**
 * Created by Kacper on 2015-04-08.
 */
public class MultiOptionPane {

    private int columns,rows;
    private final static String col = "10px,100px,5px,50px,20px,100px,5px,50px,10px";
    private final static String row = "15px,20px,10px,20px,10px,20px,15px";

    public MultiOptionPane(){
        this.initializeComponents();
    }

    public void showDialog(){
        columns = rows = 0 ;
        jtfRow.setText("");
        jtfCol.setText("");
        int value = JOptionPane.showConfirmDialog(new JFrame(),formPanel,"Enter text", JOptionPane.OK_CANCEL_OPTION);
        if (value == JOptionPane.OK_OPTION)
        {
            try {
                columns = Integer.parseInt(jtfCol.getText());
                rows = Integer.parseInt(jtfRow.getText());

                if(columns <= 0 || rows <= 0) {
                    showErrorPane("Wprowadzono błędną wartość, mniejszą lub równą zero, utworzono arkusz 1x1 !", "Błąd!");
                    columns = 1;
                    rows = 1;
                }
                else if ((columns >1000 && rows > 1000) || columns >= 10000 || rows >= 10000 ){
                   showErrorPane("Wprowadzona wartość jest zbyt duża, ustalono arkusz 1000x1000!","Błąd");
                   columns = 1000;
                   rows = 1000;
                }

            }catch(NumberFormatException e){
                    showErrorPane("Jedna lub dwie wartości są błędnie wprowadzone !","Błąd");
            }


        }
    }

    public void showErrorPane(String str,String title){
        Object[] options = {"OK"};
        JOptionPane.showOptionDialog(new JFrame(),
                str,
                title,
                JOptionPane.OK_OPTION,JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);
    }


    private void initializeComponents(){
        formPanel = new FormPanel(col,row);
        lIntro    = new JLabel("Wprowadz odpowiednie wartosci.");
        lCol      = new JLabel("Ilosc kolumn :");
        lRow      = new JLabel("Ilosc wierszy:");

        jtfCol    = new JTextField(10);
        jtfRow    = new JTextField(10);


        formPanel.addXYW(lIntro,2,2,5);
        formPanel.addXY(lCol, 2, 4);
        formPanel.addXY(lRow, 6, 4);

        formPanel.addXY(jtfCol, 4, 4);
        formPanel.addXY(jtfRow,8,4);
    }


    private FormPanel formPanel;
    private JTextField jtfCol,jtfRow;
    private JLabel lCol,lRow,lIntro;

    public int getColumns(){ return this.columns;}
    public int getRows(){   return this.rows;}

}
