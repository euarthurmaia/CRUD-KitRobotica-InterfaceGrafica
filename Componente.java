
public class Componente {
	private int IDComponente = 1;
	private String DescricaoComponente;
	private float PrecoComponente;
	
	public int getIDComponente() {
		return IDComponente;
	}
	public void setIDComponente(int iDComponente) {
		IDComponente = iDComponente;
	}
	public String getDescricaoComponente() {
		return DescricaoComponente;
	}
	public void setDescricaoComponente(String descricaoComponente) {
		DescricaoComponente = descricaoComponente;
	}
	public float getPrecoComponente() {
		return PrecoComponente;
	}
	public void setPrecoComponente(float precoComponente) {
		PrecoComponente = precoComponente;
	}
	
	public void PreencheDados(int idComponente, String descricaoComponente, float precoComponente) {
		IDComponente = idComponente;
		DescricaoComponente = descricaoComponente;
		PrecoComponente = precoComponente;
	}
}
