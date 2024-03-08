
public class ComposicaoKit {
	private int IDComposicaoKit = 1;
	private int IDKitRobotica;
	private int IDComponente;
	private int Quantidade;

	public int getIDComposicaoKit() {
		return IDComposicaoKit;
	}
	public void setIDComposicaoKit(int iDComposicaoKit) {
		IDComposicaoKit = iDComposicaoKit;
	}
	public int getIDKitRobotica() {
		return IDKitRobotica;
	}
	public void setIDKitRobotica(int iDKitRobotica) {
		IDKitRobotica = iDKitRobotica;
	}
	public int getIDComponente() {
		return IDComponente;
	}
	public void setIDComponente(int iDComponente) {
		IDComponente = iDComponente;
	}
	public int getQuantidade() {
		return Quantidade;
	}
	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}
	
	public void PreencheDados(int idComposicaoKit, int iDKitRobotica, int iDComponente, int quantidade) {
		IDComposicaoKit = idComposicaoKit;
		IDKitRobotica = iDKitRobotica;
		IDComponente = iDComponente;
		Quantidade = quantidade;
	}
}
