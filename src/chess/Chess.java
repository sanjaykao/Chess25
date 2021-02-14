package chess;

import java.util.ArrayList;
import java.util.Scanner;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;
import pieces.Pieces;

/*
 * @author Sanjay Kao (sjk231) and Virginia Cheng (vc365)
 */

public class Chess {
	static ArrayList<Pieces> initialize(){
		ArrayList<Pieces> temp = new ArrayList<>();
		temp.add(new Rook("a1", "wR"));
		temp.add(new Knight("b1", "wN"));
		temp.add(new Bishop("c1", "wB"));
		temp.add(new Queen("d1", "wQ"));
		temp.add(new King("e1", "wK"));
		temp.add(new Bishop("f1", "wB"));
		temp.add(new Knight("g1", "wN"));
		temp.add(new Rook("h1", "wR"));
		temp.add(new Pawn("a2", "wp"));
		temp.add(new Pawn("b2", "wp"));
		temp.add(new Pawn("c2", "wp"));
		temp.add(new Pawn("d2", "wp"));
		temp.add(new Pawn("e2", "wp"));
		temp.add(new Pawn("f2", "wp"));
		temp.add(new Pawn("g2", "wp"));
		temp.add(new Pawn("h2", "wp"));
		
		temp.add(new Rook("a8", "bR"));
		temp.add(new Knight("b8", "bN"));
		temp.add(new Bishop("c8", "bB"));
		temp.add(new Queen("d8", "bQ"));
		temp.add(new King("e8", "bK"));
		temp.add(new Bishop("f8", "bB"));
		temp.add(new Knight("g8", "bN"));
		temp.add(new Rook("h8", "bR"));
		temp.add(new Pawn("a7", "bp"));
		temp.add(new Pawn("b7", "bp"));
		temp.add(new Pawn("c7", "bp"));
		temp.add(new Pawn("d7", "bp"));
		temp.add(new Pawn("e7", "bp"));
		temp.add(new Pawn("f7", "bp"));
		temp.add(new Pawn("g7", "bp"));
		temp.add(new Pawn("h7", "bp"));
		return temp;	
	}
	
	static void printBoard(ArrayList<Pieces> list) {
		char[] file = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
		for(int i = 8; i > 0; i--) {
			for(int j = 0; j < file.length; j++) {
				boolean piece = false;
				String loc = file[j] + Integer.toString(i);
				for(Pieces item : list) {
					if(loc.equals(item.getLoc())) {
						if(j == file.length - 1) {
							System.out.println(item.getColor() + " " + i);
						}else {
							System.out.print(item.getColor() + " ");
						}
						piece = true;
						break;
					}
				}
				if(piece) {
					continue;
				}else {
					if(i % 2 == 0) {
						if(j % 2 == 1) {
							if(j == file.length - 1) {
								System.out.println("## " + i);
							}else {
								System.out.print("## ");
							}
						}else {
							System.out.print("   ");
						}
					}else {
						if(j % 2 == 1) {
							if(j == file.length - 1) {
								System.out.println("   " + i);
							}else {
								System.out.print("   ");
							}
						}else {
							System.out.print("## ");
						}
					}
				}
			}
		}
		for(int i = 0; i < file.length; i++) {
			if(i == 0) {
				System.out.print(" " + file[i]);
			}else if(i == file.length - 1) {
				System.out.println("  " + file[i]);
			}else {
				System.out.print("  " + file[i]);
			}
		}
		System.out.println();
	}
	
	static boolean pathClear(ArrayList<Pieces> list, String from, String to) {
		int h = to.charAt(0) - from.charAt(0);
		int v = to.charAt(1) - from.charAt(1);
		int start = 0;
		int end = 0;
			
		char file = from.charAt(0);
		if(h == 0) {
			if(v < 0) {
				start = Character.getNumericValue(to.charAt(1));
				end = Character.getNumericValue(from.charAt(1));
			}else {
				start = Character.getNumericValue(from.charAt(1));
				end = Character.getNumericValue(to.charAt(1));
			}
			start++;
			while(start < end) {
				String loc = file + Integer.toString(start);
				for(Pieces item : list) {
					if(loc.equals(item.getLoc())) {
						return false;
					}
				}
				start++;
			}
		}else if(v == 0) {
			if(h < 0) {
				start = (int)to.charAt(0);
				end = (int)from.charAt(0);
			}else {
				start = (int)from.charAt(0);
				end = (int)to.charAt(0);
			}
			start++;
			while(start < end) {
				String loc1 = Character.toString((char)start) + from.charAt(1);
				for(Pieces item : list) {
					if(loc1.equals(item.getLoc())) {
						return false;
					}
				}
				start++;
			}
		}else {
			if(h < 0) {
				start = (int)to.charAt(0);
				end = Character.getNumericValue(to.charAt(1));
			}else {
				start = (int)from.charAt(0);
				end = Character.getNumericValue(from.charAt(1));
			}
			start++;
			if(v > 0) {
				end++;
			}else {
				end--;
			}
			for(int i = 0; i < Math.abs(h) - 1; i++) {
				String loc2 = Character.toString((char)start) + Integer.toString(end);
				for(Pieces item : list) {
					if(loc2.equals(item.getLoc())) {
						return false;
					}
				}
				if((h < 0 && v < 0) || (h > 0 && v > 0)) {
					start++;
					end++;
				}else {
					start++;
					end--;
				}
			}
		}
		return true;
	}
	
