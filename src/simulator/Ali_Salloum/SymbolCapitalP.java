package simulator.Ali_Salloum;

import java.util.LinkedList;
import java.util.Random;
import simulator.do_not_change.*;

/**
 * A kind of the symbols in the simulation. it implements the interfaces Passive
 * and CapitalCase and override their methods: move(), die(), escape(), jump(),
 * moveBreed(). And it extends the class {@code simulator.do_not_change.Symbol}.
 * 
 * @author Ali Salloum
 * @see simulator.Simulator, simulator.Symbol
 */
public class SymbolCapitalP extends Symbol implements Passive, CapitalCase {

	/**
	 * A default constructor without parameters.  
	 */
	public SymbolCapitalP() {
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
	public SymbolCapitalP(int idSymbol, int numberIterationsAlive, int sightDistance) {
		this.idSymbol = idSymbol;
		Symbol.COUNT_SYMBOLS++;
		this.position = null;
		this.numberIterationsAlive = numberIterationsAlive;
		this.sightDistance = sightDistance;
	}
	
	/**
	 * Initialize a SymbolCapitalP.
	 * 
	 * @param idSymbol
	 * @param position
	 * @param numberIterationsAlive
	 * @param sightDistance
	 */
	public SymbolCapitalP(int idSymbol, Position position, int numberIterationsAlive, int sightDistance) {
		this.idSymbol = idSymbol;
		Symbol.COUNT_SYMBOLS++;
		this.position = position;
		this.numberIterationsAlive = numberIterationsAlive;
		this.sightDistance = sightDistance;
	}

	/**
	 * A constructor for class SymbolCapitalP that initialize the position and the
	 * numberIterationsAlive given as parameters. And it gives the symbol a unique
	 * id and a sight distance that is common for all the symbols in the simulation.
	 * <br>
	 * <br>
	 * It is used when a small case S is upgraded so the number of iterations alive
	 * don't get reset but is kept as the small case symbol.
	 * 
	 * @param position
	 * @param numberIterationsAlive
	 */
	public SymbolCapitalP(Position position, int numberIterationsAlive) {
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
	public SymbolCapitalP(Position position) {
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
				if (x < Simulator.MAX_ROWS - 1)
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
	 * Jump to any position in the world regardless if it's within your sight
	 * distance or not.
	 */
	@Override
	public void jump() {
		Random random = new Random();
		int i = random.nextInt(WorldController.MAX_ROWS);
		int j = random.nextInt(WorldController.MAX_COLS);
		Position newPosition = new Position(i, j);
		Simulator.world.get(this.getPosition()).remove(this);
		this.setPosition(newPosition);
		Simulator.world.get(newPosition).add(this);
	}

	/**
	 * If a threat is here, move randomly (because of being scared).
	 */
	@Override
	public void escape() {
		LinkedList<Symbol> symbolsHere = Simulator.world.get(this.position);
		for (Symbol symbol : symbolsHere) {
			if ((symbol instanceof SymbolCapitalS) || (symbol instanceof SymbolSmallS)) {
				move();
				break;
			}
		}
	}

	/**
	 * Move to a position where there is a symbol of the same kind to breed and keep
	 * its kind going.
	 */
	@Override
	public void moveBreed() {
		for (Symbol symbol : getMainList()) {
			if (symbol instanceof SymbolCapitalP && !this.equals(symbol)) {
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
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        package simulator.Ali_Salloum;

import java.util.LinkedList;
import java.util.Random;
import simulator.do_not_change.*;

/**
 * A kind of the symbols in the simulation. it implements the interfaces Passive
 * and CapitalCase and override their methods: move(), die(), escape(), jump(),
 * moveBreed(). And it extends the class {@code simulator.do_not_change.Symbol}.
 * 
 * @author Ali Salloum
 * @see simulator.Simulator, simulator.Symbol
 */
public class SymbolCapitalP extends Symbol implements Passive, CapitalCase {

	/**
	 * A default constructor without parameters.  
	 */
	public SymbolCapitalP() {
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
	public SymbolCapitalP(int idSymbol, int numberIterationsAlive, int sightDistance) {
		this.idSymbol = idSymbol;
		Symbol.COUNT_SYMBOLS++;
		this.position = null;
		this.numberIterationsAlive = numberIterationsAlive;
		this.sightDistance = sightDistance;
	}
	
	/**
	 * Initialize a SymbolCapitalP.
	 * 
	 * @param idSymbol
	 * @param position
	 * @param numberIterationsAlive
	 * @param sightDistance
	 */
	public SymbolCapitalP(int idSymbol, Position position, int numberIterationsAlive, int sightDistance) {
		this.idSymbol = idSymbol;
		Symbol.COUNT_SYMBOLS++;
		this.position = position;
		this.numberIterationsAlive = numberIterationsAlive;
		this.sightDistance = sightDistance;
	}

	/**
	 * A constructor for class SymbolCapitalP that initialize the position and the
	 * numberIterationsAlive given as parameters. And it gives the symbol a unique
	 * id and a sight distance that is common for all the symbols in the simulation.
	 * <br>
	 * <br>
	 * It is used when a small case S is upgraded so the number of iterations alive
	 * don't get reset but is kept as the small case symbol.
	 * 
	 * @param position
	 * @param numberIterationsAlive
	 */
	public SymbolCapitalP(Position position, int numberIterationsAlive) {
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
	public SymbolCapitalP(Position position) {
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
				if (x < Simulator.MAX_ROWS - 1)
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
	 * Jump to any position in the world regardless if it's within your sight
	 * distance or not.
	 */
	@Override
	public void jump() {
		Random random = new Random();
		int i = random.nextInt(WorldController.MAX_ROWS);
		int j = random.nextInt(WorldController.MAX_COLS);
		Position newPosition = new Position(i, j);
		Simulator.world.get(this.getPosition()).remove(this);
		this.setPosition(newPosition);
		Simulator.world.get(newPosition).add(this);
	}

	/**
	 * If a threat is here, move randomly (because of being scared).
	 */
	@Override
	public void escape() {
		LinkedList<Symbol> symbolsHere = Simulator.world.get(this.position);
		for (Symbol symbol : symbolsHere) {
			if ((symbol instanceof SymbolCapitalS) || (symbol instanceof SymbolSmallS)) {
				move();
				break;
			}
		}
	}

	/**
	 * Move to a position where there is a symbol of the same kind to breed and keep
	 * its kind going.
	 */
	@Override
	public void moveBreed() {
		for (Symbol symbol : getMainList()) {
			if (symbol instanceof SymbolCapitalP && !this.equals(symbol)) {
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
