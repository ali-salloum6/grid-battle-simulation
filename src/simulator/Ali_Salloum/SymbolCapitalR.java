package simulator.Ali_Salloum;

import java.util.LinkedList;
import java.util.Random;
import simulator.do_not_change.*;

/**
 * A kind of the symbols in the simulation. it implements the interfaces
 * Aggressive and CapitalCase and override their methods: move(), die(),
 * attackSmart(), jump(). And it extends the class
 * {@code simulator.do_not_change.Symbol}.
 * 
 * @author Ali Salloum
 * @see simulator.Simulator, simulator.Symbol
 */
public class SymbolCapitalR extends Symbol implements Aggressive, CapitalCase {

	/**
	 * A default constructor without parameters.  
	 */
	public SymbolCapitalR() {
		this.idSymbol = COUNT_SYMBOLS;
		Symbol.COUNT_SYMBOLS++;
		this.position = null;
		this.numberIterationsAlive = 0;
		this.sightDistance = 0;
	}
	
	/** A constructor with 3 parameters.
	 * @param idSymbol
	 * @param numberIterationsAlive
	 * @param sightDistance
	 */
	public SymbolCapitalR(int idSymbol, int numberIterationsAlive, int sightDistance) {
		this.idSymbol = idSymbol;
		Symbol.COUNT_SYMBOLS++;
		this.position = null;
		this.numberIterationsAlive = numberIterationsAlive;
		this.sightDistance = sightDistance;
	}
	
	/**
	 * Initialize a SymbolCapitalR.
	 * 
	 * @param idSymbol
	 * @param position
	 * @param numberIterationsAlive
	 * @param sightDistance
	 */
	public SymbolCapitalR(int idSymbol, Position position, int numberIterationsAlive, int sightDistance) {
		this.idSymbol = idSymbol;
		Symbol.COUNT_SYMBOLS++;
		this.position = position;
		this.numberIterationsAlive = numberIterationsAlive;
		this.sightDistance = sightDistance;
	}

	/**
	 * A constructor that initialize the position and the numberIterationsAlive
	 * given as parameters. And it gives the symbol a unique id and a sight distance
	 * that is common for all the symbols in the simulation. <br>
	 * <br>
	 * It is used when a small case of this kind is upgraded so the number of
	 * iterations alive don't get reset but is kept as the small case number.
	 * 
	 * @param position
	 * @param numberIterationsAlive
	 */
	public SymbolCapitalR(Position position, int numberIterationsAlive) {
		this.idSymbol = Symbol.COUNT_SYMBOLS;
		Symbol.COUNT_SYMBOLS++;
		this.position = position;
		this.numberIterationsAlive = numberIterationsAlive;
		this.sightDistance = 4;
	}

	/**
	 * A constructor with a position parameter and a numberIterationsAlive (0) and a
	 * constant sight distance (4) and idSymbol set to COUNT_SYMBOLS
	 * 
	 * @param position
	 */
	public SymbolCapitalR(Position position) {
		this.idSymbol = Symbol.COUNT_SYMBOLS;
		Symbol.COUNT_SYMBOLS++;
		this.position = position;
		this.numberIterationsAlive = 0;
		this.sightDistance = 4;
	}

	/**
	 * Move randomly within the sight distance. The sight distance is measured using
	 * the Manhattan distance.
	 */
	@Override
	public void move() {
		Random random = new Random();
		int x = this.position.row, y = this.position.column;
		for (int i = 0; i < this.sightDistance; i++) {
			int temp = random.nextInt(4);
			switch (temp) {
			case 0:
				if (x < Simulator.MAX_ROWS - 1) // to not get out of bounds
					x++;
				break;
			case 1:
				if (x > 0)
					x--;
				break;
			case 2:
				if (y < Simulator.MAX_COLS - 1)
					y++;
				break;
			case 3:
				if (y > 0)
					y--;
				break;
			}
		}
		Simulator.world.get(this.position).remove(this);
		Position newPosition = new Position(x, y);
		this.setPosition(newPosition);
		Simulator.world.get(newPosition).add(this);
	}

	/**
	 * Just remove the symbol from the hash map {@code world}.
	 */
	@Override
	public void die() {
		Simulator.world.get(this.position).remove(this);
	}

	/**
	 * Move to a symbol weaker than you in your sight distance.
	 */
	@Override
	public void attackSmart() {
		for (Symbol symbol : getMainList()) {
			if (symbol instanceof SymbolCapitalS || symbol instanceof SymbolSmallS) {
				if (this.position.manhattanDistance(symbol.getPosition()) <= this.sightDistance) {
					Simulator.world.get(this.getPosition()).remove(this);
					this.setPosition(symbol.getPosition());
					Simulator.world.get(symbol.getPosition()).add(this);
					break;
				}
			}
		}
	}

	/**
	 * Jump to any position in the world regardless if it's within your sight
	 * distance or not.
	 */
	@Override
	public void jump() {
		Random random = new Random();
		int i = random.nextInt(10);
		int j = random.nextInt(10);
		Position newPosition = new Position(i, j);
		Simulator.world.get(this.getPosition()).remove(this);
		this.setPosition(newPosition);
		Simulator.world.get(newPosition).add(this);
	}

	/**
	 * @return list a linked list that contains all the symbols in the hash map
	 *         {@code world}.
	 */
	public static LinkedList<Symbol> getMainList() {
		LinkedList<Symbol> list = new LinkedList<Symbol>();
		for (int i = 0; i < WorldController.MAX_ROWS; i++) {
			for (int j = 0; j < WorldController.MAX_COLS; j++) {
				Position position = new Position(i, j);
				for (Symbol symbol : Simulator.world.get(position)) {
					list.add(symbol);
				}
			}
		}
		return list;
	}

}
                                                                                                                                                                                                                                                                                                                                                                                                                              package simulator.Ali_Salloum;

