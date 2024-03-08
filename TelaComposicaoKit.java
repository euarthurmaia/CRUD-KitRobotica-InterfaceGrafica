import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class TelaComposicaoKit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textIDComposicaoKit;
	private JTextField textIDKitRoboticaComposicaoKit;
	private JTextField textIDComponenteComposicaoKit;
	private JTextField textQuantidadeComposicaoKit;
	private static int IDComposicaoKit = 1;
	static List<ComposicaoKit> ListaComposicaoKit = new ArrayList<ComposicaoKit>();
	static List<KitRobotica> ListaKitRobotica = TelaKitRobotica.getListaKitRobotica();
	static List<Componente> ListaComponente = TelaComponente.getListaComponente();
	private JTable tblComposicaoKit;

	public static List<ComposicaoKit> getListaComposicaoKit() {
		return ListaComposicaoKit;
	}

	public TelaComposicaoKit(JFrame telaAtual) {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				DefaultTableModel ModeloDados = (DefaultTableModel) tblComposicaoKit.getModel();
				for (int qtd = 0; qtd < ListaComposicaoKit.size(); qtd++) {
					ModeloDados.addRow(new Object[] { ListaComposicaoKit.get(qtd).getIDComposicaoKit(),
							ListaComposicaoKit.get(qtd).getIDKitRobotica(),
							ListaComposicaoKit.get(qtd).getIDComponente(),
							ListaComposicaoKit.get(qtd).getQuantidade() });
				}
			}
		});

		setTitle("Composição Kit");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblMensagem = new JLabel("");
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMensagem.setBounds(133, 238, 291, 14);
		getContentPane().add(lblMensagem);

		JLabel lblIDComposicaoKit = new JLabel("ID");
		lblIDComposicaoKit.setBounds(10, 25, 46, 14);
		getContentPane().add(lblIDComposicaoKit);

		textIDComposicaoKit = new JTextField();
		textIDComposicaoKit.setEditable(false);
		textIDComposicaoKit.setBounds(10, 39, 82, 20);
		getContentPane().add(textIDComposicaoKit);
		textIDComposicaoKit.setColumns(10);
		textIDComposicaoKit.setText(String.valueOf(IDComposicaoKit));

		JLabel lblIDKitRobComposicaoKit = new JLabel("ID Kit Robótica");
		lblIDKitRobComposicaoKit.setBounds(102, 25, 76, 14);
		getContentPane().add(lblIDKitRobComposicaoKit);

		textIDKitRoboticaComposicaoKit = new JTextField();
		textIDKitRoboticaComposicaoKit.setBounds(102, 39, 76, 20);
		getContentPane().add(textIDKitRoboticaComposicaoKit);
		textIDKitRoboticaComposicaoKit.setColumns(10);

		JLabel lblIDComponenteComposicaoKit = new JLabel("ID Componente");
		lblIDComponenteComposicaoKit.setBounds(10, 61, 81, 14);
		getContentPane().add(lblIDComponenteComposicaoKit);

		textIDComponenteComposicaoKit = new JTextField();
		textIDComponenteComposicaoKit.setBounds(10, 80, 81, 20);
		getContentPane().add(textIDComponenteComposicaoKit);
		textIDComponenteComposicaoKit.setColumns(10);

		JButton btnEditarComposicaoKit = new JButton("Editar");
		btnEditarComposicaoKit.setBounds(335, 38, 89, 23);
		btnEditarComposicaoKit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Linha = tblComposicaoKit.getSelectedRow();
				int ID = (int) Float.parseFloat(textIDComposicaoKit.getText());
				int IDKitRobotica = (int) Float.parseFloat(textIDKitRoboticaComposicaoKit.getText());
				int IDComponente = (int) Float.parseFloat(textIDComponenteComposicaoKit.getText());
				int Quantidade = (int) Float.parseFloat(textQuantidadeComposicaoKit.getText());
				DefaultTableModel ModeloDados = (DefaultTableModel) tblComposicaoKit.getModel();
				int idDesejadoKit = (int) Float.parseFloat(textIDKitRoboticaComposicaoKit.getText());
				int idDesejadoComponente = (int) Float.parseFloat(textIDComponenteComposicaoKit.getText());

				boolean idExisteKit = false;
				boolean idExisteComponente = false;
				for (KitRobotica Kit : ListaKitRobotica) {
					if (Kit.getIDKitRobotica() == idDesejadoKit) {
						idExisteKit = true;
						for (Componente Comp : ListaComponente) {
							if (Comp.getIDComponente() == idDesejadoComponente) {
								idExisteComponente = true;
								break;
							}
						}
					}
				}

				if (idExisteKit && idExisteComponente) {
					ModeloDados.setValueAt(ID, Linha, 0);
					ModeloDados.setValueAt(IDKitRobotica, Linha, 1);
					ModeloDados.setValueAt(IDComponente, Linha, 2);
					ModeloDados.setValueAt(Quantidade, Linha, 3);
					tblComposicaoKit.setModel(ModeloDados);
					ListaComposicaoKit.get(Linha).PreencheDados(ID, IDKitRobotica, IDComponente, Quantidade);

					textIDKitRoboticaComposicaoKit.setText("");
					textIDComponenteComposicaoKit.setText("");
					textQuantidadeComposicaoKit.setText("");
					textIDComposicaoKit.setText(String.valueOf(IDComponente));
					tblComposicaoKit.clearSelection();
				}
			}
		});
		getContentPane().add(btnEditarComposicaoKit);

		JButton btnExcluirComposicaoKit = new JButton("Excluir");
		btnExcluirComposicaoKit.setBounds(233, 38, 89, 23);
		btnExcluirComposicaoKit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Linha = tblComposicaoKit.getSelectedRow();

				DefaultTableModel ModeloDados = (DefaultTableModel) tblComposicaoKit.getModel();
				int ID = (int) ModeloDados.getValueAt(Linha, 0);
				int IDKitRobotica = (int) ModeloDados.getValueAt(Linha, 1);
				int IDComponente = (int) ModeloDados.getValueAt(Linha, 2);
				int Quantidade = (int) ModeloDados.getValueAt(Linha, 3);
				int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Confirmação",
						JOptionPane.YES_NO_OPTION);

				if (opcao == JOptionPane.YES_OPTION) {
					textIDComposicaoKit.setText(String.valueOf(ID));
					textIDKitRoboticaComposicaoKit.setText(String.valueOf(IDKitRobotica));
					textIDComponenteComposicaoKit.setText(String.valueOf(IDComponente));
					textQuantidadeComposicaoKit.setText(String.valueOf(Quantidade));

					ModeloDados.removeRow(Linha);
					ListaComposicaoKit.remove(Linha);

					textIDKitRoboticaComposicaoKit.setText("");
					textIDComponenteComposicaoKit.setText("");
					textQuantidadeComposicaoKit.setText("");
					textIDComposicaoKit.setText(String.valueOf(IDComposicaoKit));
				}
			}
		});
		getContentPane().add(btnExcluirComposicaoKit);

		DefaultTableModel ModeloDados = new DefaultTableModel();
		ModeloDados.addColumn("ID");
		ModeloDados.addColumn("ID KitRobotica");
		ModeloDados.addColumn("ID Componente");
		ModeloDados.addColumn("Quantidade");

		JButton btnAdicionarComposicaoKit = new JButton("Adicionar");
		btnAdicionarComposicaoKit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idDesejadoKit = (int) Float.parseFloat(textIDKitRoboticaComposicaoKit.getText());
				int idDesejadoComponente = (int) Float.parseFloat(textIDComponenteComposicaoKit.getText());

				boolean idExisteKit = false;
				boolean idExisteComponente = false;
				for (KitRobotica Kit : ListaKitRobotica) {
					if (Kit.getIDKitRobotica() == idDesejadoKit) {
						idExisteKit = true;
						for (Componente Comp : ListaComponente) {
							if (Comp.getIDComponente() == idDesejadoComponente) {
								idExisteComponente = true;
								break;
							}
						}
					}
				}

				if (idExisteKit && idExisteComponente) {

					if (!textIDKitRoboticaComposicaoKit.getText().equals("")
							&& !textIDComponenteComposicaoKit.getText().equals("")
							&& !textQuantidadeComposicaoKit.getText().equals("")) {
						ComposicaoKit CK1 = new ComposicaoKit();
						CK1.PreencheDados(IDComposicaoKit,
								(int) Float.parseFloat(textIDKitRoboticaComposicaoKit.getText()),
								(int) Float.parseFloat(textIDComponenteComposicaoKit.getText()),
								(int) Float.parseFloat(textQuantidadeComposicaoKit.getText()));
						ListaComposicaoKit.add(CK1);

						DefaultTableModel ModeloDados = (DefaultTableModel) tblComposicaoKit.getModel();
						ModeloDados.addRow(new Object[] { CK1.getIDComposicaoKit(), CK1.getIDKitRobotica(),
								CK1.getIDComponente(), CK1.getQuantidade() });

						textIDKitRoboticaComposicaoKit.setText("");
						textIDComponenteComposicaoKit.setText("");
						textQuantidadeComposicaoKit.setText("");
						IDComposicaoKit++;
						textIDComposicaoKit.setText(String.valueOf(IDComposicaoKit));
					}
				} else {
					lblMensagem.setText("É necessário existir um Kit e Componente para concluir!");
				}
			}
		});
		btnAdicionarComposicaoKit.setBounds(233, 80, 191, 20);
		getContentPane().add(btnAdicionarComposicaoKit);

		JScrollPane scrollPaneComposicaoKit = new JScrollPane();
		scrollPaneComposicaoKit.setBounds(10, 118, 414, 114);
		getContentPane().add(scrollPaneComposicaoKit);

		tblComposicaoKit = new JTable();
		tblComposicaoKit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int Linha = tblComposicaoKit.getSelectedRow();

				DefaultTableModel ModeloDados = (DefaultTableModel) tblComposicaoKit.getModel();
				int ID = (int) ModeloDados.getValueAt(Linha, 0);
				int IDKitRobotica = (int) ModeloDados.getValueAt(Linha, 1);
				int IDComponente = (int) ModeloDados.getValueAt(Linha, 2);
				int Quantidade = (int) ModeloDados.getValueAt(Linha, 3);

				textIDComposicaoKit.setText(String.valueOf(ID));
				textIDKitRoboticaComposicaoKit.setText(String.valueOf(IDKitRobotica));
				textIDComponenteComposicaoKit.setText(String.valueOf(IDComponente));
				textQuantidadeComposicaoKit.setText(String.valueOf(Quantidade));
			}
		});
		scrollPaneComposicaoKit.setViewportView(tblComposicaoKit);

		JFrame TelaAtual = this;

		JButton btnVoltarComposicaoKit = new JButton("<-- Voltar");
		btnVoltarComposicaoKit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal frame = new TelaPrincipal();
				frame.setVisible(true);
				TelaAtual.setVisible(false);
			}
		});
		btnVoltarComposicaoKit.setBounds(10, 234, 89, 23);
		getContentPane().add(btnVoltarComposicaoKit);

		JLabel lblQuantidadeComposicaoKit = new JLabel("Quantidade");
		lblQuantidadeComposicaoKit.setBounds(102, 61, 76, 14);
		getContentPane().add(lblQuantidadeComposicaoKit);

		textQuantidadeComposicaoKit = new JTextField();
		textQuantidadeComposicaoKit.setBounds(102, 80, 76, 20);
		getContentPane().add(textQuantidadeComposicaoKit);
		textQuantidadeComposicaoKit.setColumns(10);
		tblComposicaoKit.setModel(ModeloDados);

	}

}
