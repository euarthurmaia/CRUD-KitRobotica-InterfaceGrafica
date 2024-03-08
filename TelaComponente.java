import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TelaComponente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textIDComponente;
	private JTextField textDescComponente;
	private JTextField textPrecoComponente;
	private static int IDComponente = 1;
	private JTable tblComponente;
	static List<Componente> ListaComponente = new ArrayList<Componente>();
	static List<ComposicaoKit> ListaComposicaoKit = TelaComposicaoKit.getListaComposicaoKit();

	public static List<Componente> getListaComponente() {
		return ListaComponente;
	}

	public TelaComponente(JFrame telaAtual) {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				DefaultTableModel ModeloDados = (DefaultTableModel) tblComponente.getModel();
				for (int qtd = 0; qtd < ListaComponente.size(); qtd++) {
					ModeloDados.addRow(new Object[] { ListaComponente.get(qtd).getIDComponente(),
							ListaComponente.get(qtd).getDescricaoComponente(),
							ListaComponente.get(qtd).getPrecoComponente() });
				}
			}
		});

		setTitle("Componente");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JScrollPane scrollPaneComponente = new JScrollPane();
		scrollPaneComponente.setBounds(10, 118, 414, 114);
		getContentPane().add(scrollPaneComponente);

		tblComponente = new JTable();
		tblComponente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int Linha = tblComponente.getSelectedRow();

				DefaultTableModel ModeloDados = (DefaultTableModel) tblComponente.getModel();
				int ID = (int) ModeloDados.getValueAt(Linha, 0);
				String Descricao = (String) ModeloDados.getValueAt(Linha, 1);
				float Preco = (float) ModeloDados.getValueAt(Linha, 2);

				textIDComponente.setText(String.valueOf(ID));
				textDescComponente.setText(Descricao);
				textPrecoComponente.setText(String.valueOf(Preco));
			}
		});
		scrollPaneComponente.setViewportView(tblComponente);

		JLabel lblMensagem = new JLabel("");
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMensagem.setBounds(133, 238, 291, 14);
		getContentPane().add(lblMensagem);

		JLabel lblIDComponente = new JLabel("ID");
		lblIDComponente.setBounds(10, 25, 46, 14);
		getContentPane().add(lblIDComponente);

		textIDComponente = new JTextField();
		textIDComponente.setEditable(false);
		textIDComponente.setBounds(10, 39, 46, 20);
		getContentPane().add(textIDComponente);
		textIDComponente.setColumns(10);
		textIDComponente.setText(String.valueOf(IDComponente));

		JLabel lblDescComponente = new JLabel("Descrição");
		lblDescComponente.setBounds(66, 25, 121, 14);
		getContentPane().add(lblDescComponente);

		textDescComponente = new JTextField();
		textDescComponente.setBounds(66, 39, 157, 61);
		getContentPane().add(textDescComponente);
		textDescComponente.setColumns(10);

		JLabel lblPrecoComponente = new JLabel("Preço");
		lblPrecoComponente.setBounds(10, 61, 46, 14);
		getContentPane().add(lblPrecoComponente);

		DefaultTableModel ModeloDados = new DefaultTableModel();
		ModeloDados.addColumn("ID Kit Componente");
		ModeloDados.addColumn("Descrição");
		ModeloDados.addColumn("Preço");

		textPrecoComponente = new JTextField();
		textPrecoComponente.setBounds(10, 80, 46, 20);
		getContentPane().add(textPrecoComponente);
		textPrecoComponente.setColumns(10);

		JButton btnEditarComponente = new JButton("Editar");
		btnEditarComponente.setBounds(335, 38, 89, 23);
		btnEditarComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Linha = tblComponente.getSelectedRow();
				int ID = (int) Float.parseFloat(textIDComponente.getText());
				String Descricao = textDescComponente.getText();
				float Preco = (float) Float.parseFloat(textPrecoComponente.getText());
				DefaultTableModel ModeloDados = (DefaultTableModel) tblComponente.getModel();
				ModeloDados.setValueAt(ID, Linha, 0);
				ModeloDados.setValueAt(Descricao, Linha, 1);
				ModeloDados.setValueAt(Preco, Linha, 2);
				tblComponente.setModel(ModeloDados);
				ListaComponente.get(Linha).PreencheDados(ID, Descricao, Preco);

				textDescComponente.setText("");
				textPrecoComponente.setText("");
				textIDComponente.setText(String.valueOf(IDComponente));
				tblComponente.clearSelection();
			}
		});
		getContentPane().add(btnEditarComponente);

		JButton btnExcluirComponente = new JButton("Excluir");
		btnExcluirComponente.setBounds(233, 38, 89, 23);
		btnExcluirComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tblComponente.getSelectedRow();

				if (linha != -1) {
					DefaultTableModel modeloDados = (DefaultTableModel) tblComponente.getModel();
					int ID = (int) modeloDados.getValueAt(linha, 0);
					String descricao = (String) modeloDados.getValueAt(linha, 1);
					float preco = (float) modeloDados.getValueAt(linha, 2);

					int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Confirmação",
							JOptionPane.YES_NO_OPTION);

					if (opcao == JOptionPane.YES_OPTION) {
						textIDComponente.setText(String.valueOf(ID));
						textDescComponente.setText(descricao);
						textPrecoComponente.setText(String.valueOf(preco));

						modeloDados.removeRow(linha);
						ListaComponente.remove(linha);
						for (int j = ListaComposicaoKit.size() - 1; j >= 0; j--) {
							if (ListaComposicaoKit.get(j).getIDComponente() == ID) {
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

				textDescComponente.setText("");
				textPrecoComponente.setText("");
				textIDComponente.setText(String.valueOf(IDComponente));
			}
		});
		getContentPane().add(btnExcluirComponente);

		JButton btnAdicionarComponente = new JButton("Adicionar");
		btnAdicionarComponente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textIDComponente.getText().equals("") && !textDescComponente.getText().equals("")
						&& !textPrecoComponente.getText().equals("")) {
					Componente C1 = new Componente();
					C1.PreencheDados(IDComponente, textDescComponente.getText(),
							Float.parseFloat(textPrecoComponente.getText()));
					ListaComponente.add(C1);

					DefaultTableModel ModeloDados = (DefaultTableModel) tblComponente.getModel();
					ModeloDados.addRow(new Object[] { C1.getIDComponente(), C1.getDescricaoComponente(),
							C1.getPrecoComponente() });

					textDescComponente.setText("");
					textPrecoComponente.setText("");
					IDComponente++;
					textIDComponente.setText(String.valueOf(IDComponente));
				} else {
					lblMensagem.setText("É necessário preencher todos os campos!");
				}
			}
		});
		btnAdicionarComponente.setBounds(233, 80, 191, 20);
		getContentPane().add(btnAdicionarComponente);
		tblComponente.setModel(ModeloDados);

		JFrame TelaAtual = this;

		JButton btnVoltarComponente = new JButton("<-- Voltar");
		btnVoltarComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal frame = new TelaPrincipal();
				frame.setVisible(true);
				TelaAtual.setVisible(false);
			}
		});
		btnVoltarComponente.setBounds(10, 234, 89, 23);
		getContentPane().add(btnVoltarComponente);

	}
}