import java.util.LinkedList;
import java.util.Random;
import simulator.do_not_change.*;

/**
 * A kind of the symbols in the simulation. it implements the interfaces
 * Aggressive and CapitalCase and override their methods: move(), die(),
 * attackSmart(), jump(). And it extends the class
 * {@code simulator.do_not_change.Symbol}.
 * 
 * @author Ali Salloum
 * @see simulator.Simulator, simulator.Symbol
 */
public class SymbolCapitalR extends Symbol implements Aggressive, CapitalCase {

	/**
	 * A default constructor without parameters.  
	 */
	public SymbolCapitalR() {
		this.idSymbol = COUNT_SYMBOLS;
		Symbol.COUNT_SYMBOLS++;
		this.position = null;
		this.numberIterationsAlive = 0;
		this.sightDistance = 0;
	}
	
	/** A constructor with 3 parameters.
	 * @param idSymbol
	 * @param numberIterationsAlive
	 * @param sightDistance
	 */
	public SymbolCapitalR(int idSymbol, int numberIterationsAlive, int sightDistance) {
		this.idSymbol = idSymbol;
		Symbol.COUNT_SYMBOLS++;
		this.position = null;
		this.numberIterationsAlive = numberIterationsAlive;
		this.sightDistance = sightDistance;
	}
	
	/**
	 * Initialize a SymbolCapitalR.
	 * 
	 * @param idSymbol
	 * @param position
	 * @param numberIterationsAlive
	 * @param sightDistance
	 */
	public SymbolCapitalR(int idSymbol, Position position, int numberIterationsAlive, int sightDistance) {
		this.idSymbol = idSymbol;
		Symbol.COUNT_SYMBOLS++;
		this.position = position;
		this.numberIterationsAlive = numberIterationsAlive;
		this.sightDistance = sightDistance;
	}

	/**
	 * A constructor that initialize the position and the numberIterationsAlive
	 * given as parameters. And it gives the symbol a unique id and a sight distance
	 * that is common for all the symbols in the simulation. <br>
	 * <br>
	 * It is used when a small case of this kind is upgraded so the number of
	 * iterations alive don't get reset but is kept as the small case number.
	 * 
	 * @param position
	 * @param numberIterationsAlive
	 */
	public SymbolCapitalR(Position position, int numberIterationsAlive) {
		this.idSymbol = Symbol.COUNT_SYMBOLS;
		Symbol.COUNT_SYMBOLS++;
		this.position = position;
		this.numberIterationsAlive = numberIterationsAlive;
		this.sightDistance = 4;
	}

	/**
	 * A constructor with a position parameter and a numberIterationsAlive (0) and a
	 * constant sight distance (4) and idSymbol set to COUNT_SYMBOLS
	 * 
	 * @param position
	 */
	public SymbolCapitalR(Position position) {
		this.idSymbol = Symbol.COUNT_SYMBOLS;
		Symbol.COUNT_SYMBOLS++;
		this.position = position;
		this.numberIterationsAlive = 0;
		this.sightDistance = 4;
	}

	/**
	 * Move randomly within the sight distance. The sight distance is measured using
	 * the Manhattan distance.
	 */
	@Override
	public void move() {
		Random random = new Random();
		int x = this.position.row, y = this.position.column;
		for (int i = 0; i < this.sightDistance; i++) {
			int temp = random.nextInt(4);
			switch (temp) {
			case 0:
				if (x < Simulator.MAX_ROWS - 1) // to not get out of bounds
					x++;
				break;
			case 1:
				if (x > 0)
					x--;
				break;
			case 2:
				if (y < Simulator.MAX_COLS - 1)
					y++;
				break;
			case 3:
				if (y > 0)
					y--;
				break;
			}
		}
		Simulator.world.get(this.position).remove(this);
		Position newPosition = new Position(x, y);
		this.setPosition(newPosition);
		Simulator.world.get(newPosition).add(this);
	}

	/**
	 * Just remove the symbol from the hash map {@code world}.
	 */
	@Override
	public void die() {
		Simulator.world.get(this.position).remove(this);
	}

	/**
	 * Move to a symbol weaker than you in your sight distance.
	 */
	@Override
	public void attackSmart() {
		for (Symbol symbol : getMainList()) {
			if (symbol instanceof SymbolCapitalS || symbol instanceof SymbolSmallS) {
				if (this.position.manhattanDistance(symbol.getPosition()) <= this.sightDistance) {
					Simulator.world.get(this.getPosition()).remove(this);
					this.setPosition(symbol.getPosition());
					Simulator.world.get(symbol.getPosition()).add(this);
					break;
				}
			}
		}
	}

	/**
	 * Jump to any position in the world regardless if it's within your sight
	 * distance or not.
	 */
	@Override
	public void jump() {
		Random random = new Random();
		int i = random.nextInt(10);
		int j = random.nextInt(10);
		Position newPosition = new Position(i, j);
		Simulator.world.get(this.getPosition()).remove(this);
		this.setPosition(newPosition);
		Simulator.world.get(newPosition).add(this);
	}

	/**
	 * @return list a linked list that contains all the symbols in the hash map
	 *         {@code world}.
	 */
	public static LinkedList<Symbol> getMainList() {
		LinkedList<Symbol> list = new LinkedList<Symbol>();
		for (int i = 0; i < WorldController.MAX_ROWS; i++) {
			for (int j = 0; j < WorldController.MAX_COLS; j++) {
				Position position = new Position(i, j);
				for (Symbol symbol : Simulator.world.get(position)) {
					list.add(symbol);
				}
			}
		}
		return list;
	}

}
