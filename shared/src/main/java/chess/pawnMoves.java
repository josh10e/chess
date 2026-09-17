package chess;

import java.util.ArrayList;
import java.util.Collection;

public class pawnMoves implements moveCalculator {

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        ChessGame.TeamColor myColor = board.getPiece(myPosition).getTeamColor();

        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        int direction = (myColor == ChessGame.TeamColor.WHITE) ? 1 : -1;
        int startRow = (myColor == ChessGame.TeamColor.WHITE) ? 2 : 7;
        int promotionRow = (myColor == ChessGame.TeamColor.WHITE) ? 8 : 1;

        // Straight-ahead moves (no capturing allowed)
        int oneStepRow = row + direction;
        if (oneStepRow >= 1 && oneStepRow <= 8) {
            ChessPosition oneStep = new ChessPosition(oneStepRow, col);
            if (board.getPiece(oneStep) == null) {
                addPawnMove(moves, myPosition, oneStep, oneStepRow, promotionRow);

                // Two-step move from starting row, only if both squares are empty
                if (row == startRow) {
                    int twoStepRow = row + 2 * direction;
                    ChessPosition twoStep = new ChessPosition(twoStepRow, col);
                    if (board.getPiece(twoStep) == null) {
                        moves.add(new ChessMove(myPosition, twoStep, null));
                    }
                }
            }
        }

        // Diagonal captures
        int[] diagonalCols = {col - 1, col + 1};
        for (int newCol : diagonalCols) {
            if (newCol < 1 || newCol > 8 || oneStepRow < 1 || oneStepRow > 8) {
                continue;
            }
            ChessPosition diagonal = new ChessPosition(oneStepRow, newCol);
            ChessPiece pieceAtDiagonal = board.getPiece(diagonal);

            if (pieceAtDiagonal != null && pieceAtDiagonal.getTeamColor() != myColor) {
                addPawnMove(moves, myPosition, diagonal, oneStepRow, promotionRow);
            }
        }

        return moves;
    }

    // Adds a move, expanding into all 4 promotion options if landing on the back rank
    private void addPawnMove(Collection<ChessMove> moves, ChessPosition start,
                             ChessPosition end, int endRow, int promotionRow) {
        if (endRow == promotionRow) {
            moves.add(new ChessMove(start, end, ChessPiece.PieceType.QUEEN));
            moves.add(new ChessMove(start, end, ChessPiece.PieceType.ROOK));
            moves.add(new ChessMove(start, end, ChessPiece.PieceType.BISHOP));
            moves.add(new ChessMove(start, end, ChessPiece.PieceType.KNIGHT));
        } else {
            moves.add(new ChessMove(start, end, null));
        }
    }
}