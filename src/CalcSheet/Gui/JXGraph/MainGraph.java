package CalcSheet.Gui.JXGraph;

/**
 * Created by Kacper on 2015-05-24.
 */


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javax.swing.*;
import java.util.*;


public class MainGraph{

    private static List<GraphPoints<Integer,Integer,Double>> graphPoints;
    private static int maxColumns,maxRows,index;
    private static String titleGraph,colNameGraph;

    public static void initAndShowGUI(
            List<GraphPoints<Integer,Integer,Double>> graphPointsList ,
            int columns,
            int rows,
            String title,
            String colName,
            int ind)
    {
        final JFrame frame = new JFrame("Wykres ver. 1.0");
        final JFXPanel jfxPanel = new JFXPanel();
        graphPoints = graphPointsList;
        maxColumns = columns;
        maxRows = rows;
        titleGraph = title;
        colNameGraph = colName;
        index = ind;

        frame.add(jfxPanel);
        frame.setVisible(true);
        frame.setSize(800, 600);
        Platform.runLater(()-> {initFX(jfxPanel);});
    }

    public static LineChart createChart(){
            final NumberAxis xAxis = new NumberAxis();
            final NumberAxis yAxis = new NumberAxis();

            final LineChart<Number, Number> lineChart =
                    new LineChart<Number, Number>(xAxis, yAxis);

            xAxis.setLabel(colNameGraph + ":1");
            yAxis.setLabel("Wartości komórek");
            lineChart.setTitle(titleGraph);

            for(XYChart.Series ser : GetSeries() )
                lineChart.getData().add(ser);

            return lineChart;
        }
    public static XYChart.Series[] GetSeries(){
            double[][] dx = new double[maxColumns][maxRows];
            for(GraphPoints d: graphPoints)
                dx[(int)d.getColumn()][(int)d.getRow()] = new Double(d.getValue().toString());

            XYChart.Series[] series = new XYChart.Series[maxColumns-1];
            for(int i = 1 ; i < dx.length ; i++){
                String title = colNameGraph + ":" + (i+1);
                series[i-1] = new XYChart.Series();
                series[i-1].setName(title);
                for(int j = 0 ; j < dx[i].length;j++){
                    double x,y;
                    if(index == 0) {
                        x = dx[0][j];
                        y = dx[i][j];
                    }
                    else{
                        x = dx[i][j];
                        y = dx[0][j];
                    }

                    series[i - 1].getData().add(new XYChart.Data(x, y));

                }
            }

            return series;
        }
    private static Scene createScene() {
        Scene  scene  =  new  Scene(createChart(),800,600);
        return (scene);
    }
    private static void initFX(JFXPanel jfxPanel){
        Scene scene = createScene();
        jfxPanel.setScene(scene);
    }

}