	static boolean friendlyFire(ArrayList<Pieces> list, Pieces piece, String to) {
		for(Pieces item : list) {
			if(item.getLoc().equals(to)) {
				if(item.getColor().charAt(0) == piece.getColor().charAt(0)) {
					return true;
				}
			}
		}
		return false;
	}
	
	static ArrayList<Pieces> capture(ArrayList<Pieces> list, String to) {
		ArrayList<Pieces> temp = list;
		for(Pieces item : temp) {
			if(item.getLoc().equals(to)) {
				temp.remove(item);
				break;
			}
		}
		return temp;
	}
	
	static boolean underAttack(ArrayList<Pieces> list, String loc, char turn) {
		for(Pieces item : list) {
			if(item.getColor().charAt(0) != turn) {
				boolean valid = item.isValid(loc);
				if(valid) {
					if(item instanceof Knight) {
						return true;
					}else {
						boolean clear = pathClear(list, item.getLoc(), loc);
						if(clear) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	static boolean block(ArrayList<Pieces> list, String loc, char turn) {
		for(Pieces item : list) {
			if(item.getColor().charAt(0) != turn) {
				if(item instanceof King) {
					continue;
				}
				boolean valid = item.isValid(loc);
				if(valid) {
					if(item instanceof Knight) {
						return true;
					}else {
						boolean clear = pathClear(list, item.getLoc(), loc);
						if(clear) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	static boolean castle(ArrayList<Pieces> list, String king, String middle, String to, String rook, char turn) {
		boolean path = pathClear(list, king, rook);
		if(path) {
			boolean attacked = underAttack(list, middle, turn);
			if(!attacked) {
				boolean attacked2 = underAttack(list, to, turn);
				if(!attacked2) {
					return true;
				}
			}
		}
		return false;
	}
	
	static boolean check(ArrayList<Pieces> list, char turn) {
		String color;
		if(turn == 'w') {
			color = "wK";
		}else {
			color = "bK";
		}
		for(Pieces item : list) {
			if(item.getColor().equals(color)) {
				boolean attacked = underAttack(list, item.getLoc(), turn);
				if(attacked) {
					return true;
				}
				
			}
		}
		return false;
	}
	
	static ArrayList<String> findMoves(ArrayList<Pieces> list, String loc){
		ArrayList<String> temp = new ArrayList<String>();
		int file = (int)loc.charAt(0);
		int rank = Character.getNumericValue(loc.charAt(1));
		String loc1;
		for(int i = file - 1; i <= file + 1; i++) {
			for(int j = rank - 1; j <= rank + 1; j++) {
				if(i < 97 || i > 104 || j < 1 || j > 8) {
					continue;
				}
				loc1 = Character.toString((char)i) + Integer.toString(j);
				if(loc1.equals(loc)) {
					continue;
				}else {
					temp.add(loc1);
				}
			}
		}
		return temp;
	}
	
	static ArrayList<String> findAttacking(ArrayList<Pieces> list, String loc, char turn){
		ArrayList<String> temp = new ArrayList<String>();
		for(Pieces item : list) {
			if(item.getColor().charAt(0) != turn) {
				boolean valid = item.isValid(loc);
				if(valid) {
					if(item instanceof Knight) {
						temp.add(item.getLoc());
					}else {
						boolean clear = pathClear(list, item.getLoc(), loc);
						if(clear) {
							temp.add(item.getLoc());
						}
					}
				}
			}
		}
		return temp;
	}
	
	static boolean checkmate(ArrayList<Pieces> list, Pieces king, char turn) {
		ArrayList<String> moves = new ArrayList<String>();
		ArrayList<String> apieces = new ArrayList<String>();
		char other;
		if(turn == 'w') {
			other = 'b';
		}else {
			other = 'w';
		}
		apieces = findAttacking(list, king.getLoc(), turn);
		moves = findMoves(list, king.getLoc());
		int numMoves = 0;
		for(String move : moves) {
			boolean ffire = friendlyFire(list, king, move);
			boolean attacked = underAttack(list, move, turn);
			boolean canBlock = block(list, move, other);
			if(!ffire && (!attacked || canBlock)) {
				numMoves++;
			}
		}
		for(String piece : apieces) {
			boolean attacked = underAttack(list, piece, other);
			if(attacked) {
				numMoves++;
			}
		}
		if(numMoves == 0) {
			return true;
		}
		return false;
	}
	
	static ArrayList<Pieces> pawnDiagonal(ArrayList<Pieces> list, Pieces piece, String to, char turn) {
		boolean ffire = friendlyFire(list, piece, to);
		boolean piecePresent = false;
		Pieces tempPiece = null;
		ArrayList<Pieces> temp = list;
		for(Pieces item : temp) {
			if(item.getLoc().equals(to)) {
				piecePresent = true;
			}
		}
		
		for(Pieces item : temp) {
			if(item.getLoc().equals(piece.getLoc())) {
				tempPiece = item;
				System.out.println(tempPiece.getColor());
				break;
			}
		}
		
		//regular diagonal capture
		if(!ffire && piecePresent) {
			temp = capture(temp, to);
			((Pawn)tempPiece).move(to);
			return temp;
		} else if(!piecePresent) {
			//check for enpassant
			//check rows 4 (for white pawn) or 5 (for black pawn)
			char col = to.charAt(0);
			String checkSquare;
			boolean canEnpassant = false;
			if(turn == 'w') {
				checkSquare = col + "5";
			} else {
				checkSquare = col + "4";
			}
			
			for(Pieces item : temp) {
				if(item.getLoc().equals(checkSquare)) {
					canEnpassant = ((Pawn)item).canEnpassant();
					System.out.println(item.getLoc() + " " + item.getColor());
					temp.remove(item);
					break;
				}
			}
			
			if(canEnpassant) {
				((Pawn)tempPiece).move(to);
				return temp;
			
			} else {
				System.out.println("\nIllegal move in pawnDiagonal, try again\n");
			}

		}
		
		return null; //returning null would mean it's an illegal move
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		ArrayList<Pieces> pieces = initialize();
		
		boolean finished = false;
		char turn = 'w';
		boolean success = true;
		boolean wchecked = false;
		boolean bchecked = false;
		boolean canDraw = false;
		boolean mate = false;
		
		while(!finished) {
			boolean valid;
			boolean clear;
			if(success) {
				printBoard(pieces);
			}
			wchecked = check(pieces, 'w');
			bchecked = check(pieces, 'b');
			if(turn == 'w') {
				if(wchecked) {
					System.out.println("Check\n");
				}
			}else {
				if(bchecked) {
					System.out.println("Check\n");
				}
			}
			if(turn == 'w' && wchecked) {
				for(Pieces x : pieces) {
					if(x.getColor().equals("wK")) {
						mate = checkmate(pieces, x, turn);
						break;
					}
				}
				if(mate) {
					System.out.println("\nCheckmate\n\nBlack wins");
					System.exit(0);
				}
			}
			if(turn == 'b' && bchecked) {
				for(Pieces x : pieces) {
					if(x.getColor().equals("bK")) {
						mate = checkmate(pieces, x, turn);
						break;
					}
				}
				if(mate) {
					System.out.println("\nCheckmate\n\nWhite wins");
					System.exit(0);
				}
			}
			if(turn == 'w') {
				System.out.print("White's move: ");
			}else {
				System.out.print("Black's move: ");
			}
			String move = input.nextLine();
			//must check for draw? length > 7
			//non-promotion draw is length 11
			//promotion draw is length 13
			if(move.length() > 7) {
				if(move.length() == 11) {
					//non-promotion draw
					String draw = move.substring(6);
					if(draw.equals("draw?")) {
						canDraw = true;
					} else {
						System.out.println("\nIllegal move, try again\n");
						success = false;
					}
				} else if(move.length() == 13) {
					//promotion draw
					String draw = move.substring(9);
					if(draw.equals("draw?")) {
						canDraw = true;
					} else {
						System.out.println("\nIllegal move, try again\n");
						success = false;
					}
					
				} else {
					//invalid move
					System.out.println("\nIllegal move, try again\n");
					success = false;
				}
			} else if(move.equals("resign")) {
				if(turn == 'w') {
					System.out.println("\nBlack wins");
				}else {
					System.out.println("\nWhite wins"); 
				}
				System.exit(0);
			} else if(canDraw) {
				if(move.equals("draw")) {
					System.out.println("\nDraw");
					System.exit(0);
				}else {
					System.out.println("\nIllegal move, try again\n");
					success = false;
				}
			} else if(move.length() == 7) {
				//PAWN PROMOTION
				//example input: g7 g8 Q
				String from = move.substring(0, move.indexOf(' '));
				String to = move.substring(move.indexOf(' ') + 1, move.length() - 2);
				char newPiece = move.charAt(move.length() - 1);

				for(Pieces item : pieces ) {
					if(item.getLoc().equals(from) && item instanceof Pawn) {
						if(item.isValid(to) && (to.charAt(1) == '8' || to.charAt(1) == '1')) {
								pieces.remove(item);
								Pieces temp = null;
								//PROMOTE TO QUEEN, ROOK, BISHOP OR KNIGHT
								if(newPiece == 'Q') {
									if(turn == 'w') {
										temp = new Queen(to, "wQ");
									} else {
										temp = new Queen(to, "bQ");
									}
								} else if(newPiece == 'R') {
									if(turn == 'w') {
										temp = new Rook(to, "wR");
									} else {
										temp = new Rook(to, "bR");
									}
								} else if(newPiece == 'B') {
									if(turn == 'w') {
										temp = new Bishop(to, "wB");
									} else {
										temp = new Bishop(to, "bB");
									}
								} else if(newPiece == 'N') {
									if(turn == 'w') {
										temp = new Knight(to, "wN");
									} else {
										temp = new Knight(to, "bN");
									}
								} else {
									System.out.println("\nIllegal move, try again\n");
									success = false;
									break;
								}
								
								pieces.add(temp);
								success = true;
								break;
						} else {
							System.out.println("\nIllegal move, try again\n");
							success = false;
							break;
						}
					}
				}
			} else if(move.length() < 5) {
				System.out.println("\nIllegal move, try again\n");
				success = false;
				
			} else {
				String from = move.substring(0, move.indexOf(' '));
				String to = move.substring(move.indexOf(' ') + 1);
				for(Pieces item : pieces) {
					if(item.getLoc().equals(from)) {
						int h = to.charAt(0) - from.charAt(0);
						int v = to.charAt(1) - from.charAt(1);
						//CASTLING
						boolean castled = false;
						if(item instanceof King && Math.abs(h) == 2) {
							if(turn == 'w') {
								if(wchecked) {
									System.out.println("\nIllegal move, try again\n");
									success = false;
									break;
								}
							}else {
								if(bchecked) {
									System.out.println("\nIllegal move, try again\n");
									success = false;
									break;
								}
							}
							boolean moved = ((King)item).moved();
							if(!moved) {
								String rook;
								String rookTo;
								if(h > 0) {
									if(turn == 'w') {
										rook = "h1";
										rookTo = "f1";
									}else {
										rook = "h8";
										rookTo = "f8";
									}
								}else {
									if(turn == 'w') {
										rook = "a1";
										rookTo = "d1";
									}else {
										rook = "a8";
										rookTo = "d8";
									}
								}
								for(Pieces piece: pieces) {
									if(piece.getLoc().equals(rook) && piece.getColor().charAt(0) == turn) {
										if(piece instanceof Rook) {
											boolean rmoved = ((Rook)piece).moved();
											if(!rmoved) {
												castled = castle(pieces, from, rookTo, to, piece.getLoc(), turn);
												if(castled) {
													item.move(to);
													((King)item).setMoved(true);
													piece.move(rookTo);
													((Rook)piece).setMoved(true);
													success = true;
												}else {
													System.out.println("\nIllegal move, try again\n");
													success = false;
												}
											}
										}else {
											System.out.println("\nIllegal move, try again\n");
											success = false;
										}
									}
								}
								if(!castled) {
									System.out.println("\nIllegal move, try again\n");
									success = false;
								}
							}else {
								System.out.println("\nIllegal move, try again\n");
								success = false;
							}
						} else if(item instanceof Pawn && (Math.abs(h) == Math.abs(v))){
							//EN PASSANT OR DIAGONAL CAPTURE FOR PAWN
							ArrayList<Pieces> temp = pawnDiagonal(pieces, item, to, turn);
							if(temp == null) {
								success = false;
							} else {
								pieces = temp;
								success = true;
							}
							
						} else {
							//REGULAR MOVE
							valid = item.isValid(to);
							if(valid) {
								boolean ffire = friendlyFire(pieces, item, to);
								if(!ffire) {
									clear = pathClear(pieces, from, to);
									if(clear) {
										pieces = capture(pieces, to);
										item.move(to);
										boolean checked = false;
										if(turn == 'w') {
											checked = check(pieces, 'w');
										}else {
											checked = check(pieces, 'b');
										}
										if(checked) {
											System.out.println("\nIllegal move, try again\n");
											item.move(from);
											success = false;
										}else {
											success = true;
										}
									}else {
										System.out.println("\nIllegal move, try again\n");
										success = false;
									}
								}else {
									System.out.println("\nIllegal move, try again\n");
									success = false;
								}	
							}else {
								System.out.println("\nIllegal move, try again\n");
								success = false;
							}
						}
						break;
					}
				}
			}
			if(success) {
				if(turn == 'w') {
					turn = 'b';
				}else {
					turn = 'w';
				}
				System.out.println();
			}
		}
		
		input.close(); 
	}
}
