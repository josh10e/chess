package chess;

import java.util.ArrayList;
import java.util.Collection;

public class bishopMoves implements moveCalculator {

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        ChessGame.TeamColor myColor = board.getPiece(myPosition).getTeamColor();

        // Bishop moves in 4 diagonal directions
        int[][] directions = {
                {1, 1},   // up-right
                {1, -1},  // up-left
                {-1, 1},  // down-right
                {-1, -1}  // down-left
        };

        for (int[] direction : directions) {
            int row = myPosition.getRow();
            int col = myPosition.getColumn();

            while (true) {
                row += direction[0];
                col += direction[1];

                // Stop if we've moved off the board
                if (row < 1 || row > 8 || col < 1 || col > 8) {
                    break;
                }

                ChessPosition newPosition = new ChessPosition(row, col);
                ChessPiece pieceAtNewPosition = board.getPiece(newPosition);

                if (pieceAtNewPosition == null) {
                    // Empty square: valid move, keep sliding
                    moves.add(new ChessMove(myPosition, newPosition, null));
                } else {
                    // Occupied square
                    if (pieceAtNewPosition.getTeamColor() != myColor) {
                        // Enemy piece: capture, but can't go further
                        moves.add(new ChessMove(myPosition, newPosition, null));
                    }
                    // Friendly or enemy piece: stop sliding in this direction either way
                    break;
                }
            }
        }

        return moves;
    }
}