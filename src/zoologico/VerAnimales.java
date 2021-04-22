package zoologico;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.sql.*;
public class VerAnimales extends JFrame  {
	public JPanel panel;
	public JLabel Tigre;
	public JLabel Tortuga;
	public JLabel Canguro;
	public JLabel Tiburon;
	public JLabel Hipopotamo;
	public JLabel Cocodrilo;
	public JLabel Mono;
	public JLabel Jirafa;
	public JLabel Rinoceronte;
	public JLabel Oso;
	public JLabel Avestruz;
	public JLabel Leon;
	public JLabel Elefante;
	public JLabel Puercoespin;
	public JLabel Pinguino;
	public JTextField IngresarAnimal;
	public JTextField ClaveAnimal;
	public JLabel AnimalIntroducido;
	public JLabel AnimalCarne;
	public JLabel AnimalPlanta;
	public JLabel AnimalDeTodo;
	public String dbName = "Register";
	public String URL = "jdbc:postgresql://localhost:5432/Animal";
	public String USER = "postgres";
	public String PASSWORD = "";
	PreparedStatement ps;
	ResultSet res;
	//
	public JTextField ingresoMensaje;
    public JTextArea pantallaChat;
    public JMenuItem adjuntar;
    private static ServerSocket servidor;
    private static Socket cliente;
    private static String ipServidor;// = "127.0.0.1";
    public static Cliente ventanaCliente;
    public static String usuario;
    public boolean recibir;
	



public VerAnimales() {
		this.setTitle("Animales");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		IniciarAnimales();
		this.setLocationRelativeTo(null);

	}
public Connection getConection() {
	Connection con = null;
	try {
		
		Class.forName("org.postgresql.Driver");
		con = (Connection) DriverManager.getConnection(URL,USER,PASSWORD);
		System.out.println("conexion exitosa");
		JOptionPane.showMessageDialog(null,"conexion exitosa");
		
	}catch(Exception e){
		System.out.println(e.getMessage());
	}return con;
 }
	private void LimpiarCajas() {
		ClaveAnimal.setText(null);
		IngresarAnimal.setText(null);
		
	}
	public void IniciarAnimales(){
		ColocarAnimales();
		ColocarCosas();
		TodosLosAnimales();
		AnimalNuevo();
		ColocarAnimal();
		Boton2();
		Boton3();
		BotonEliminar();
		BotonModificar();
		BotonBuscar();
		ClaveAnimal();
		ListaAnimales();
	}
	public void ColocarAnimales()  {
	panel = new JPanel();
	panel.setLayout(null);
	this.getContentPane().add(panel);
	}
	public void ColocarCosas() {
	JLabel animales = new JLabel();
	animales.setText("ZOOLOGICO");
	animales.setBounds(440,20,1000,50);
	animales.setForeground(Color.GREEN);
	animales.setFont(new Font("arial",1,20));
	panel.add(animales);
	}
	public void TodosLosAnimales() {
		JButton boton1 = new JButton();
		boton1.setText("Lista de Animales");
		boton1.setEnabled(false);
		boton1.setBounds(100, 100, 200, 40);
		
		panel.add(boton1);
		JLabel AnimalNuevoRepetido = new JLabel();
		AnimalNuevoRepetido.setBounds(100, 215, 100, 200);
		panel.add(AnimalNuevoRepetido);
		
		ActionListener Boton01 = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
					AnimalNuevoRepetido.setText(""+IngresarAnimal.getText());
					try {
					Connection con =null;
					con =  getConection();
					
					ps= con.prepareStatement("Select * FROM Animales");
					res= ps.executeQuery();
					if(res.next()) {
						JOptionPane.showMessageDialog(null, res.getString("Animal"));
					}else {
						JOptionPane.showMessageDialog(null,"NO EXISTEN DATOS");
						}
					con.close();
					}catch(Exception a) {
						System.out.println(a);
					}
					}
			
		};
		boton1.addActionListener(Boton01);
	}
	public void AnimalNuevo() {
		JLabel	AnimalN = new JLabel();
		AnimalN.setBounds(450, 60, 100, 30);
		AnimalN.setText("Ingrese el Animal");
		panel.add(AnimalN);
	}		
	public void Boton2() {
		JButton Boton02 = new JButton();
		Boton02.setBounds(400, 240, 200, 40);
		Boton02.setText("Guardar");
		
		AnimalIntroducido = new JLabel();
		AnimalIntroducido.setBounds(400, 200, 250, 30);
		panel.add(AnimalIntroducido);
		panel.add(Boton02);
		JLabel AnimalRepetido2 = new JLabel();
		AnimalRepetido2.setBounds(100, 215, 100, 200);
		panel.add(AnimalRepetido2);
		
		
		
		ActionListener AgregarAnimal = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
					con = getConection();
					ps= con.prepareStatement("INSERT INTO Animales (Animal,Id) VALUES(?,?)");
					ps.setString(1,IngresarAnimal.getText());
					ps.setString(2, ClaveAnimal.getText());
					int res= ps.executeUpdate();
					if(res>0) {
						JOptionPane.showMessageDialog(null, "Animal Guardado");
						LimpiarCajas();
					}else {
						JOptionPane.showMessageDialog(null, "Error al Guardar");
						LimpiarCajas();
					}
					con.close();
				}catch(Exception c) {
					System.err.print(c);
					
				}
				
			}
			
		};
		Boton02.addActionListener(AgregarAnimal);
	}
	public void ColocarAnimal() {
		IngresarAnimal = new JTextField();
		IngresarAnimal.setBounds(400, 100, 200, 40);
		panel.add(IngresarAnimal);
		
	}
	public void Boton3() {

		JButton Boton03 = new JButton();
		Boton03.setBounds(700, 100, 280, 40);
		Boton03.setText("Comportamientos de los Animales");
		panel.add(Boton03);
		AnimalCarne= new JLabel();
		AnimalCarne.setBounds(790, 250, 100, 40);
		panel.add(AnimalCarne);
		AnimalPlanta = new JLabel();
		AnimalPlanta.setBounds(790,230, 100, 40);
		panel.add(AnimalPlanta);
		AnimalDeTodo = new JLabel();
		AnimalDeTodo.setBounds(790,200,100,40);
		panel.add(AnimalDeTodo);
		
		
		ActionListener CompAnimales = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnimalCarne.setText("Animales que comen carne");
				AnimalPlanta.setText("Animales que comen planta");
				AnimalDeTodo.setText("Animales que comen de todo");
				
				
			}
			
		};
		Boton03.addActionListener(CompAnimales);
		
	
	}

	public void BotonEliminar() {
		JButton BotonEliminar = new JButton();
		BotonEliminar.setBounds(400, 340, 200, 40);
		BotonEliminar.setText("Eliminar");
		panel.add(BotonEliminar);
		
		ActionListener BotonEliminarAcc = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
					con = getConection();
					ps= con.prepareStatement("DELETE FROM Animales WHERE Id=?");
					ps.setString(1,ClaveAnimal.getText());
					int res= ps.executeUpdate();
					if(res>0) {
						JOptionPane.showMessageDialog(null, "Animal Borrado");
						LimpiarCajas();
					}else {
						JOptionPane.showMessageDialog(null, "Error Al Borrar");
						LimpiarCajas();
					}
					con.close();
				}catch(Exception c) {
					System.err.print(c);
					
				}
				
			}
			
		};
		BotonEliminar.addActionListener(BotonEliminarAcc);
		
	}
	public void BotonModificar() {
		JButton BotonModificar = new JButton();
		BotonModificar.setBounds(400, 440, 200, 40);
		BotonModificar.setText("Modificar");
		panel.add(BotonModificar);
		ActionListener BotonModificarAcc = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
					con = getConection();
					ps= con.prepareStatement("UPDATE Animales SET Animal=? WHERE Id=?");
					ps.setString(1,IngresarAnimal.getText());
					ps.setString(2,ClaveAnimal.getText());
					int res= ps.executeUpdate();
					if(res>0) {
						JOptionPane.showMessageDialog(null, "Animal Modificado");
						LimpiarCajas();
					}else {
						JOptionPane.showMessageDialog(null, "Error al Modificar");
						LimpiarCajas();
					}
					con.close();
				}catch(Exception c) {
					System.err.print(c);
					
				}
				
			}
			
		};
		BotonModificar.addActionListener(BotonModificarAcc);
	}
	public void BotonBuscar() {
		JButton BotonBuscar = new JButton();
		BotonBuscar.setBounds(400, 540, 200, 40);
		BotonBuscar.setText("Buscar");
		panel.add(BotonBuscar);
		
		ActionListener BotonBuscarAcc = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
					con=getConection();
					ps= con.prepareStatement("Select * From Animales WHERE Id = ?");
					ps.setString(1,ClaveAnimal.getText() );
					res = ps.executeQuery();
					if(res.next()) {
						ClaveAnimal.setText(res.getString("id"));
						IngresarAnimal.setText(res.getString("Animal"));
					}else {
						JOptionPane.showMessageDialog(null, "No existe ese animal en este zoologico");
					}
				}catch(Exception d){
					System.err.print(d);
				}
				
				
			}
			
		};
		BotonBuscar.addActionListener(BotonBuscarAcc);
		
	}
	public void ClaveAnimal() {
		ClaveAnimal = new JTextField();
		JLabel ID = new JLabel();
		ClaveAnimal.setBounds(400,175, 200, 40);
		panel.add(ClaveAnimal);
		ID.setBounds(500, 110, 100, 100);
		ID.setText("ID");
		panel.add(ID);
	}

	public void ListaAnimales() {
		JLabel Lista = new JLabel();
		Lista.setBounds(175, 105, 100, 100);
		Lista.setText("ID/Animales/Tipo ");
		panel.add(Lista);
		Cocodrilo = new JLabel();
		Cocodrilo.setBounds(100, 110, 100, 200);
		Cocodrilo.setText("1/Tigre/Carnivoro");
		Tortuga = new JLabel();
		Tortuga.setBounds(100, 125, 100, 200);
		Tortuga.setText("2/Tortuga/Herviboro");
		Canguro = new JLabel();
		Canguro.setBounds(100, 140, 100, 200);
		Canguro.setText("3/Canguro/Herviboro");
		Pinguino = new JLabel();
		Pinguino.setBounds(100, 155, 100, 200);
		Pinguino.setText("4/Tiburon/Carnivoro");
		Hipopotamo = new JLabel();
		Hipopotamo.setBounds(100, 170, 100, 200);
		Hipopotamo.setText("5/Hipopotamo/Herviboro");
		Tortuga = new JLabel();
		Tortuga.setText("6/Cocodrilo/Carnivoro");
		Tortuga.setBounds(100, 185, 100, 200);
		Mono = new JLabel();
		Mono.setText("7/Mono/Omnivoro");
		Mono.setBounds(100, 200, 100, 200);
		Oso = new JLabel();
		Oso.setText("8/Jirafa/Herviboro");
		Oso.setBounds(250, 110, 100, 200);
		Tigre = new JLabel();
		Tigre.setText("9/Rinoceronte/Herviboro");
		Tigre.setBounds(250, 125, 100, 200);
		Leon = new JLabel();
		Leon.setText("10/Oso/Carnivoro");
		Leon.setBounds(250, 140, 100, 200);
		Jirafa = new JLabel();
		Jirafa.setText("11/Avestruz/Omnivoro");
		Jirafa.setBounds(250, 155, 100, 200);
		Leon = new JLabel();
		Leon.setText("12/Leon/Carnivoro");
		Leon.setBounds(250, 170, 100, 200);
		Elefante = new JLabel();
		Elefante.setText("13/Elefante/Herviboro");
		Elefante.setBounds(250, 185, 100, 200);
		Puercoespin = new JLabel();
		Puercoespin.setText("14/Puercoespin/Herviboro");
		Puercoespin.setBounds(250, 200, 100, 200);
		Pinguino = new JLabel();
		Pinguino.setText("15/Pinguino/Carnivoro");
		Pinguino.setBounds(250, 215, 100, 200);
		panel.add(Tigre); //1
		panel.add(Tortuga);
		panel.add(Canguro);
		panel.add(Tiburon);
		panel.add(Hipopotamo);
		panel.add(Cocodrilo);
		panel.add(Mono);
		panel.add(Jirafa);
		panel.add(Rinoceronte);
		panel.add(Oso);
		panel.add(Avestruz);
		panel.add(Leon);
		panel.add(Elefante);
		panel.add(Puercoespin);
		panel.add(Pinguino);
	
	}	
	}
	
	 