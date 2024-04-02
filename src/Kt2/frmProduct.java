/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kt2;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author giahuy
 */
class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
    CheckBoxRenderer() {
        setHorizontalAlignment(JCheckBox.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Boolean) {
            setSelected((Boolean) value);
        } else if (value instanceof String) {
            // Xử lý chuyển đổi từ String sang Boolean tại đây
            String stringValue = (String) value;
            // Ví dụ: Nếu "Disable" là String biểu thị cho giá trị False
            setSelected(stringValue.equalsIgnoreCase("Disable"));
        }
        return this;
    }
}
public class frmProduct extends javax.swing.JFrame {

    /**
     * Creates new form frmProduct
     */
    private String fullName;
    private boolean authentication;
    public frmProduct(String fullName, boolean authentication) {
        initComponents();
        // Tạo một TableCellRenderer mới
        CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();

        // Đặt TableCellRenderer cho cột "Vô hiệu hóa" tại index 1
        tbProduct.getColumnModel().getColumn(1).setCellRenderer(checkBoxRenderer);
        this.fullName = fullName;
        lb_fullName.setText(fullName); 
        this.authentication = authentication;
        if(authentication == true){
            lb_authentication.setText("Admin");
        }else{
            lb_authentication.setText("Nhân viên bán hàng");
        }
    }

    private frmProduct() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public DefaultTableModel productList() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("STT");
        model.addColumn("VÔ HIỆU HÓA");
        model.addColumn("NHÓM MẶT HÀNG");
        model.addColumn("MÃ MẶT HÀNG");
        model.addColumn("MẶT HÀNG");
        model.addColumn("GIÁ BÁN");
        model.addColumn("ĐƠN VỊ TÍNH");
        model.addColumn("MÔ TẢ");

        Connection connection = Connect_Database.connect();
        if (connection != null) {
            try {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT MAMH, TENMH, L.TENLOAI, GIABAN, DVT, MOTA, M.VOHIEUHOA FROM LOAI_MAT_HANG L, MAT_HANG M WHERE L.MALOAI = M.MALOAI");
                int count = 1;
                while (rs.next()) {
                    boolean voHieuHoa = rs.getBoolean("VOHIEUHOA");
                    String tenLoai = rs.getString("TENLOAI");
                    String maMH = rs.getString("MAMH");
                    String tenMH = rs.getString("TENMH");
                    double giaBan = rs.getDouble("GIABAN");
                    String dvt = rs.getString("DVT");
                    String mota = rs.getString("MOTA");
                    model.addRow(new Object[]{count, voHieuHoa, tenLoai, maMH, tenMH, giaBan, dvt, mota});
                    count++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                Connect_Database.close(connection);
            }
        }
        return model;
}

    public void loadDataTable() {
        DefaultTableModel model = productList();
        tbProduct.setModel(model);
        // Đặt renderer chỉ cho cột "VÔ HIỆU HÓA"
        CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
        tbProduct.getColumnModel().getColumn(1).setCellRenderer(checkBoxRenderer);
    }
    
    public DefaultComboBoxModel<String> getComboBoxData() {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        Connection connection = Connect_Database.connect();
        if (connection != null) {
            try {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT TENLOAI FROM LOAI_MAT_HANG");
                while (rs.next()) {
                    String tenLoai = rs.getString("TENLOAI");
                    comboBoxModel.addElement(tenLoai);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                Connect_Database.close(connection);
            }
        }
        return comboBoxModel;
    }

    public void loadComboBoxData() {
        DefaultComboBoxModel<String> comboBoxModel = getComboBoxData();
        cmbCategory.setModel(comboBoxModel); // Thay yourComboBox bằng tên thực của JComboBox của bạn
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lb_authentication = new javax.swing.JLabel();
        lb_fullName = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProduct = new javax.swing.JTable();
        lbTTMH = new javax.swing.JLabel();
        panelInformationProduct = new javax.swing.JPanel();
        lbLMH = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        txtIdProduct = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        btnClicktoCategory = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNameProduct = new javax.swing.JTextField();
        txtDVT = new javax.swing.JTextField();
        rdVHH = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        txtDecsription = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("PHẦN MỀM QUẢN LÝ VĂN PHÒNG PHẨM");

        lb_authentication.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lb_authentication.setText("Chức vụ:");

        lb_fullName.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lb_fullName.setText("Họ tên");

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_back.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_add.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_edit.png"))); // NOI18N
        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_delete.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_Save.png"))); // NOI18N
        btnSave.setText("Lưu");

        txtSearch.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnSearch.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tbProduct.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbProduct);

        lbTTMH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbTTMH.setText("Thông tin mặt hàng");

        lbLMH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbLMH.setText("Loại mặt hàng");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Mã mặt hàng");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Giá bán");

