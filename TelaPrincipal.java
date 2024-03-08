import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		setResizable(false);
		setTitle("Tela Inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JFrame TelaAtual = this;
		
		JButton btnRobotica = new JButton("Kit Robótica");
		btnRobotica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaKitRobotica frame = new TelaKitRobotica(TelaAtual);
				frame.setVisible(true);
				TelaAtual.setVisible(false);
			}
		});
		btnRobotica.setBounds(139, 90, 156, 23);
		contentPane.add(btnRobotica);
		
		
		JButton btnComponente = new JButton("Componente");
		btnComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaComponente frame = new TelaComponente(TelaAtual);
				frame.setVisible(true);
				TelaAtual.setVisible(false);
			}
		});
		btnComponente.setBounds(139, 133, 156, 23);
		contentPane.add(btnComponente);
		
		JButton btnComposicaoDeKit = new JButton("Composição de Kit");
		btnComposicaoDeKit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaComposicaoKit frame = new TelaComposicaoKit(TelaAtual);
				frame.setVisible(true);
				TelaAtual.setVisible(false);
			}
		});
		btnComposicaoDeKit.setBounds(139, 175, 156, 23);
		contentPane.add(btnComposicaoDeKit);
		
		JLabel lblTelaInicial = new JLabel("Gerenciamento");
		lblTelaInicial.setForeground(new Color(0, 0, 0));
		lblTelaInicial.setFont(new Font("Ebrima", Font.BOLD, 22));
		lblTelaInicial.setBounds(139, 23, 196, 45);
		contentPane.add(lblTelaInicial);
		
		JButton btnFecharPrograma = new JButton("Encerrar");
		btnFecharPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnFecharPrograma.setBounds(139, 216, 156, 23);
		contentPane.add(btnFecharPrograma);
	}
}
