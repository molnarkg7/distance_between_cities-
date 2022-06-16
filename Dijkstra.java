package mestaEvropa;

public class Dijkstra {

	public String alg(int matrix[][], int pocetno, int krajnje) {
		int dimMatrice = matrix.length;
		
		int rastojanja[] = new int[dimMatrice];		
		
		Boolean found[] = new Boolean[dimMatrice];
		int parent[] = new int[dimMatrice];
		
		for(int i = 0; i < dimMatrice ; i++) {
			rastojanja[i] = Integer.MAX_VALUE;
			found[i] = false;
		}		
		
		rastojanja[pocetno] = 0; 
		
		parent[pocetno] = -1;
		
		for(int i = 0; i < dimMatrice - 1; i++) {
			int curr = najkraceRastojanje(rastojanja, found, dimMatrice);
			
			found[curr] = true;
			
			for(int j = 0; j < dimMatrice; j++) {
				if(!found[j] && matrix[curr][j] != 0 
							&& rastojanja[curr] != Integer.MAX_VALUE
							&& rastojanja[curr] + matrix[curr][j] < rastojanja[j]) {
					rastojanja[j] = rastojanja[curr] + matrix[curr][j];
					parent[j] = curr;
				}
			}
		}
		return putanja(rastojanja, krajnje, parent);
	}
	
	int najkraceRastojanje(int rastojanja[], Boolean found[], int dimMatrice) {
		int min = Integer.MAX_VALUE;
		int minNode = -1;
		
		for(int i = 0; i < dimMatrice; i++) {
			if(rastojanja[i] <= min && found[i] == false) {
				min = rastojanja[i];
				minNode = i;
			}
		}
		
		return minNode;
	}
	
	String putanja(int rastojanja[], int krajnje, int precursor[]) {
		return "Rastojanje izme\u0111u dva mesta je: " + rastojanja[krajnje] + "\nPutanja: " +
			stampanjePutanje(krajnje, precursor);
	}
	
	String stampanjePutanje(int node, int precursor[]) {
			String path = "";
			
			if(node == -1) 
				return "Start";
			else
				path += "=>" + node;
			
			return stampanjePutanje(precursor[node], precursor) + path;
	}
} 


