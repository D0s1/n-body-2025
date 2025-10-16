package org.simulation;

import org.simulation.PointOfMass;

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
        repaint(); // Swing sorgt dafür, dass paintComponent aufgerufen wird
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // löscht den Hintergrund
        g.setColor(Color.BLUE);

        if (pointsOfMass != null) {
            pointsOfMass.forEach(point ->
                    g.fillOval((int) point.getCoordinate().getX(),
                            (int) point.getCoordinate().getY(),
                            (int) point.getSize(),
                            (int) point.getSize()));
        }
    }
}