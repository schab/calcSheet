
package Gui.Graph;
import SheetComponent.SpreadSheet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphComponent extends JPanel {

    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);


    private int width;
    private int height;
    private int padding;
    private int labelPadding;
    private Color lineColor;
    private Color pointColor;
    private Color gridColor;
    private Color graphColor;
    private Color graphXYColor;
    private int pointWidth;
    private int numberYDivisions;
    private List<Double> scores;
    private String propertyName;


    public GraphComponent() {
        propertyName = "Graph";
        width = 800;
        height = 400;
        padding = 25;
        labelPadding = 25;
        lineColor = new Color(44, 102, 230, 180);
        pointColor = new Color(100, 100, 100, 180);
        gridColor = new Color(200, 200, 200, 200);
        graphColor = Color.WHITE;
        graphXYColor = Color.BLACK;
        this.setPreferredSize(new Dimension(800,400));
        pointWidth = 4;
        numberYDivisions = 10;
        scores = new ArrayList<>();
    }


    public void setPropertyName(String str){
        String oldpropertyName = this.propertyName;
        this.propertyName = propertyName;
        this.firePropertyChange("Graph", oldpropertyName, propertyName);
        repaint();
    }
    public void setGraphWidth(int width){
        int oldWidth = this.width;
        this.width = width;
        this.firePropertyChange("Graph width", oldWidth,width);
        repaint();
    }
    public void setGraphHeight(int height){
        int oldHeight = this.height;
        this.height = height;
        this.firePropertyChange("Graph width", oldHeight,height);
        repaint();
    }
    public void setPadding(int padding){
        int oldPadding = this.padding;
        this.padding = padding;
        this.firePropertyChange("Graph padding",oldPadding,padding);
        repaint();
    }
    public void setLabelPadding(int labelPadding){
        int oldLabelPadding = this.labelPadding;
        this.labelPadding = labelPadding;
        this.firePropertyChange("Label Padding", oldLabelPadding, labelPadding);
        repaint();
    }
    public void setLineColor(Color color){
        Color oldLineColor = this.lineColor;
        this.lineColor = color;
        this.firePropertyChange("Line Color", oldLineColor,lineColor);
        repaint();
    }
    public void setGridColor(Color color){
        Color oldGridColor = gridColor;
        this.gridColor = color;
        this.firePropertyChange("Grid Color", oldGridColor,gridColor);
        repaint();
    }
    public void setPointColor(Color color){
        Color oldPointColor = this.pointColor;
        this.pointColor = color;
        this.firePropertyChange("Point Color", oldPointColor,pointColor);
        repaint();
    }
    public void setGraphColor(Color color){
        Color oldPointColor = this.graphColor;
        this.graphColor = color;
        this.firePropertyChange("graphBackgroundColor", oldPointColor,pointColor);
        repaint();
    }
    public void setGraphXYColor(Color color){
        Color oldPointColor = this.graphXYColor;
        this.graphXYColor = color;
        this.firePropertyChange("graphXYColor", oldPointColor,graphXYColor);
        repaint();
    }
    public void setPointWidth(int width){
        int oldPointWidth = this.pointWidth;
        this.pointWidth = width;
        this.firePropertyChange("Point width",oldPointWidth, pointWidth);
        repaint();
    }
    public void setNumberYDivisions(int numberYDivisions){
        int oldNumberYDivisions = this.numberYDivisions;
        this.numberYDivisions = numberYDivisions;
        this.firePropertyChange("Number Y divisions",oldNumberYDivisions,numberYDivisions);
        repaint();

    }
    public void setScores ( List<Double> list){
        List<Double> oldScores = scores;
        this.scores = list;
        this.firePropertyChange("Scores", oldScores, scores);
        repaint();
    }


    public void setScoresTable ( SpreadSheet spr, int col, int row){
        List<Double> oldScores = scores;
        List<Double> newScores = null;

        for(int i = 0; i < col; i ++)
            for(int j = 0 ; j < row ; j ++)
                    newScores.add((Double) spr.getModel().getValueAt(j,i));

    }

    public String getPropertyName(){ return this.propertyName;}
    public int    getGraphWidth(){   return this.width;}
    public int    getGraphHeight(){  return this.height;}
    public int    getPadding(){      return this.padding;}
    public int    getLabelPadding(){ return this.labelPadding;}
    public Color  getLineColor(){    return this.lineColor; }
    public Color  getGridColor(){    return this.gridColor;}
    public Color  getPointColor(){   return this.pointColor;}
    public Color  getGraphColor(){   return this.graphColor;}
    public Color  getGraphXYColor(){ return this.graphXYColor;}
    public int    getPointWidth(){   return this.pointWidth;}
    public int    getNumberYDivisiosn(){ return this.numberYDivisions;}
    public List<Double> getScores(){ return this.scores; }


    @Override
    protected void paintComponent(Graphics g) {
        if(scores!=null){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1);
            double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

            List<Point> graphPoints = new ArrayList<>();
            for (int i = 0; i < scores.size(); i++) {
                int x1 = (int) (i * xScale + padding + labelPadding);
                int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
                graphPoints.add(new Point(x1, y1));
            }

            // draw white background
            g2.setColor(graphColor);
            g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
            g2.setColor(graphXYColor);

            // create hatch marks and grid lines for y axis.
            for (int i = 0; i < numberYDivisions + 1; i++) {
                int x0 = padding + labelPadding;
                int x1 = pointWidth + padding + labelPadding;
                int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
                int y1 = y0;
                if (scores.size() > 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                    g2.setColor(graphXYColor);
                    String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(yLabel);
                    g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }

            // and for x axis
            for (int i = 0; i < scores.size(); i++) {
                if (scores.size() > 1) {
                    int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
                    int x1 = x0;
                    int y0 = getHeight() - padding - labelPadding;
                    int y1 = y0 - pointWidth;
                    if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
                        g2.setColor(gridColor);
                        g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                        g2.setColor(graphXYColor);
                        String xLabel = i + "";
                        FontMetrics metrics = g2.getFontMetrics();
                        int labelWidth = metrics.stringWidth(xLabel);
                        g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                    }
                    g2.drawLine(x0, y0, x1, y1);
                }
            }

            // create x and y axes
            g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
            g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

            Stroke oldStroke = g2.getStroke();
            g2.setColor(lineColor);
            g2.setStroke(GRAPH_STROKE);
            for (int i = 0; i < graphPoints.size() - 1; i++) {
                int x1 = graphPoints.get(i).x;
                int y1 = graphPoints.get(i).y;
                int x2 = graphPoints.get(i + 1).x;
                int y2 = graphPoints.get(i + 1).y;
                g2.drawLine(x1, y1, x2, y2);
            }

            g2.setStroke(oldStroke);
            g2.setColor(pointColor);
            for (int i = 0; i < graphPoints.size(); i++) {
                int x = graphPoints.get(i).x - pointWidth / 2;
                int y = graphPoints.get(i).y - pointWidth / 2;
                int ovalW = pointWidth;
                int ovalH = pointWidth;
                g2.fillOval(x, y, ovalW, ovalH);
            }

        }
    }

    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }
}