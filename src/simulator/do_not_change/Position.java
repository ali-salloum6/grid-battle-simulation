package simulator.do_not_change;

public class Position {
	public int row;
	public int column;

	public Position(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	/**
	 * Return the Euclidian distance between this position and another object of
	 * Position
	 * 
	 * @param other
	 * @return
	 */
	public double euclidianDistance(Position other) {
		return Math.sqrt(Math.pow(this.row - other.row, 2) + Math.pow(this.column - other.column, 2));
	}

	/**
	 * Return the Manhattan distance between this position and another object of
	 * Position
	 * 
	 * @param other
	 * @return
	 */
	public int manhattanDistance(Position other) {
		return Math.abs(this.row - other.row) + Math.abs(this.column - other.column);
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int hash = 1;
		hash = prime * hash + Integer.hashCode(row);
		hash = prime * hash + Integer.hashCode(column);
		return hash;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Position position = (Position) o;
		return row == position.row && column == position.column;
	}
}
