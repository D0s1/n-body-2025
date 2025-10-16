package org.simulation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphicInterface extends JPanel {

    private List<PointOfMass> pointsOfMass;

    public GraphicInterface() {
        JFrame frame = new JFrame("NBody Simulation");
        this.setPreferredSize(new Dimension(1000, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void setPoints(List<PointOfMass> points) {
        this.pointsOfMass = points;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        if (pointsOfMass != null) {
            pointsOfMass.forEach(point ->
                    g.fillOval((int) point.getCoordinate().getX(),
                            (int) point.getCoordinate().getY(),
                            (int) point.getSize()*2,
                            (int) point.getSize()*2));
        }
    }
}