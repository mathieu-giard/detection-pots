package pactutils;

public class SignatureCarre extends Signature {
	public SignatureCarre(){
		super();
		
		for(int i = -500; i < 500; i++){
			this.add(500,i);
			this.add(i,500);
			this.add(-500,i);
			this.add(-500,i);
		}
		
		//this.normalise();
	}
	
	private void add(int x, int y){
		super.add(x,y,0,0);
	}
}
