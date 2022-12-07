package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import Controladores.AltaUsuario;
import Controladores.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Formulario extends JFrame {

	private JPanel contentPane;
	private int id = 0;
	private JTextField txt_name;
	private JTextField txt_apellidos;
	private JTextField txt_mail;
	private JTextField txt_telefono;
	private JTextField txt_dir;
	private JComboBox cb_sexo;
	private String[] titulos = {"ID", "Nombre", "Apellidos", "Email", "Telefono", "Direccion", "Sexo"};
	private String[] datos=new String[7];
	private JTable table;
	private DefaultTableModel modelo;
	private JTextField txt_search;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formulario frame = new Formulario();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
			
	}

	/**
	 * Create the frame.
	 */
	public Formulario() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		configNombre();
		
		configApellidos();
		
		configEmail();
		
		configTelefono();
		
		configDireccion();
		
		configSexo();
		
		JButton btn_añadir = new JButton("Añadir");
		btn_añadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				insertBD();
				
			}
		});
		btn_añadir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_añadir.setBounds(10, 487, 89, 33);
		contentPane.add(btn_añadir);
		
		JButton btn_borrar = new JButton("Borrar");
		btn_borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarRegistro();
			}
		});
		btn_borrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_borrar.setBounds(109, 487, 89, 33);
		contentPane.add(btn_borrar);
		
		JButton btn_salir = new JButton("Salir");
		btn_salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn_salir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_salir.setBounds(430, 487, 75, 33);
		contentPane.add(btn_salir);
		
		
		JButton btn_borrar_1 = new JButton("Cargar");
		btn_borrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTabla();
			}
		});
		btn_borrar_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_borrar_1.setBounds(335, 487, 89, 33);
		contentPane.add(btn_borrar_1);
		
		txt_search = new JTextField();
		txt_search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				filtro(txt_search.getText(), table);
			}
		});
		txt_search.setColumns(10);
		txt_search.setBounds(10, 456, 495, 20);
		contentPane.add(txt_search);
		
		JLabel lblBuscarPorEmail = new JLabel("Buscar por Email");
		lblBuscarPorEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBuscarPorEmail.setBounds(10, 442, 155, 14);
		contentPane.add(lblBuscarPorEmail);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 233, 495, 205);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				
				String tblNombre = tblModel.getValueAt(table.getSelectedRow(), 1).toString();
				String tblApellidos = tblModel.getValueAt(table.getSelectedRow(), 2).toString();
				String email = tblModel.getValueAt(table.getSelectedRow(), 3).toString();
				String tblTelefono = tblModel.getValueAt(table.getSelectedRow(), 4).toString();
				String tblDireccion = tblModel.getValueAt(table.getSelectedRow(), 5).toString();
				String tblSexo = tblModel.getValueAt(table.getSelectedRow(), 6).toString();
				
				txt_name.setText(tblNombre);
				txt_apellidos.setText(tblApellidos);
				txt_mail.setText(email);
				txt_telefono.setText(tblTelefono);
				txt_dir.setText(tblDireccion);
				cb_sexo.setSelectedItem(tblSexo);
				
			}
		});
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Apellidos", "Email", "Telefono", "Direccion", "Sexo"
			}
		));
		
		JButton btn_borrar_2 = new JButton("Editar");
		btn_borrar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				modificarRegistro();
				
			}
		});
		btn_borrar_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_borrar_2.setBounds(208, 487, 89, 33);
		contentPane.add(btn_borrar_2);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(63);
		table.getColumnModel().getColumn(3).setPreferredWidth(58);
		table.getColumnModel().getColumn(4).setPreferredWidth(62);
		table.getColumnModel().getColumn(5).setPreferredWidth(62);		
		

	}

	
	private void configSexo() {
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSexo.setBounds(271, 178, 155, 14);
		contentPane.add(lblSexo);
		
		cb_sexo = new JComboBox();
		cb_sexo.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una opción", "Masculino", "Femenino"}));
		cb_sexo.setToolTipText("Seleccione un sexo");
		cb_sexo.setBounds(271, 201, 234, 22);
		contentPane.add(cb_sexo);
	}

	private void configDireccion() {
		JLabel lblDireccin = new JLabel("Dirección:");
		lblDireccin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDireccin.setBounds(10, 177, 155, 14);
		contentPane.add(lblDireccin);
		
		JLabel etiquetaErrDir = new JLabel("");
		etiquetaErrDir.setBounds(119, 177, 125, 14);
		contentPane.add(etiquetaErrDir);
		
		txt_dir = new JTextField();
		txt_dir.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if(txt_dir.getText().isEmpty()) {
					etiquetaErrDir.setForeground(Color.RED);
					etiquetaErrDir.setText("Obligatorio rellenar");
				}
				
			}
		});
		txt_dir.setColumns(10);
		txt_dir.setBounds(10, 202, 234, 20);
		contentPane.add(txt_dir);
	}

	private void configTelefono() {
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTelefono.setBounds(271, 124, 155, 14);
		contentPane.add(lblTelefono);
		
		JLabel etiquetaErrTelefono = new JLabel("");
		etiquetaErrTelefono.setBounds(397, 125, 108, 14);
		contentPane.add(etiquetaErrTelefono);
		
		txt_telefono = new JTextField();
		txt_telefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) ||(c == KeyEvent.VK_BACK_SPACE) ||(c == KeyEvent.VK_DELETE))) {
					etiquetaErrTelefono.setForeground(Color.RED);
					etiquetaErrTelefono.setText("Solo números");
					e.consume();
				}else if (txt_telefono.getText().length() > 10) {
					etiquetaErrTelefono.setForeground(Color.RED);
					etiquetaErrTelefono.setText("Maxímo alcanzado");
					e.consume();
				}else {
					etiquetaErrTelefono.setText("");
				}

				
			}
		});
		txt_telefono.setColumns(10);
		txt_telefono.setBounds(271, 146, 234, 20);
		contentPane.add(txt_telefono);
	}

	private void configEmail() {
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(10, 124, 155, 14);
		contentPane.add(lblEmail);
		
		JLabel etiquetaErrEmail = new JLabel("");
		etiquetaErrEmail.setBounds(119, 124, 125, 14);
		contentPane.add(etiquetaErrEmail);
		
		txt_mail = new JTextField();
		txt_mail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"); 
				// El email a validar
						String email = txt_mail.getText();
						Matcher mather = pattern.matcher(email);
						if (mather.find() == true) {
							etiquetaErrEmail.setForeground(Color.BLUE);
							etiquetaErrEmail.setText("Email correcto");
							} else {
								etiquetaErrEmail.setForeground(Color.RED);
								etiquetaErrEmail.setText("Email incorrecto");
								}
			}
		});
		txt_mail.setColumns(10);
		txt_mail.setBounds(10, 146, 234, 20);
		contentPane.add(txt_mail);
	}

	private void configApellidos() {
		JLabel lblApellidosDelContacto = new JLabel("Apellidos del Contacto:");
		lblApellidosDelContacto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblApellidosDelContacto.setBounds(10, 67, 155, 14);
		contentPane.add(lblApellidosDelContacto);
		
		JLabel etiquetaErrApellidos = new JLabel("");
		etiquetaErrApellidos.setBounds(294, 67, 211, 14);
		contentPane.add(etiquetaErrApellidos);
		
		txt_apellidos = new JTextField();
		txt_apellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				if(!Character.isLetter(event.getKeyChar()) && !(event.getKeyChar()==KeyEvent.VK_SPACE) && !(event.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
					event.consume();
					etiquetaErrApellidos.setForeground(Color.RED);
					etiquetaErrApellidos.setText("Escribe solo letras");
				}else {
					etiquetaErrApellidos.setText("");
				}
			}
		});
		txt_apellidos.setColumns(10);
		txt_apellidos.setBounds(10, 93, 495, 20);
		contentPane.add(txt_apellidos);
	}

	private void configNombre() {
		JLabel etiquetaErrNombre = new JLabel("");
		etiquetaErrNombre.setForeground(new Color(220, 20, 60));
		etiquetaErrNombre.setBounds(291, 12, 214, 14);
		contentPane.add(etiquetaErrNombre);
		
		JLabel lblNewLabel = new JLabel("Nombre del Contacto:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 11, 155, 14);
		contentPane.add(lblNewLabel);
		
		txt_name = new JTextField();
		txt_name.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent event) {
				if(!Character.isLetter(event.getKeyChar()) && !(event.getKeyChar()==KeyEvent.VK_SPACE) && !(event.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
					event.consume();
					etiquetaErrNombre.setForeground(Color.RED);
					etiquetaErrNombre.setText("Escribe solo letras");
				}else {
					etiquetaErrNombre.setText("");
				}
			}
		});
		txt_name.setBounds(10, 36, 495, 20);
		contentPane.add(txt_name);
		txt_name.setColumns(10);
	}
	
	private void insertBD() {
		Controladores.Conexion mysql = new Controladores.Conexion();
		Connection cn = mysql.conectar();
		String nombre,apellidos,direccion,email,sexo,tlf;
		int telefono;
		String sSQL ="";
		nombre=txt_name.getText();
		apellidos = txt_apellidos.getText();
		direccion = txt_dir.getText();
		email = txt_mail.getText();
		sexo = cb_sexo.getSelectedItem().toString();
		tlf = txt_telefono.getText();
		telefono = Integer.parseInt(tlf);
		sSQL = "INSERT INTO formulariocontacto (nombre,apellidos,email,telefono,direccion,sexo)"
				+ "VALUES(?,?,?,?,?,?)";
		
		try {
			PreparedStatement pst = cn.prepareStatement(sSQL);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setString(3, email);
			pst.setInt(4, telefono);
			pst.setString(5, direccion);
			pst.setString(6, sexo);
			
			
				
			if(comprobarUsuario()==true) {
				JOptionPane.showMessageDialog(null, "El usuario ya existe");
			}
			else {
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "El registro se ingresó correctamente");
				mostrarTabla();
				cn.close();
			}
			
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error " + ex);
		}
		
		
	}
	
	private void mostrarTabla() {	
			
			String sSQL ="";
			Conexion mysql = new Conexion();
			modelo = new DefaultTableModel(null, titulos);
			Connection cn = mysql.conectar();
			sSQL="SELECT id,nombre,apellidos,email,telefono,direccion,sexo FROM formulariocontacto";
			try {
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery(sSQL);
				
				while(rs.next()) {
					datos[0]=rs.getString("id");
					datos[1]=rs.getString("nombre");
					datos[2]=rs.getString("apellidos");
					datos[3]=rs.getString("email");
					datos[4]=rs.getString("telefono");
					datos[5]=rs.getString("direccion");
					datos[6]=rs.getString("sexo");
					modelo.addRow(datos);
				}
				table.setModel(modelo);
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error " + e, sSQL ,WIDTH);
			}
			
		}
	
	private void borrarRegistro() {
		int fila = table.getSelectedRow();
		String valor = table.getValueAt(fila, 0).toString();
		id = Integer.parseInt(valor);
		Conexion mysql = new Conexion();
		Connection cn = mysql.conectar();
		PreparedStatement pst;
		try {
			pst= cn.prepareStatement("DELETE FROM formulariocontacto WHERE id=" + id);
			pst.executeUpdate();
			mostrarTabla();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	private void modificarRegistro() {
		
		Conexion mysql = new Conexion();
		Connection cn = mysql.conectar();
		PreparedStatement pst;
		
		
		String nombre,apellidos,direccion,email,sexo,tlf;
		int telefono;
		int fila = table.getSelectedRow();
		String valor = table.getValueAt(fila, 0).toString();
		id = Integer.parseInt(valor);		
		nombre=txt_name.getText();
		apellidos = txt_apellidos.getText();
		direccion = txt_dir.getText();
		email = txt_mail.getText();
		sexo = cb_sexo.getSelectedItem().toString();
		tlf = txt_telefono.getText();
		telefono = Integer.parseInt(tlf);
		
		try {
			pst= cn.prepareStatement("UPDATE IGNORE formulariocontacto SET nombre='"+nombre+"', apellidos='"+apellidos+"', email='"+email+"', telefono="+telefono+", direccion='"+direccion+"', sexo='"+sexo+"' WHERE id="+id);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Registro actualizado correctamente");
			mostrarTabla();
			cn.close();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	private boolean comprobarUsuario() {
		
		boolean comprobar = false;
		String nombre, apellidos;
		String nombreBuscar = txt_name.getText();
		String apellidoBuscar = txt_apellidos.getText();
		
		String sSQL ="";
		Conexion mysql = new Conexion();
		modelo = new DefaultTableModel(null, titulos);
		Connection cn = mysql.conectar();
		sSQL="SELECT nombre,apellidos FROM formulariocontacto where nombre='"+nombreBuscar+"' AND apellidos='"+apellidoBuscar+"'";
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sSQL);
			
			if(rs.next()) {
				comprobar = true;
			}else {
				comprobar = false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
    
		return comprobar;

	}
	
	DefaultTableModel dm;
	
	private void filtro(String consulta, JTable jtableBuscar){
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta, 3));
}
}

	