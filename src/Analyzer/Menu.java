package Analyzer;

import java.io.File;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Menu extends javax.swing.JFrame {
    
    int x, y, line = 1; 
    JFileChooser abrirArchivo;
        
    public Menu() {
        this.setTitle("Alphabet - Inicio");
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        close_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textPanel = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(816, 500));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(816, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
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

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("JetBrains Mono NL", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(51, 51, 51));
        jButton3.setText("Seleccionar archivo");
        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        mainPanel.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 160, 30));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-google-tag-manager-25.png"))); // NOI18N
        jLabel1.setText("Analyzer");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 40));

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
        jPanel1.add(close_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, 40, 40));

        mainPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 40));

        textPanel.setEditable(false);
        textPanel.setBackground(new java.awt.Color(50, 48, 50));
        textPanel.setColumns(20);
        textPanel.setFont(new java.awt.Font("JetBrains Mono", 0, 13)); // NOI18N
        textPanel.setForeground(new java.awt.Color(255, 255, 255));
        textPanel.setRows(5);
        jScrollPane1.setViewportView(textPanel);

        mainPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 540, 370));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 816, 499));

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

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        jButton3.setForeground(new java.awt.Color(51, 51, 51));
        jButton3.setBorder(null);
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if( abrirArchivo == null ) abrirArchivo = new JFileChooser();
            //Con esto solamente podamos abrir archivos
            abrirArchivo.setFileSelectionMode( JFileChooser.FILES_ONLY );
 
            int seleccion = abrirArchivo.showOpenDialog( this );
 
            if( seleccion == JFileChooser.APPROVE_OPTION )
            {
                File file = abrirArchivo.getSelectedFile();
                try
                {
                    String nombre = file.getName();
                    String path = file.getAbsolutePath();
                    Scanner sc = new Scanner(file);
                    textPanel.setText(String.format("%2d |", line) + "\t");
                while(sc.hasNextLine()){
                    line++;
                    textPanel.setText(textPanel.getText() + "\n"+ String.format("%2d |", line) + "\t"+ sc.nextLine());
                    
                }
                    this.setTitle( nombre );
 
 
                }catch( Exception exp){}
            }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void close_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_btnMouseEntered
        close_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/close_enabled.png")));
    }//GEN-LAST:event_close_btnMouseEntered

    private void close_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_btnMouseExited
        close_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/close_disabled.png")));
    }//GEN-LAST:event_close_btnMouseExited

    private void close_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_btnActionPerformed
        this.dispose();
    }//GEN-LAST:event_close_btnActionPerformed

    public static void main(String args[]) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton close_btn;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextArea textPanel;
    // End of variables declaration//GEN-END:variables

}
