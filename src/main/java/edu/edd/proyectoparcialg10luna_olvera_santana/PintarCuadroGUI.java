/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.edd.proyectoparcialg10luna_olvera_santana;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 *
 * @author miguel
 */
public class PintarCuadroGUI extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PintarCuadroGUI().setVisible(true));
    }
    private Cuadro cuadro;
    private JPanel paintPanel;
    private JSpinner filaSpinner, colSpinner;
    private JComboBox<String> colorComboBox;
    private JButton paintButton;
    private final Map<String, Color> COLORS = new LinkedHashMap<>() {
        {
            put("Blanco", Color.WHITE);
            put("Rojo", Color.RED);
            put("Verde", Color.GREEN);
            put("Azul", Color.BLUE);
            put("Amarillo", Color.YELLOW);
            put("Naranja", Color.ORANGE);
            put("Magenta", Color.MAGENTA);
            put("Cyan", Color.CYAN);
            put("Rosado", Color.PINK);
            put("Morado", new Color(128, 0, 128));
        }
    };

    private List<Set<Cuadro.Punto>> clusteres;
    private int currentClusterIndex = 0;

    public PintarCuadroGUI() {
        super("Pintar Cuadro GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(800, 480);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        cuadro = new Cuadro();

        paintPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawCuadro(g);
            }
        };
        paintPanel.setPreferredSize(new Dimension(680, 480));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

//        JPanel formPanel = new JPanel();
//        formPanel.setBorder(Borderout.Y_AXIS));
//        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));Factory.createTitledBorder("Control Panel"));
        JPanel formPanel = createControlPanel();

        controlPanel.add(formPanel);
        add(paintPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);
    }

    private JPanel createControlPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Control Panel"));
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel filaLabel = new JLabel("Fila:");
        filaLabel.setPreferredSize(new Dimension(80, 25));
        formPanel.add(filaLabel, gbc);

        gbc.gridx = 1;
        filaSpinner = new JSpinner(new SpinnerNumberModel(0, 0, cuadro.getMatriz().length - 1, 1));
        filaSpinner.setPreferredSize(new Dimension(100, 25));
        formPanel.add(filaSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel colLabel = new JLabel("Columna:");
        colLabel.setPreferredSize(new Dimension(80, 25));
        formPanel.add(colLabel, gbc);

        gbc.gridx = 1;
        colSpinner = new JSpinner(new SpinnerNumberModel(0, 0, cuadro.getMatriz()[0].length - 1, 1));
        colSpinner.setPreferredSize(new Dimension(100, 25));
        formPanel.add(colSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel colorLabel = new JLabel("Color:");
        colorLabel.setPreferredSize(new Dimension(80, 25));
        formPanel.add(colorLabel, gbc);

        gbc.gridx = 1;
        colorComboBox = new JComboBox<String>(COLORS.keySet().toArray(new String[0]));
        colorComboBox.setPreferredSize(new Dimension(100, 25));
        colorComboBox.setSelectedIndex(1);
        formPanel.add(colorComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        paintButton = new JButton("Paint");
        paintButton.setPreferredSize(new Dimension(100, 30));
        paintButton.addActionListener(e -> paintCuadro());
        formPanel.add(paintButton, gbc);

//        controlPanel.add(formPanel);
//        add(paintPanel, BorderLayout.CENTER);
//        add(controlPanel, BorderLayout.EAST);
        gbc.gridy = 4;
        detectButton = new JButton("Detect Clusters");
        detectButton.setPreferredSize(new Dimension(150, 30));
        detectButton.addActionListener(e -> detectClusters());
        formPanel.add(detectButton, gbc);

        gbc.gridy = 5;
        nextClusterButton = new JButton("Next Cluster");
        nextClusterButton.setPreferredSize(new Dimension(150, 30));
        nextClusterButton.setEnabled(false);
        nextClusterButton.addActionListener(e -> highlightNextCluster());
        formPanel.add(nextClusterButton, gbc);

        return formPanel;

    }

    private void paintCuadro() {
        int fila = (int) filaSpinner.getValue();
        int col = (int) colSpinner.getValue();
        int color = colorComboBox.getSelectedIndex();
        cuadro.pintarCuadro(fila, col, color);
        paintPanel.repaint();
    }

    private void detectClusters() {
        clusteres = cuadro.detectarClusteres();
        currentClusterIndex = 0;
        if (clusteres.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No clusters detected.");
            nextClusterButton.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, clusteres.size() + " clusters detected.");
            nextClusterButton.setEnabled(true);
            highlightNextCluster();
        }
    }

    private void highlightNextCluster() {
        if (clusteres == null || clusteres.isEmpty()) {
            return;
        }

        Set<Cuadro.Punto> currentCluster = clusteres.get(currentClusterIndex);
        int colorIndex = (currentClusterIndex % COLORS.size()) + 1; // Ciclar colores
        for (Cuadro.Punto p : currentCluster) {
            cuadro.getMatriz()[p.getFila()][p.getCol()] = colorIndex;
        }
        currentClusterIndex = (currentClusterIndex + 1) % clusteres.size();
        paintPanel.repaint();
    }

    private void drawCuadro(Graphics g) {
        int cellSize = Math.min(paintPanel.getWidth() / cuadro.getMatriz()[0].length,
                paintPanel.getHeight() / cuadro.getMatriz().length);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0,
                cuadro.getMatriz()[0].length * cellSize,
                cuadro.getMatriz().length * cellSize);
        for (int i = 0; i < cuadro.getMatriz().length; i++) {
            for (int j = 0; j < cuadro.getMatriz()[i].length; j++) {
                int color = cuadro.getMatriz()[i][j];
                g.setColor((Color) COLORS.values().toArray()[Math.max(0, Math.min(color, COLORS.size() - 1))]);
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }
}
