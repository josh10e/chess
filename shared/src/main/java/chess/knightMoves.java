package chess;

import java.util.ArrayList;
import java.util.Collection;

public class knightMoves implements moveCalculator {

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        ChessGame.TeamColor myColor = board.getPiece(myPosition).getTeamColor();

        int[][] offsets = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        for (int[] offset : offsets) {
            int row = myPosition.getRow() + offset[0];
            int col = myPosition.getColumn() + offset[1];

            if (row < 1 || row > 8 || col < 1 || col > 8) {
                continue;
            }

            ChessPosition newPosition = new ChessPosition(row, col);
            ChessPiece pieceAtNewPosition = board.getPiece(newPosition);

            if (pieceAtNewPosition == null || pieceAtNewPosition.getTeamColor() != myColor) {
                moves.add(new ChessMove(myPosition, newPosition, null));
            }
        }

        return moves;
    }
}