package projetobd.frames.inserts;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import projetobd.App;
import projetobd.Genero;
import projetobd.frames.Login;

public class InsertGenero extends javax.swing.JFrame {

    private static InsertGenero igUnico;
    
    
    public static InsertGenero getInsertGenero(){
        if(igUnico == null){
            igUnico = new InsertGenero();
        }
        return igUnico;
    }
    /**
     * Creates new form InsertGenero
     */
    public InsertGenero() {
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

        titulo = new javax.swing.JLabel();
        cod = new javax.swing.JLabel();
        nome = new javax.swing.JLabel();
        desc = new javax.swing.JLabel();
        cx_cod = new javax.swing.JTextField();
        cx_nome = new javax.swing.JTextField();
        cx_desc = new javax.swing.JTextField();
        bt_close = new javax.swing.JButton();
        bt_confirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titulo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        titulo.setText("Inserir Genero");

        cod.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cod.setText("Código: ");

        nome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nome.setText("Nome: ");

        desc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        desc.setText("Descrição:");

        bt_close.setText("Cancel");
        bt_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_closeActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_confirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_close))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(desc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cx_cod)
                                .addComponent(cx_nome)
                                .addComponent(cx_desc, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
                        .addGap(0, 48, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(titulo)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cod)
                    .addComponent(cx_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome)
                    .addComponent(cx_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(desc)
                    .addComponent(cx_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_close)
                    .addComponent(bt_confirm))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_closeActionPerformed
        // TODO add your handling code here:
        quit();
    }//GEN-LAST:event_bt_closeActionPerformed

    private void bt_confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_confirmActionPerformed
        // TODO add your handling code here:
        Genero gen = null;
        
        try {
            gen = new Genero(Integer.parseInt(cx_cod.getText()), 
                            cx_nome.getText(),
                            cx_desc.getText());
            
            App app = new App();
            app.setUser(Login.getFrameLogin().username);
            app.setPassword(Login.getFrameLogin().bdpassword);
            
            try {
                app.insertGen(gen);
                JOptionPane.showMessageDialog(null, "Insert Realizado", "Transacao feita", 1);
            	clear();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Insert invalido", "Transacao invalida", 2);
            }
        }catch (NumberFormatException nfe) {
    		JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!", "Transacao invalida", 2);
    	}
    }//GEN-LAST:event_bt_confirmActionPerformed

    public void clear() {
        cx_cod.setText("");
        cx_nome.setText("");
        cx_desc.setText("");
        cx_cod.requestFocus();;
    }
    
    public void quit(){
        if (!cx_cod.getText().isBlank() || !cx_nome.getText().isBlank()){
            int op = JOptionPane.showConfirmDialog(null, "Deseja cancelar a insercao?", "Sair", 1);
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
            java.util.logging.Logger.getLogger(InsertGenero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsertGenero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsertGenero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsertGenero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsertGenero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_close;
    private javax.swing.JButton bt_confirm;
    private javax.swing.JLabel cod;
    private javax.swing.JTextField cx_cod;
    private javax.swing.JTextField cx_desc;
    private javax.swing.JTextField cx_nome;
    private javax.swing.JLabel desc;
    private javax.swing.JLabel nome;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
