package Gui.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Kacper
 */
public class GraphFrame extends JFrame {

    public GraphFrame() {
        super("Wykres v 1.0");
        initComponents();
    }
    private void initComponents() {

        graphComponent = new GraphComponent();
        ustawWartosc = new JButton();
        ustawKolor = new JButton();
        comboColor = new JComboBox();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        GroupLayout anotherProject1Layout = new GroupLayout(graphComponent);
        graphComponent.setLayout(anotherProject1Layout);
        anotherProject1Layout.setHorizontalGroup(
                anotherProject1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 800, Short.MAX_VALUE)
        );
        anotherProject1Layout.setVerticalGroup(
                anotherProject1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );

        ustawWartosc.setText("Ustaw zmienne");
        ustawWartosc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ustawWartoscActionPerformed(evt);
            }
        });

        ustawKolor.setText("Ustaw Kolor");
        ustawKolor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ustawKolorActionPerformed(evt);
            }
        });

        comboColor.setModel(new DefaultComboBoxModel(new String[]{"Kolor lini", "Kolor kratki", "Kolor punktu", "Kolor tła", "Kolor tła wykresu", "Kolor lini wykresu"}));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(graphComponent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(56, 56, 56)
                                                .addComponent(ustawWartosc)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ustawKolor, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(comboColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(168, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(51, Short.MAX_VALUE)
                                .addComponent(graphComponent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(ustawWartosc)
                                        .addComponent(ustawKolor)
                                        .addComponent(comboColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13))
        );

        pack();
    }
    private void ustawKolorActionPerformed(ActionEvent evt) {//GEN-FIRST:event_ustawKolorActionPerformed

        JColorChooser jcc = new JColorChooser();
        Color txtColor = JColorChooser.showDialog(jcc.getParent(),"Wybierz kolor", Color.black);

        if(txtColor!=null)
        {
            switch(comboColor.getSelectedIndex()){

                case 0:
                    graphComponent.setLineColor(txtColor);
                    break;
                case 1:
                    graphComponent.setGridColor(txtColor);
                    break;
                case 2:
                    graphComponent.setPointColor(txtColor);
                    break;
                case 3:
                    graphComponent.setBackground(txtColor);
                    break;
                case 4:
                    graphComponent.setGraphColor(txtColor);
                    break;
                case 5:
                    graphComponent.setGraphXYColor(txtColor);
                    break;
            }



        }

    }
    private void ustawWartoscActionPerformed(ActionEvent evt) {//GEN-FIRST:event_ustawWartoscActionPerformed
        graphComponent.setScores(createScores());

    }
    public List<Double> createScores(){
        List<Double> scores = new ArrayList<>();
        Random random = new Random();
        int maxDataPoints = 40;
        int maxScore = 10;
        for (int i = 0; i < maxDataPoints; i++) {
            scores.add(random.nextDouble() * maxScore);
        }
        return scores;
    }

    private GraphComponent graphComponent;
    private JButton ustawWartosc;
    private JButton ustawKolor;
    private JComboBox comboColor;
}

