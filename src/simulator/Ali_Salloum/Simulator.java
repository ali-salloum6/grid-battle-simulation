package simulator.Ali_Salloum;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import simulator.do_not_change.*;

/**
 * A class that simulate a world where there are six kinds of symbols which
 * interact between each other. Basically it demonstrates the
 * rock-paper-scissors relationship.
 * 
 * @author Ali Salloum
 *
 */
public class Simulator extends WorldController {

	private static StringBuilder worldGrid = new StringBuilder();
	private static Simulator simulator = new Simulator();

	public static void main(String[] args) {

		initializeWorld();
		for (int iteration = 0; iteration < 1000; iteration++) {
			System.out.println(simulator.plotWorld());
			System.out.println("this is iteration no. " + (iteration + 1));
			System.out.println();
			makeAllOlder(getMainList());
			simulator.symbolsDie(getMainList());
			simulator.smallCaseUpgrade(getSmallCaseList());
			simulator.capitalCaseJump(getCapitalCaseList());
			simulator.symbolsMove(getMainList());
			simulator.passiveEscape(getPassiveList());
			simulator.passiveBreed(getPassiveList());
			simulator.aggressiveAttackSmart(getAggressiveList());
		}
	}

	/**
	 * Moves the symbols in the list randomly.
	 */
	@Override
	public void symbolsMove(List<Symbol> symbols) {
		for (Symbol symbol : symbols) {
			symbol.move();
		}
	}

	/**
	 * Kill all symbols that reached 5 iterations old.
	 */
	@Override
	public void symbolsDie(List<Symbol> symbols) {
		final int maxAge = 8;
		for (Symbol symbol : symbols) {
			if (symbol.getNumberIterationsAlive() > maxAge) {
				symbol.die();
			}
		}
	}

	/**
	 * Turn small case symbols into capital case symbols if they reached 3
	 * iterations old.
	 */
	@Override
	public void smallCaseUpgrade(List<SmallCase> symbols) {
		final int puberty = 4;
		for (SmallCase smallCase : symbols) {
			Symbol symbol = (Symbol) smallCase;
			if (symbol.getNumberIterationsAlive() >= puberty) {
				smallCase.upgrade();
			}
		}
	}

	/**
	 * Make capital case symbols jump randomly to any position in the world
	 * regardless if it is in its sight distance or not.
	 */
	@Override
	public void capitalCaseJump(List<CapitalCase> symbols) {
		for (CapitalCase capitalCaseSymbol : symbols) {
			capitalCaseSymbol.jump();
		}
	}

	/**
	 * Make all passive symbols escape if there is a threat on their lives.
	 */
	@Override
	public void passiveEscape(List<Passive> symbols) {
		for (Passive passiveSymbol : symbols) {
			passiveSymbol.escape();
		}
	}

	/**
	 * Make passive symbols move to symbols of the same kind to breed. (if they're
	 * in their sight distance).
	 */
	@Override
	public void passiveBreed(List<Passive> symbols) {
		for (Passive passiveSymbol : symbols) {
			passiveSymbol.moveBreed();
		}
	}

	/**
	 * Make all aggressive symbols attack symbols that are weaker than them within
	 * their sight distance.
	 */
	@Override
	public void aggressiveAttackSmart(List<Aggressive> symbols) {
		for (Aggressive aggressiveSymbol : symbols) {
			aggressiveSymbol.attackSmart();
		}
	}

