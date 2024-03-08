import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaKitRobotica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textIDKitRobotica;
	private JTextField textDescKitRobotica;
	private JTextField textPrecoKitRobotica;
	private JTable tblKitRobotica;
	private static int IDKitRobotica = 1;
	static List<KitRobotica> ListaKitRobotica = new ArrayList<KitRobotica>();
	static List<ComposicaoKit> ListaComposicaoKit = TelaComposicaoKit.getListaComposicaoKit();

	public static List<KitRobotica> getListaKitRobotica() {
		return ListaKitRobotica;
	}

	public TelaKitRobotica(JFrame telaAtual) {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				DefaultTableModel ModeloDados = (DefaultTableModel) tblKitRobotica.getModel();
				for (int qtd = 0; qtd < ListaKitRobotica.size(); qtd++) {
					ModeloDados.addRow(new Object[] { ListaKitRobotica.get(qtd).getIDKitRobotica(),
							ListaKitRobotica.get(qtd).getDescricaoKitRobotica(),
							ListaKitRobotica.get(qtd).getPrecoKitRobotica() });
				}
			}
		});
		setTitle("Kit Robótica");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JScrollPane scrollPaneKitRobotica = new JScrollPane();
		scrollPaneKitRobotica.setBounds(10, 118, 414, 114);
		getContentPane().add(scrollPaneKitRobotica);

		tblKitRobotica = new JTable();
		tblKitRobotica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int Linha = tblKitRobotica.getSelectedRow();

				DefaultTableModel ModeloDados = (DefaultTableModel) tblKitRobotica.getModel();
				int ID = (int) ModeloDados.getValueAt(Linha, 0);
				String Descricao = (String) ModeloDados.getValueAt(Linha, 1);
				float Preco = (float) ModeloDados.getValueAt(Linha, 2);

				textIDKitRobotica.setText(String.valueOf(ID));
				textDescKitRobotica.setText(Descricao);
				textPrecoKitRobotica.setText(String.valueOf(Preco));
			}
		});
		scrollPaneKitRobotica.setViewportView(tblKitRobotica);

		JLabel lblMensagem = new JLabel("");
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMensagem.setBounds(133, 238, 291, 14);
		getContentPane().add(lblMensagem);

		JLabel lblIDKitRobotica = new JLabel("ID:");
		lblIDKitRobotica.setBounds(10, 25, 46, 14);
		getContentPane().add(lblIDKitRobotica);

		textIDKitRobotica = new JTextField();
		textIDKitRobotica.setEditable(false);
		textIDKitRobotica.setBounds(10, 39, 46, 20);
		getContentPane().add(textIDKitRobotica);
		textIDKitRobotica.setColumns(10);
		textIDKitRobotica.setText(String.valueOf(IDKitRobotica));

		JLabel lblDescKitRobotica = new JLabel("Descrição:");
		lblDescKitRobotica.setBounds(66, 25, 73, 14);
		getContentPane().add(lblDescKitRobotica);

		textDescKitRobotica = new JTextField();
		textDescKitRobotica.setBounds(66, 39, 157, 61);
		getContentPane().add(textDescKitRobotica);
		textDescKitRobotica.setColumns(10);

		JLabel lblPrecoKitRobotica = new JLabel("Preço:");
		lblPrecoKitRobotica.setBounds(10, 61, 46, 14);
		getContentPane().add(lblPrecoKitRobotica);

		DefaultTableModel ModeloDados = new DefaultTableModel();
		ModeloDados.addColumn("ID Kit Robótica");
		ModeloDados.addColumn("Descrição");
		ModeloDados.addColumn("Preço");

		textPrecoKitRobotica = new JTextField();
		textPrecoKitRobotica.setBounds(10, 80, 46, 20);
		getContentPane().add(textPrecoKitRobotica);
		textPrecoKitRobotica.setColumns(10);

		JButton btnEditarKitRobotica = new JButton("Editar");
		btnEditarKitRobotica.setBounds(335, 38, 89, 23);
		btnEditarKitRobotica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Linha = tblKitRobotica.getSelectedRow();
				int ID = (int) Float.parseFloat(textIDKitRobotica.getText());
				String Descricao = textDescKitRobotica.getText();
				float Preco = (float) Float.parseFloat(textPrecoKitRobotica.getText());
				DefaultTableModel ModeloDados = (DefaultTableModel) tblKitRobotica.getModel();
				ModeloDados.setValueAt(ID, Linha, 0);
				ModeloDados.setValueAt(Descricao, Linha, 1);
				ModeloDados.setValueAt(Preco, Linha, 2);
				tblKitRobotica.setModel(ModeloDados);
				ListaKitRobotica.get(Linha).PreencheDados(ID, Descricao, Preco);

				textDescKitRobotica.setText("");
				textPrecoKitRobotica.setText("");
				textIDKitRobotica.setText(String.valueOf(IDKitRobotica));
			}
		});
		getContentPane().add(btnEditarKitRobotica);

		JButton btnExcluirKitRobotica = new JButton("Excluir");
		btnExcluirKitRobotica.setBounds(233, 38, 89, 23);
		btnExcluirKitRobotica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tblKitRobotica.getSelectedRow();

				if (linha != -1) {
					DefaultTableModel modeloDados = (DefaultTableModel) tblKitRobotica.getModel();
					int ID = (int) modeloDados.getValueAt(linha, 0);
					String descricao = (String) modeloDados.getValueAt(linha, 1);
					float preco = (float) modeloDados.getValueAt(linha, 2);

					int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Confirmação", JOptionPane.YES_NO_OPTION);

					if (opcao == JOptionPane.YES_OPTION) {
						textIDKitRobotica.setText(String.valueOf(ID));
						textDescKitRobotica.setText(descricao);
						textPrecoKitRobotica.setText(String.valueOf(preco));

						modeloDados.removeRow(linha);
						ListaKitRobotica.remove(linha);
						for (int j = ListaComposicaoKit.size() - 1; j >= 0; j--) {
							if (ListaComposicaoKit.get(j).getIDKitRobotica() == ID) {
								ListaComposicaoKit.remove(j);
							}
						}
						for (int i = ListaComposicaoKit.size() - 1; i >= 0; i--) {
							if (ListaComposicaoKit.get(i).getIDComposicaoKit() == ID) {
								ListaComposicaoKit.remove(i);
							}
						}
					}
				}

				textDescKitRobotica.setText("");
				textPrecoKitRobotica.setText("");
				textIDKitRobotica.setText(String.valueOf(IDKitRobotica));

			}
		});
		getContentPane().add(btnExcluirKitRobotica);

		JButton btnAdicionarKitRobotica = new JButton("Adicionar");
		btnAdicionarKitRobotica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textDescKitRobotica.getText().equals("") && !textPrecoKitRobotica.getText().equals("")) {
					KitRobotica KR1 = new KitRobotica();
					KR1.PreencheDados(IDKitRobotica, textDescKitRobotica.getText(),
							Float.parseFloat(textPrecoKitRobotica.getText()));
					ListaKitRobotica.add(KR1);

					DefaultTableModel ModeloDados = (DefaultTableModel) tblKitRobotica.getModel();
					ModeloDados.addRow(new Object[] { KR1.getIDKitRobotica(), KR1.getDescricaoKitRobotica(),
							KR1.getPrecoKitRobotica() });

					textDescKitRobotica.setText("");
					textPrecoKitRobotica.setText("");
					IDKitRobotica++;
					textIDKitRobotica.setText(String.valueOf(IDKitRobotica));
				} else {
					lblMensagem.setText("É necessário preencher todos os campos!");
				}
			}
		});
		btnAdicionarKitRobotica.setBounds(233, 80, 191, 20);
		getContentPane().add(btnAdicionarKitRobotica);
		tblKitRobotica.setModel(ModeloDados);

		JFrame TelaAtual = this;

		JButton btnVoltarKitRobotica = new JButton("<-- Voltar");
		btnVoltarKitRobotica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal frame = new TelaPrincipal();
				frame.setVisible(true);
				TelaAtual.setVisible(false);
			}
		});
		btnVoltarKitRobotica.setBounds(10, 234, 89, 23);
		getContentPane().add(btnVoltarKitRobotica);

	}
}