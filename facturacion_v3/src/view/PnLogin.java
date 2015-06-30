package view;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Font;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import dao.BDDUsuarios;

import java.awt.Color;

import javax.swing.border.MatteBorder;
import javax.swing.border.EmptyBorder;

import model.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class PnLogin extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TxfCorreo txtEmail;
	private JPasswordField txtPassword;
	private Usuario usuarioPanel;
	public JButton btnLogin;
	private JPanel pnLogin;

	public PnLogin(Usuario u) {
		this.usuarioPanel = u;
		Font fontTfLogin = new Font("tfLogin", UIManager.getFont("TextField.font").getStyle(), UIManager.getFont("TextField.font").getSize()+6);
		Font fontLblLogin = new Font("lblLogin", UIManager.getFont("Label.font").getStyle(), UIManager.getFont("Label.font").getSize()+8);
		
		setOpaque(false);
		setBounds(0, 0, 706, 533);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("300px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),
				FormFactory.MIN_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		pnLogin = new JPanel();
		pnLogin.setBackground(new Color(0, 0, 0, 50));
		add(pnLogin, "2, 2, fill, fill");
		pnLogin.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("20px"),
				ColumnSpec.decode("200px:grow"),
				ColumnSpec.decode("20px"),},
			new RowSpec[] {
				RowSpec.decode("30px"),
				RowSpec.decode("bottom:26px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:30px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("bottom:26px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:30px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:pref:grow"),
				RowSpec.decode("30px"),}));
		
		JLabel lblUsuariocorreo = new JLabel("Correo electr\u00F3nico");
		lblUsuariocorreo.setForeground(Color.WHITE);
		pnLogin.add(lblUsuariocorreo, "2, 2, fill, top");
		lblUsuariocorreo.setFont(fontLblLogin);
		
		txtEmail = new TxfCorreo();
		txtEmail.setFont(fontTfLogin);
		pnLogin.add(txtEmail, "2, 4, fill, fill");
		txtEmail.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setForeground(Color.WHITE);
		pnLogin.add(lblContrasea, "2, 6, fill, top");
		lblContrasea.setFont(fontLblLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(fontTfLogin);
		pnLogin.add(txtPassword, "2, 8, fill, fill");
		txtPassword.setColumns(10);
		
		btnLogin = new JButton("Entrar");
		pnLogin.add(btnLogin, "2, 10, fill, fill");
		btnLogin.setFont(new Font("Dialog", Font.PLAIN, 20));
	}

	public Usuario getUsuarioPanel() {
		return usuarioPanel;
	}

	public Usuario login() {
		Usuario user = new BDDUsuarios().login(txtEmail.getText(), new String(txtPassword.getPassword()));
		if (user!=null && user.getStatus()>0) {
			user.setLoged(true);
			return user;
		} else {
			return null;
		}
	}
}
