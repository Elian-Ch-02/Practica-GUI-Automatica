/**
 *
 * @author Elian
 */

package miniencuesta;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniEncuesta extends JFrame {
    // Componentes para sistema operativo (RadioButtons)
    private JRadioButton rbWindows;
    private JRadioButton rbLinux;
    private JRadioButton rbMac;
    private ButtonGroup grupoSO;
    
    // Componentes para especialidad (CheckBoxes)
    private JCheckBox cbProgramacion;
    private JCheckBox cbDiseño;
    private JCheckBox cbAdministracion;
    
    // Componentes para horas (Slider)
    private JSlider sliderHoras;
    private JLabel labelHoras;
    
    // Botón para procesar encuesta
    private JButton btnProcesar;
    
    public MiniEncuesta() {
        initComponents();
        setupEventListeners();
        setupLayout();
    }
    
    private void initComponents() {
        setTitle("Miniencuesta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        // RadioButtons para sistema operativo
        rbWindows = new JRadioButton("Windows");
        rbLinux = new JRadioButton("Linux");
        rbMac = new JRadioButton("Mac");
        
        // Agrupar RadioButtons
        grupoSO = new ButtonGroup();
        grupoSO.add(rbWindows);
        grupoSO.add(rbLinux);
        grupoSO.add(rbMac);
        
        // CheckBoxes para especialidad
        cbProgramacion = new JCheckBox("Programación");
        cbDiseño = new JCheckBox("Diseño gráfico");
        cbAdministracion = new JCheckBox("Administración");
        
        // Slider para horas
        sliderHoras = new JSlider(0, 10, 0);
        sliderHoras.setMajorTickSpacing(2);
        sliderHoras.setMinorTickSpacing(1);
        sliderHoras.setPaintTicks(true);
        sliderHoras.setPaintLabels(true);
        
        // Label para mostrar valor del slider
        labelHoras = new JLabel("Horas: 0");
        
        // Botón procesar
        btnProcesar = new JButton("Procesar Encuesta");
    }
    
    private void setupEventListeners() {
        // Listener para el slider de horas
        sliderHoras.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int valor = sliderHoras.getValue();
                labelHoras.setText("Horas: " + valor);
            }
        });
        
        // Listener para RadioButtons de sistema operativo
        ActionListener radioListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton source = (JRadioButton) e.getSource();
                System.out.println("Sistema operativo seleccionado: " + source.getText());
            }
        };
        
        rbWindows.addActionListener(radioListener);
        rbLinux.addActionListener(radioListener);
        rbMac.addActionListener(radioListener);
        
        // Listener para CheckBoxes de especialidad
        ActionListener checkListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox source = (JCheckBox) e.getSource();
                String estado = source.isSelected() ? "seleccionado" : "deseleccionado";
                System.out.println("Especialidad " + source.getText() + " " + estado);
            }
        };
        
        cbProgramacion.addActionListener(checkListener);
        cbDiseño.addActionListener(checkListener);
        cbAdministracion.addActionListener(checkListener);
        
        // Listener para botón procesar
        btnProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarEncuesta();
            }
        });
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Título sistema operativo
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelPrincipal.add(new JLabel("Elige un sistema operativo:"), gbc);
        
        // RadioButtons
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        panelPrincipal.add(rbWindows, gbc);
        gbc.gridx = 1;
        panelPrincipal.add(rbLinux, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        panelPrincipal.add(rbMac, gbc);
        
        // Título especialidad
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelPrincipal.add(new JLabel("Elige tu especialidad:"), gbc);
        
        // CheckBoxes
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 4;
        panelPrincipal.add(cbProgramacion, gbc);
        gbc.gridx = 1;
        panelPrincipal.add(cbDiseño, gbc);
        gbc.gridx = 0; gbc.gridy = 5;
        panelPrincipal.add(cbAdministracion, gbc);
        
        // Título horas
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        panelPrincipal.add(new JLabel("Horas dedicadas al ordenador:"), gbc);
        
        // Slider y label
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(sliderHoras, gbc);
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(labelHoras, gbc);
        
        // Botón procesar
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(btnProcesar, gbc);
        
        add(panelPrincipal, BorderLayout.CENTER);
    }
    
    private void procesarEncuesta() {
        System.out.println("=== RESULTADOS DE LA ENCUESTA ===");
        
        // Obtener sistema operativo seleccionado
        String sistemaOperativo = "Ninguno";
        if (rbWindows.isSelected()) sistemaOperativo = "Windows";
        else if (rbLinux.isSelected()) sistemaOperativo = "Linux";
        else if (rbMac.isSelected()) sistemaOperativo = "Mac";
        
        System.out.println("Sistema Operativo: " + sistemaOperativo);
        
        // Obtener especialidades seleccionadas
        System.out.println("Especialidades:");
        boolean algunaEspecialidad = false;
        if (cbProgramacion.isSelected()) {
            System.out.println("  - Programación");
            algunaEspecialidad = true;
        }
        if (cbDiseño.isSelected()) {
            System.out.println("  - Diseño gráfico");
            algunaEspecialidad = true;
        }
        if (cbAdministracion.isSelected()) {
            System.out.println("  - Administración");
            algunaEspecialidad = true;
        }
        if (!algunaEspecialidad) {
            System.out.println("  - Ninguna seleccionada");
        }
        
        // Obtener horas
        System.out.println("Horas dedicadas: " + sliderHoras.getValue());
        System.out.println("================================");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new MiniEncuesta().setVisible(true);
            }
        });
    }
}

