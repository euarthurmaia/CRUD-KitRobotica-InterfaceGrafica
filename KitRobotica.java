public class KitRobotica {

	private int IDKitRobotica = 0;
	private String DescricaoKitRobotica;
	private float PrecoKitRobotica;
	
	public int getIDKitRobotica() {
		return IDKitRobotica;
	}

	public void setIDKitRobotica(int iDKitRobotica) {
		IDKitRobotica = iDKitRobotica;
	}

	public String getDescricaoKitRobotica() {
		return DescricaoKitRobotica;
	}

	public void setDescricaoKitRobotica(String descricaoKitRobotica) {
		DescricaoKitRobotica = descricaoKitRobotica;
	}

	public float getPrecoKitRobotica() {
		return PrecoKitRobotica;
	}

	public void setPrecoKitRobotica(float precoKitRobotica) {
		PrecoKitRobotica = precoKitRobotica;
	}

	public void PreencheDados(int iDKitRobotica, String descKitRobotica, float precoKitRobotica) {
		IDKitRobotica = iDKitRobotica;
		DescricaoKitRobotica = descKitRobotica;
		PrecoKitRobotica = precoKitRobotica;
	}
}