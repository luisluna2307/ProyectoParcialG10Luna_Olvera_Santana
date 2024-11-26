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

    private Cuadro cuadro;
    private JPanel paintPanel;
    private JComboBox<String> clusterComboBox;
    private JComboBox<String> colorComboBox;
    private JButton paintButton, detectButton, nextClusterButton;
    private final Map<String, Color> COLORS = new LinkedHashMap<>() {{
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
    }};
    private List<Set<Cuadro.Punto>> clusteres;
    private int currentClusterIndex = 0;

    public PintarCuadroGUI() {
        super("Pintar Cuadro GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        JLabel clusterLabel = new JLabel("Seleccionar Clúster:");
        clusterLabel.setPreferredSize(new Dimension(150, 25));
        formPanel.add(clusterLabel, gbc);

        gbc.gridx = 1;
        clusterComboBox = new JComboBox<>();
        clusterComboBox.setPreferredSize(new Dimension(100, 25));
        formPanel.add(clusterComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel colorLabel = new JLabel("Seleccionar Color:");
        colorLabel.setPreferredSize(new Dimension(150, 25));
        formPanel.add(colorLabel, gbc);

        gbc.gridx = 1;
        colorComboBox = new JComboBox<>(COLORS.keySet().toArray(new String[0]));
        colorComboBox.setPreferredSize(new Dimension(100, 25));
        colorComboBox.setSelectedIndex(1);
        formPanel.add(colorComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        paintButton = new JButton("Pintar Clúster");
        paintButton.setPreferredSize(new Dimension(150, 30));
        paintButton.addActionListener(e -> pintarCluster());
        formPanel.add(paintButton, gbc);

        gbc.gridy = 3;
        detectButton = new JButton("Detectar Clústeres");
        detectButton.setPreferredSize(new Dimension(150, 30));
        detectButton.addActionListener(e -> detectClusters());
        formPanel.add(detectButton, gbc);

        return formPanel;
    }

    private void pintarCluster() {
        int colorIndex = colorComboBox.getSelectedIndex() + 1; 
        Set<Cuadro.Punto> selectedCluster = clusteres.get(clusterComboBox.getSelectedIndex());
        cuadro.pintarCluster(selectedCluster, colorIndex);
        paintPanel.repaint();
    }

    private void detectClusters() {
        clusteres = cuadro.detectarClusteres();
        clusterComboBox.removeAllItems();
        if (clusteres.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se detectaron clústeres.");
        } else {
            JOptionPane.showMessageDialog(this, clusteres.size() + " clústeres detectados.");
            for (int i = 0; i < clusteres.size(); i++) {
                clusterComboBox.addItem("Clúster " + (i + 1) + " (Tamaño: " + clusteres.get(i).size() + ")");
            }
        }
    }

    private void drawCuadro(Graphics g) {
        int cellSize = Math.min(paintPanel.getWidth() / cuadro.getMatriz()[0].length,
                            paintPanel.getHeight() / cuadro.getMatriz().length);

        Color[] colorArray = COLORS.values().toArray(new Color[0]);
        
        for (int i = 0; i < cuadro.getMatriz().length; i++) {
            for (int j = 0; j < cuadro.getMatriz()[i].length; j++) {
                int colorIndex = Math.max(0, Math.min(cuadro.getMatriz()[i][j], colorArray.length - 1));
                g.setColor(colorArray[colorIndex]);
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PintarCuadroGUI().setVisible(true));
    }
}