        cmbCategory.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtIdProduct.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtPrice.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnClicktoCategory.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnClicktoCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_add_category.png"))); // NOI18N
        btnClicktoCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClicktoCategoryActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Mặt hàng");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("ĐVT");

        txtNameProduct.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtDVT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        rdVHH.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rdVHH.setText("Disable");
        rdVHH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdVHHActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Mô tả");

        txtDecsription.setColumns(20);
        txtDecsription.setRows(5);

        javax.swing.GroupLayout panelInformationProductLayout = new javax.swing.GroupLayout(panelInformationProduct);
        panelInformationProduct.setLayout(panelInformationProductLayout);
        panelInformationProductLayout.setHorizontalGroup(
            panelInformationProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformationProductLayout.createSequentialGroup()
                .addGroup(panelInformationProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbLMH)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(32, 32, 32)
                .addGroup(panelInformationProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelInformationProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cmbCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIdProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(btnClicktoCategory)
                .addGap(26, 26, 26)
                .addGroup(panelInformationProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(30, 30, 30)
                .addGroup(panelInformationProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNameProduct)
                    .addComponent(txtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelInformationProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformationProductLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDecsription))
                    .addGroup(panelInformationProductLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(rdVHH)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelInformationProductLayout.setVerticalGroup(
            panelInformationProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformationProductLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInformationProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLMH)
                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClicktoCategory)
                    .addComponent(jLabel4)
                    .addComponent(txtNameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtDecsription, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInformationProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdVHH))
                .addGap(126, 126, 126)
                .addGroup(panelInformationProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_authentication)
                .addGap(18, 18, 18)
                .addComponent(lb_fullName, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 1108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSearch))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 18, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInformationProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(406, 406, 406)
                    .addComponent(lbTTMH)
                    .addContainerGap(660, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lb_authentication, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBack)
                    .addComponent(lb_fullName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelInformationProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(267, 267, 267)
                    .addComponent(lbTTMH)
                    .addContainerGap(374, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.dispose();
        frmMain frmMain = new frmMain(fullName,authentication);
        frmMain.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed
    
    private String getMaLoaiByName(String tenLoai) {
        Connection connection = Connect_Database.connect();
        String maLoai = "";

        if (connection != null) {
            try {
                String query = "SELECT MALOAI FROM LOAI_MAT_HANG WHERE TENLOAI = ?";
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setString(1, tenLoai);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    maLoai = rs.getString("MALOAI");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                Connect_Database.close(connection);
            }
        }

        return maLoai;
    }

    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
            // TODO add your handling code here:    
        boolean VHH = false; // Khai báo và khởi tạo biến VHH

        if (rdVHH.isSelected()) {
            VHH = true;
        }

        Connection connection = Connect_Database.connect();
        if (connection != null) {
            try {
                String maLoai = getMaLoaiByName(((String) cmbCategory.getSelectedItem()));
                String query = "INSERT INTO MAT_HANG (MAMH, TENMH, GIABAN, DVT, MALOAI, MOTA, VOHIEUHOA) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setString(1, txtIdProduct.getText());
                pst.setString(2, txtNameProduct.getText());
                pst.setInt(3, Integer.parseInt(txtPrice.getText()));
                pst.setString(4, txtDVT.getText());
                pst.setString(5, maLoai);
                pst.setString(6, txtDecsription.getText());
                pst.setBoolean(7, VHH);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi thêm sản phẩm: " + ex.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            } finally {
                Connect_Database.close(connection);
            }
        }
        loadDataTable();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        boolean VHH = false; // Khai báo và khởi tạo biến VHH

        if (rdVHH.isSelected()) {
            VHH = true;
        }

        Connection connection = Connect_Database.connect();
        if (connection != null) {
            try {
                String maLoai = getMaLoaiByName((String) cmbCategory.getSelectedItem());
                String query = "UPDATE MAT_HANG SET TENMH=?, GIABAN=?, DVT=?, MALOAI=?, MOTA=?, VOHIEUHOA=? WHERE MAMH=?";
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setString(1, txtNameProduct.getText());
                pst.setInt(2, Integer.parseInt(txtPrice.getText()));
                pst.setString(3, txtDVT.getText());
                pst.setString(4, maLoai);
                pst.setString(5, txtDecsription.getText());
                pst.setBoolean(6, VHH);
                pst.setString(7, txtIdProduct.getText());
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Cập nhật sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật sản phẩm: " + ex.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            } finally {
                Connect_Database.close(connection);
            }
        }
        loadDataTable();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int confirmation = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sản phẩm không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            Connection connection = Connect_Database.connect();
            if (connection != null) {
                try {
                    String query = "DELETE FROM MAT_HANG WHERE MAMH=?";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setString(1, txtIdProduct.getText());
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi xóa sản phẩm: " + ex.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                } finally {
                    Connect_Database.close(connection);
                }
            }
            loadDataTable();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        loadDataTable();
        loadComboBoxData();
    }//GEN-LAST:event_formWindowOpened

    private void btnClicktoCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClicktoCategoryActionPerformed
        // TODO add your handling code here:
        frmCategory frmCategory = new frmCategory(fullName, authentication);
        frmCategory.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnClicktoCategoryActionPerformed

    private void rdVHHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdVHHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdVHHActionPerformed

    private void tbProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductMouseClicked
        // TODO add your handling code here:
        int row = tbProduct.getSelectedRow();
        if (row != -1) {
            DefaultTableModel model = (DefaultTableModel) tbProduct.getModel();

            // Lấy dữ liệu từ hàng được chọn trong bảng
            String tenLoai = model.getValueAt(row, 2).toString();
            String maMH = model.getValueAt(row, 3).toString();
            String tenMH = model.getValueAt(row, 4).toString();
            double giaBan = Double.parseDouble(model.getValueAt(row, 5).toString());
            String dvt = model.getValueAt(row, 6).toString();
            String mota = model.getValueAt(row, 7).toString();
            boolean voHieuHoa = (boolean) model.getValueAt(row, 1);

            // Đưa dữ liệu vào các trường trong panelInformationProduct
            cmbCategory.setSelectedItem(tenLoai);
            txtIdProduct.setText(maMH);
            txtNameProduct.setText(tenMH);
            txtPrice.setText(String.valueOf(giaBan));
            txtDVT.setText(dvt);
            txtDecsription.setText(mota);
            rdVHH.setSelected(voHieuHoa);
        }
    }//GEN-LAST:event_tbProductMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
// TODO add your handling code here:
        String search = txtSearch.getText();
        Connection connection = Connect_Database.connect();
        if (connection != null) {
            try {
                String query = "SELECT MAMH, TENMH, L.TENLOAI, GIABAN, DVT, MOTA, M.VOHIEUHOA FROM LOAI_MAT_HANG L, MAT_HANG M WHERE (M.MALOAI = L.MALOAI) AND (MAMH LIKE ? OR TENMH LIKE ? OR DVT LIKE ? OR TENLOAI LIKE ?)";
                PreparedStatement pst = connection.prepareStatement(query);

                // Thiết lập các tham số cho câu truy vấn
                pst.setString(1, "%" + search + "%");
                pst.setString(2, "%" + search + "%");
                pst.setString(3, "%" + search + "%");
                pst.setString(4, "%" + search + "%");

                // Thực thi truy vấn và lấy kết quả
                ResultSet rs = pst.executeQuery();

                // Hiển thị kết quả trong JTable
                DefaultTableModel model = (DefaultTableModel) tbProduct.getModel();
                model.setRowCount(0); // Xóa dữ liệu cũ trong JTable

                int count = 1;
                while (rs.next()) {
                    boolean voHieuHoa = rs.getBoolean("VOHIEUHOA");
                    String tenLoai = rs.getString("TENLOAI");
                    String maMH = rs.getString("MAMH");
                    String tenMH = rs.getString("TENMH");
                    double giaBan = rs.getDouble("GIABAN");
                    String dvt = rs.getString("DVT");
                    String mota = rs.getString("MOTA");
                    model.addRow(new Object[]{count, voHieuHoa, tenLoai, maMH, tenMH, giaBan, dvt, mota});
                    count++;
                }
              
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Khoog tìm thấy mặt hàng: " + ex.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            } finally {
                Connect_Database.close(connection);
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtSearchActionPerformed

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
            java.util.logging.Logger.getLogger(frmProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClicktoCategory;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbLMH;
    private javax.swing.JLabel lbTTMH;
    private javax.swing.JLabel lb_authentication;
    private javax.swing.JLabel lb_fullName;
    private javax.swing.JPanel panelInformationProduct;
    private javax.swing.JRadioButton rdVHH;
    private javax.swing.JTable tbProduct;
    private javax.swing.JTextField txtDVT;
    private javax.swing.JTextArea txtDecsription;
    private javax.swing.JTextField txtIdProduct;
    private javax.swing.JTextField txtNameProduct;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
