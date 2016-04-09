package graph;

/**
 * @author Sudharaka Palamakumbura
 * 
 * Represents a Vertex/Node in the Graph.
 *
 */
public class Vertex {
	
	private final int value;
	
	/**
	 * Constructor for creating a vertex.
	 * 
	 * @param a
	 */
	public Vertex(int a){
		this.value = a;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Integer.toString(value);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (value != other.value)
			return false;
		return true;
	}
}
