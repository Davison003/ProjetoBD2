package projetobd.frames.deletes;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import projetobd.App;
import projetobd.frames.Login;


public class DeleteLivro extends javax.swing.JFrame {

    private static DeleteLivro dlUnico;
    
    
    public static DeleteLivro getDeleteLivro(){
        if(dlUnico == null){
            dlUnico = new DeleteLivro();
        }
        return dlUnico;
    }
    
    /**
     * Creates new form DeleteLivro
     */
    public DeleteLivro() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();
        cod = new javax.swing.JLabel();
        cx_cod = new javax.swing.JTextField();
        bt_cancel = new javax.swing.JButton();
        bt_confirm = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titulo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        titulo.setText("Remover Livros");

        cod.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cod.setText("Código: ");

        bt_cancel.setText("Cancel");
        bt_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelActionPerformed(evt);
            }
        });

        bt_confirm.setText("Confirm");
        bt_confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_confirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(cod)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo)
                    .addComponent(cx_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_confirm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_cancel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(titulo)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cod)
                    .addComponent(cx_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_cancel)
                    .addComponent(bt_confirm))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
        // TODO add your handling code here:
        quit();
    }//GEN-LAST:event_bt_cancelActionPerformed

    private void bt_confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_confirmActionPerformed
        // TODO add your handling code here:
        App app = new App();
        app.setUser(Login.getFrameLogin().username);
        app.setPassword(Login.getFrameLogin().bdpassword);
        try {
            app.DeleteLivro(Integer.parseInt(cx_cod.getText()));
            clear();
        } catch (SQLException | NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Delete invalido", "Transacao invalida", 2);
        }
    }//GEN-LAST:event_bt_confirmActionPerformed

    public void clear() {
        cx_cod.setText("");
        cx_cod.requestFocus();
    }
    
    public void quit(){
        if (!cx_cod.getText().isBlank()){
            int op = JOptionPane.showConfirmDialog(null, "Deseja cancelar o delete?", "Sair", 1);
            if(op == 0){
                clear();
                sair();
            }
        }
        else {
            sair();
        }
        
    }
    
    public void sair(){
        this.dispose();
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DeleteLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteLivro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cancel;
    private javax.swing.JButton bt_confirm;
    private javax.swing.JLabel cod;
    private javax.swing.JTextField cx_cod;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}