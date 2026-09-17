package chess;

import java.util.Collection;

/**
 * Calculates all the possible moves a chess piece can make from a given
 * position on the board, without regard to whether the move would leave
 * the mover's own king in check.
 */
public interface moveCalculator {

    /**
     * Calculates all positions a piece can move to from myPosition, given
     * the current state of the board.
     *
     * @param board      the current state of the chessboard
     * @param myPosition the position of the piece whose moves are being calculated
     * @return a collection of valid moves for that piece from that position
     */
    Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition);
}