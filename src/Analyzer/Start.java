package Analyzer;

import static Analyzer.Analyzer.analyzeFile;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Start extends javax.swing.JFrame {
    
    int x, y, line = 1; 
    JFileChooser chooser;
    FileNameExtensionFilter filter;
    File file;
    String path, code = "";
        
    public Start() {
        this.setTitle("Analyzer");
        initComponents();
        textPanel.setCaretPosition(0);
    }
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
        getImage(ClassLoader.getSystemResource("Icons/logo.png"));


   return retValue;
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        abrir = new javax.swing.JButton();
        check = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textPanel = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        close_btn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        errorsPanel = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        errorsCounter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(816, 500));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(816, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        mainPanel.setMaximumSize(new java.awt.Dimension(816, 500));
        mainPanel.setMinimumSize(new java.awt.Dimension(816, 500));
        mainPanel.setPreferredSize(new java.awt.Dimension(816, 500));
        mainPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                mainPanelMouseDragged(evt);
            }
        });
        mainPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainPanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mainPanelMouseReleased(evt);
            }
        });
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(33, 33, 33));
        jPanel2.setForeground(new java.awt.Color(102, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        abrir.setBackground(new java.awt.Color(0, 51, 255));
        abrir.setFont(new java.awt.Font("JetBrains Mono NL", 0, 14)); // NOI18N
        abrir.setForeground(new java.awt.Color(51, 51, 51));
        abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-insert-page-24 (1).png"))); // NOI18N
        abrir.setBorder(null);
        abrir.setContentAreaFilled(false);
        abrir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        abrir.setFocusable(false);
        abrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        abrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                abrirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                abrirMouseExited(evt);
            }
        });
        abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });
        jPanel2.add(abrir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 30));

        check.setBackground(new java.awt.Color(0, 51, 255));
        check.setFont(new java.awt.Font("JetBrains Mono NL", 0, 14)); // NOI18N
        check.setForeground(new java.awt.Color(51, 51, 51));
        check.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-check-file-24 (2).png"))); // NOI18N
        check.setBorder(null);
        check.setContentAreaFilled(false);
        check.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        check.setEnabled(false);
        check.setFocusable(false);
        check.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        check.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                checkMouseExited(evt);
            }
        });
        check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkActionPerformed(evt);
            }
        });
        jPanel2.add(check, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 70, 30));

        editar.setBackground(new java.awt.Color(0, 51, 255));
        editar.setFont(new java.awt.Font("JetBrains Mono NL", 0, 14)); // NOI18N
        editar.setForeground(new java.awt.Color(51, 51, 51));
        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-edit-file-24 (3).png"))); // NOI18N
        editar.setBorder(null);
        editar.setContentAreaFilled(false);
        editar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        editar.setEnabled(false);
        editar.setFocusable(false);
        editar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editarMouseExited(evt);
            }
        });
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        jPanel2.add(editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 70, 30));

        guardar.setBackground(new java.awt.Color(0, 51, 255));
        guardar.setFont(new java.awt.Font("JetBrains Mono NL", 0, 14)); // NOI18N
        guardar.setForeground(new java.awt.Color(51, 51, 51));
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-save-24 (1).png"))); // NOI18N
        guardar.setBorder(null);
        guardar.setContentAreaFilled(false);
        guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        guardar.setEnabled(false);
        guardar.setFocusable(false);
        guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                guardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                guardarMouseExited(evt);
            }
        });
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel2.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 70, 30));

        mainPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 840, 30));

        textPanel.setEditable(false);
        textPanel.setBackground(new java.awt.Color(33, 33, 33));
        textPanel.setColumns(20);
        textPanel.setFont(new java.awt.Font("JetBrains Mono", 0, 13)); // NOI18N
        textPanel.setForeground(new java.awt.Color(255, 255, 255));
        textPanel.setRows(5);
        textPanel.setBorder(null);
        textPanel.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(textPanel);

        mainPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 840, 410));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        close_btn.setBackground(new java.awt.Color(255, 255, 255));
        close_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/close_disabled.png"))); // NOI18N
        close_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        close_btn.setBorderPainted(false);
        close_btn.setContentAreaFilled(false);
        close_btn.setFocusable(false);
        close_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                close_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                close_btnMouseExited(evt);
            }
        });
        close_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_btnActionPerformed(evt);
            }
        });
        jPanel1.add(close_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, 40, 40));

        jLabel2.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-google-tag-manager-25.png"))); // NOI18N
        jLabel2.setText("Analyzer");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 40));

        mainPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 40));

        jScrollPane2.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane2.setForeground(new java.awt.Color(255, 0, 0));

        errorsPanel.setEditable(false);
        errorsPanel.setBackground(new java.awt.Color(102, 102, 102));
        errorsPanel.setColumns(20);
        errorsPanel.setFont(new java.awt.Font("JetBrains Mono", 0, 13)); // NOI18N
        errorsPanel.setForeground(new java.awt.Color(255, 255, 255));
        errorsPanel.setRows(5);
        jScrollPane2.setViewportView(errorsPanel);

        mainPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 840, 130));

        jPanel3.setBackground(new java.awt.Color(33, 33, 33));
        mainPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 840, 60));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        errorsCounter.setFont(new java.awt.Font("JetBrains Mono Medium", 0, 13)); // NOI18N
        errorsCounter.setForeground(new java.awt.Color(255, 255, 255));
        errorsCounter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-info-20.png"))); // NOI18N
        errorsCounter.setText("Salida");
        jPanel4.add(errorsCounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        mainPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 840, 20));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 630));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mainPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_mainPanelMousePressed

    private void mainPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseDragged
        this.setLocation(this.getLocation().x + evt.getX()-x, this.getLocation().y + evt.getY()-y );
        this.setOpacity(0.9f);
    }//GEN-LAST:event_mainPanelMouseDragged

    private void mainPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseReleased
        this.setOpacity(1f);
    }//GEN-LAST:event_mainPanelMouseReleased

    private void abrirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abrirMouseEntered
        abrir.setContentAreaFilled(true);
    }//GEN-LAST:event_abrirMouseEntered

    private void abrirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abrirMouseExited
        abrir.setContentAreaFilled(false);
    }//GEN-LAST:event_abrirMouseExited

    private void abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirActionPerformed
        //Lanzar el fileChooser para poder elegir un archivo con extensión C o txt
        
        if( chooser == null ) chooser = new JFileChooser();
        
        filter = new FileNameExtensionFilter("Archivos C", "C", "txt");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );

        int seleccion = chooser.showOpenDialog( this );

        if( seleccion == JFileChooser.APPROVE_OPTION ){
            file = chooser.getSelectedFile();
            
            this.setTitle(file.getName());
            path = file.getAbsolutePath();
            openFile();
            editar.setEnabled(true);
            
            //Llamar a las funciones que analizan el archivo
            showErrors(analyzeFile(path));
            Analyzer.printTokens();
            Analyzer.printErrors();
        }
    }//GEN-LAST:event_abrirActionPerformed

    private void close_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_btnMouseEntered
        close_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/close_enabled.png")));
    }//GEN-LAST:event_close_btnMouseEntered

    private void close_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_btnMouseExited
        close_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/close_disabled.png")));
    }//GEN-LAST:event_close_btnMouseExited

    private void close_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_btnActionPerformed
        this.dispose();
    }//GEN-LAST:event_close_btnActionPerformed

    private void editarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editarMouseEntered
        editar.setContentAreaFilled(true);
    }//GEN-LAST:event_editarMouseEntered

    private void editarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editarMouseExited
        editar.setContentAreaFilled(false);
    }//GEN-LAST:event_editarMouseExited

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        textPanel.setEditable(true);
        textPanel.setText(code);
        guardar.setEnabled(true);
        textPanel.getCaret().setVisible(true);
    }//GEN-LAST:event_editarActionPerformed

    private void guardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarMouseEntered
        guardar.setContentAreaFilled(true);
    }//GEN-LAST:event_guardarMouseEntered

    private void guardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarMouseExited
        guardar.setContentAreaFilled(false);
    }//GEN-LAST:event_guardarMouseExited

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        PrintWriter pw;
        
        try {
            pw = new PrintWriter(file);
            pw.write(textPanel.getText());
            pw.flush();
            openFile();
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        textPanel.setEditable(false);
        guardar.setEnabled(false);
        check.setEnabled(true);
    }//GEN-LAST:event_guardarActionPerformed

    private void checkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkMouseEntered
        check.setContentAreaFilled(true);
    }//GEN-LAST:event_checkMouseEntered

    private void checkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkMouseExited
        check.setContentAreaFilled(false);
    }//GEN-LAST:event_checkMouseExited

    private void checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActionPerformed
        //Después de editar un archivo, se puede llamar al boton para checar el archivo e imprimir 
        // tokens y errores
        showErrors(analyzeFile(path));
        Analyzer.printTokens();
        Analyzer.printErrors();
    }//GEN-LAST:event_checkActionPerformed

    //Mostrar errores en la interfaz grafica
    void showErrors(List<Integer> errors){
        errorsPanel.setText("");
        errorsCounter.setText("Salida");
        if(!errors.isEmpty()){
            errors.forEach(error -> {
                errorsCounter.setText("Salida - " + errors.size() + " errores");
                errorsPanel.setText(errorsPanel.getText() + "\nError en la línea " + error);
            });
        }else{
            errorsPanel.setText("No hay errores");
        }
    }
    
    //Abrir un archivo
    void openFile(){
        String next;
        code = "";
        textPanel.setText("");
        line = 1;
        
        try (Scanner sc = new Scanner(file)) {
            while(sc.hasNextLine()){
                next = sc.nextLine();
                code += next + "\n";
                textPanel.setText(textPanel.getText() + String.format("%2d |", line) + "\t"+ next + "\n");
                line++;
            }            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String args[]) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new Start().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrir;
    private javax.swing.JButton check;
    private javax.swing.JButton close_btn;
    private javax.swing.JButton editar;
    private javax.swing.JLabel errorsCounter;
    private javax.swing.JTextArea errorsPanel;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextArea textPanel;
    // End of variables declaration//GEN-END:variables

}