	/**
	 * First, make all symbols breed then attack. then iterate over all the
	 * positions and print the oldest one in this position. It appends the symbol to
	 * a StringBuilder and at last return the contents of this StringBuilder as a
	 * string.
	 * 
	 * @return the world as a string
	 */
	@Override
	public String plotWorld() {
		breedAll();
		attackAll();
		worldGrid.delete(0, worldGrid.length());
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Position position = new Position(i, j);
				LinkedList<Symbol> symbols = world.get(position);
				if (symbols.isEmpty() == true) {
					worldGrid.append(". ");
				} else {
					Symbol symbol = world.get(position).get(0);
					char c = getCharOfSymbol(symbol);
					worldGrid.append(c + " ");
				}
			}
			worldGrid.append('\n');
		}
		return worldGrid.toString();
	}

	/**
	 * edit create the linked list of each position in the hash map called
	 * world, then fill the world with random symbols. But first, make a pair of
	 * each kind of symbol so that every kind has the chance to breed and Live.
	 */
	private static void initializeWorld() {
		world = new HashMap<Position, LinkedList<Symbol>>();
		Random random = new Random();
		makePairs();
		for (int i = 0; i < 100; i++) {
			Position here = new Position(i / 10, i % 10);
			Symbol symbol = null;
			LinkedList<Symbol> temp = new LinkedList<Symbol>();
			int x = random.nextInt(7);
			switch (x) {
			case 0:
				SymbolCapitalR capitalR = new SymbolCapitalR(Symbol.COUNT_SYMBOLS, here, 0, 4);
				symbol = capitalR;
				break;
			case 1:
				SymbolSmallR smallR = new SymbolSmallR(Symbol.COUNT_SYMBOLS, here, 0, 4);
				symbol = smallR;
				break;
			case 2:
				SymbolCapitalP capitalP = new SymbolCapitalP(Symbol.COUNT_SYMBOLS, here, 0, 4);
				symbol = capitalP;
				break;
			case 3:
				SymbolSmallP smallP = new SymbolSmallP(Symbol.COUNT_SYMBOLS, here, 0, 4);
				symbol = smallP;
				break;
			case 4:
				SymbolCapitalS capitalS = new SymbolCapitalS(Symbol.COUNT_SYMBOLS, here, 0, 4);
				symbol = capitalS;
				break;
			case 5:
				SymbolSmallS smallS = new SymbolSmallS(Symbol.COUNT_SYMBOLS, here, 0, 4);
				symbol = smallS;
				break;
			case 6:
				// do not create a symbol
				break;
			}
			if (x != 6) {
				temp.add(symbol);
			}
			world.put(here, temp);
		}
	}

	/**
	 * Make a pair of each kind of symbols.
	 */
	private static void makePairs() {

		SymbolCapitalR capitalR1 = new SymbolCapitalR(Symbol.COUNT_SYMBOLS, null, 0, 4);
		SymbolCapitalR capitalR2 = new SymbolCapitalR(Symbol.COUNT_SYMBOLS, null, 0, 4);
		distributePair(capitalR1, capitalR2);
		SymbolSmallR smallR1 = new SymbolSmallR(Symbol.COUNT_SYMBOLS, null, 0, 4);
		SymbolSmallR smallR2 = new SymbolSmallR(Symbol.COUNT_SYMBOLS, null, 0, 4);
		distributePair(smallR1, smallR2);
		SymbolCapitalP capitalP1 = new SymbolCapitalP(Symbol.COUNT_SYMBOLS, null, 0, 4);
		SymbolCapitalP capitalP2 = new SymbolCapitalP(Symbol.COUNT_SYMBOLS, null, 0, 4);
		distributePair(capitalP1, capitalP2);
		SymbolSmallP smallP1 = new SymbolSmallP(Symbol.COUNT_SYMBOLS, null, 0, 4);
		SymbolSmallP smallP2 = new SymbolSmallP(null);
		distributePair(smallP1, smallP2);
		SymbolCapitalS capitalS1 = new SymbolCapitalS(Symbol.COUNT_SYMBOLS, null, 0, 4);
		SymbolCapitalS capitalS2 = new SymbolCapitalS(Symbol.COUNT_SYMBOLS, null, 0, 4);
		distributePair(capitalS1, capitalS2);
		SymbolSmallS smallS1 = new SymbolSmallS(Symbol.COUNT_SYMBOLS, null, 0, 4);
		SymbolSmallS smallS2 = new SymbolSmallS(Symbol.COUNT_SYMBOLS, null, 0, 4);
		distributePair(smallS1, smallS2);

	}

	/**
	 * Distribute pairs of symbols randomly.
	 * 
	 * @param first
	 * @param second
	 */
	private static void distributePair(Symbol first, Symbol second) {
		Random random = new Random();
		Position firstPosition = new Position(random.nextInt(10), random.nextInt(10));
		Position secondPosition = new Position(random.nextInt(10), random.nextInt(10));
		first.setPosition(firstPosition);
		second.setPosition(secondPosition);
	}

	/**
	 * @return a linked list with all living symbols in the hash map called world.
	 */
	private static LinkedList<Symbol> getMainList() {
		LinkedList<Symbol> list = new LinkedList<Symbol>();
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLS; j++) {
				Position position = new Position(i, j);
				for (Symbol symbol : world.get(position)) {
					list.add(symbol);
				}
			}
		}
		return list;
	}

	/**
	 * @return a linked list with all living small case symbols in the hash map
	 *         called world
	 */
	public static LinkedList<SmallCase> getSmallCaseList() {
		LinkedList<SmallCase> list = new LinkedList<SmallCase>();
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLS; j++) {
				Position position = new Position(i, j);
				for (Symbol symbol : world.get(position)) {
					if (symbol instanceof SmallCase) {
						list.add((SmallCase) symbol);
					}
				}
			}
		}
		return list;
	}

	/**
	 * @return a linked list with all capital case symbols in the hash map called
	 *         world
	 */
	public static LinkedList<CapitalCase> getCapitalCaseList() {
		LinkedList<CapitalCase> list = new LinkedList<CapitalCase>();
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLS; j++) {
				Position position = new Position(i, j);
				for (Symbol symbol : world.get(position)) {
					if (symbol instanceof CapitalCase) {
						list.add((CapitalCase) symbol);
					}
				}
			}
		}
		return list;
	}

	/**
	 * @return a linked list with all passive symbols in the hash map called world
	 */
	public static LinkedList<Passive> getPassiveList() {
		LinkedList<Passive> list = new LinkedList<Passive>();
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLS; j++) {
				Position position = new Position(i, j);
				for (Symbol symbol : world.get(position)) {
					if (symbol instanceof Passive) {
						list.add((Passive) symbol);
					}
				}
			}
		}
		return list;
	}

	/**
	 * @return a linked list with all aggressive symbols in the hash map called
	 *         world
	 */
	public static LinkedList<Aggressive> getAggressiveList() {
		LinkedList<Aggressive> list = new LinkedList<Aggressive>();
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLS; j++) {
				Position position = new Position(i, j);
				for (Symbol symbol : world.get(position)) {
					if (symbol instanceof Aggressive) {
						list.add((Aggressive) symbol);
					}
				}
			}
		}
		return list;
	}

	/**
	 * checks for the kind of a symbol and gives it as a char
	 * 
	 * @param symbol which we want to convert to char
	 * @return a char that represents the given symbol
	 */
	private static char getCharOfSymbol(Symbol symbol) {
		if (symbol instanceof SymbolSmallS) {
			return SMALL_S;
		} else if (symbol instanceof SymbolCapitalS) {
			return CAPITAL_S;
		} else if (symbol instanceof SymbolSmallP) {
			return SMALL_P;
		} else if (symbol instanceof SymbolCapitalP) {
			return CAPITAL_P;
		} else if (symbol instanceof SymbolSmallR) {
			return SMALL_R;
		} else if (symbol instanceof SymbolCapitalR) {
			return CAPITAL_R;
		} else
			return '.';
	}

	/**
	 * increase age of all symbols in the list
	 * 
	 * @param list contains the symbols that will become older
	 */
	private static void makeAllOlder(List<Symbol> list) {
		for (Symbol symbol : list) {
			symbol.becomeOlder();
		}
	}

	/**
	 * Make symbols of the same kind at the same position breed and give a child in
	 * the previous position in the world if it exists.
	 */
	private static void breedAll() {
		for (int i = 0; i < WorldController.MAX_ROWS; i++) {
			for (int j = 0; j < WorldController.MAX_COLS; j++) {
				Position position = new Position(i, j);
				LinkedList<Symbol> symbolsHere = Simulator.world.get(position);
				int smallSCount = 0, smallPCount = 0, smallRCount = 0, capitalSCount = 0, capitalPCount = 0,
						capitalRCount = 0;
				for (Symbol symbol : symbolsHere) {
					if (symbol instanceof SymbolSmallS) {
						smallSCount++;
					} else if (symbol instanceof SymbolCapitalS) {
						capitalSCount++;
					} else if (symbol instanceof SymbolSmallP) {
						smallPCount++;
					} else if (symbol instanceof SymbolCapitalP) {
						capitalPCount++;
					} else if (symbol instanceof SymbolSmallR) {
						smallRCount++;
					} else if (symbol instanceof SymbolCapitalR) {
						capitalRCount++;
					}
				}
				int x = 0;
				if (i > 0)
					x = i - 1;
				else
					x = i + 1;
				Position childPosition = new Position(x, j);
				if (smallSCount >= 2) {
					SymbolSmallS child = new SymbolSmallS(childPosition);
					Simulator.world.get(childPosition).add(child);
				}
				if (smallPCount >= 2) {
					SymbolSmallP child = new SymbolSmallP(childPosition);
					Simulator.world.get(childPosition).add(child);
				}
				if (smallRCount >= 2) {
					SymbolSmallR child = new SymbolSmallR(childPosition);
					Simulator.world.get(childPosition).add(child);
				}
				if (capitalSCount >= 2) {
					SymbolSmallS child = new SymbolSmallS(childPosition);
					Simulator.world.get(childPosition).add(child);
				}
				if (capitalPCount >= 2) {
					SymbolSmallP child = new SymbolSmallP(childPosition);
					Simulator.world.get(childPosition).add(child);
				}
				if (capitalRCount >= 2) {
					SymbolSmallR child = new SymbolSmallR(childPosition);
					Simulator.world.get(childPosition).add(child);
				}
			}
		}
	}

	/**
	 * Make the symbols at the same position kill each other depending on their
	 * kind's hierarchy. the order is: paper > rock > scissors > paper (a cycle, no
	 * dominant kind). <br>
	 * <br>
	 * If there is all kinds of symbols at the same position, all of them die.
	 */
	private static void attackAll() {
		for (int i = 0; i < WorldController.MAX_ROWS; i++) {
			for (int j = 0; j < WorldController.MAX_COLS; j++) {
				Position position = new Position(i, j);
				LinkedList<Symbol> symbolsHere = Simulator.world.get(position);
				LinkedList<Symbol> sList = new LinkedList<Symbol>();
				LinkedList<Symbol> pList = new LinkedList<Symbol>();
				LinkedList<Symbol> rList = new LinkedList<Symbol>();
				for (Symbol symbol : symbolsHere) {
					if (symbol instanceof SymbolSmallS || symbol instanceof SymbolCapitalS) {
						sList.add(symbol);
					} else if (symbol instanceof SymbolSmallP || symbol instanceof SymbolCapitalP) {
						pList.add(symbol);
					} else if (symbol instanceof SymbolSmallR || symbol instanceof SymbolCapitalR) {
						rList.add(symbol);
					}
				}
				LinkedList<Symbol> killingList = new LinkedList<Symbol>();
				if (sList.size() > 0 && pList.size() > 0 && rList.size() > 0) {
					killingList.addAll(sList);
					killingList.addAll(pList);
					killingList.addAll(rList);
				} else if (sList.size() == 0) {
					if (pList.size() > 0 && rList.size() > 0) {
						killingList.addAll(rList);
					}
				} else if (rList.size() == 0) {
					if (sList.size() > 0 && pList.size() > 0) {
						killingList.addAll(pList);
					}
				} else if (pList.size() == 0) {
					if (sList.size() > 0 && rList.size() > 0) {
						killingList.addAll(sList);
					}
				}
				for (Symbol symbol : killingList) {
					symbol.die();
				}
			}
		}
	}
}
