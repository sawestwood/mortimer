package core;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class BitBoardTest {

    BitBoard board;

    @Before()
    public void setup() {
        board = new BitBoard();
        board.resetToInitialSetup();
        MoveGen.initialiseKnightLookupTable();
        MoveGen.initialiseKingLookupTable();
        MoveGen.initialisePawnLookupTable();
        MoveGen.generateMoveDatabase(true);
        MoveGen.generateMoveDatabase(false);
    }

    @Test
    public void checkWhiteIsNotInCheck() {

        String[] whiteIsNotInCheckTestCases = {"rnbqkbnr/1ppppppp/p7/8/3P4/P7/1PP1PPPP/RNBQKBNR",
                "rnbqkbnr/1ppppppp/8/p7/3P4/P7/1PP1PPPP/RNBQKBNR",
                "rnbqkbnr/2pppppp/pp6/8/3P4/P7/1PP1PPPP/RNBQKBNR",
                "rnbqkbnr/1p1ppppp/p1p5/8/3P4/P7/1PP1PPPP/RNBQKBNR",
                "rnbqkbnr/1pp1pppp/p2p4/8/3P4/P7/1PP1PPPP/RNBQKBNR",
                "rnbqkbnr/1ppppppp/p7/8/3P4/1P6/P1P1PPPP/RNBQKBNR",
                "rnbqkbnr/1ppppppp/8/p7/3P4/1P6/P1P1PPPP/RNBQKBNR",
                "rnbqkbnr/1ppppppp/p7/8/3P4/2P5/PP2PPPP/RNBQKBNR",
                "rnbqkbnr/1ppppppp/8/p7/3P4/2P5/PP2PPPP/RNBQKBNR",
                "rnbqkbnr/1ppppppp/p7/8/3P4/4P3/PPP2PPP/RNBQKBNR"};

        for (String fen : whiteIsNotInCheckTestCases) {
            board.loadFen(fen);
            assertEquals(false, board.check(CoreConstants.WHITE));
        }
    }

    @Test
    public void checkWhiteIsInCheck() {
        // Test cases where white is in check
        String[] whiteIsInCheckTestCases = {"rnbqk1nr/pppp1ppp/8/4p3/1b1P4/4P3/PPP2PPP/RNBQKBNR",
                "r1bqk1nr/pppp1ppp/8/4P3/1b1n4/4P3/PPP1KPPP/RNBQ1BNR",
                "r1b1k1nr/pppp1ppp/8/4P3/1b1nKq2/4P3/PPP2PPP/RNBQ1BNR",
                "r1b1k1nr/pppp1ppp/8/3Kq3/1b1n4/4P3/PPP2PPP/RNBQ1BNR",
                "r1b1k1nr/pppp1ppp/8/2q5/1bKn4/4P3/PPP2PPP/RNBQ1BNR",
                "r1b1k1nr/pppp1ppp/8/8/1bqn4/3KP3/PPP2PPP/RNBQ1BNR",
                "r1b1k1nr/pppp1ppp/8/3q4/1b1nK3/4P3/PPP2PPP/RNBQ1BNR",
                "r1b1k1nr/pppp1ppp/8/4q3/1b1n1K2/4P3/PPP2PPP/RNBQ1BNR",
                "r1b1k1nr/pppp1ppp/8/5q2/1b1n2K1/4P3/PPP2PPP/RNBQ1BNR",
                "r1b1k1nr/pppp1ppp/8/6q1/1b1n3K/4P3/PPP2PPP/RNBQ1BNR"};
        for (String fen : whiteIsInCheckTestCases) {
            board.loadFen(fen);
            assertEquals(true, board.check(CoreConstants.WHITE));
        }

    }

    @Test
    public void checkBlackIsNotInCheck() {

        // Test cases where black is not in check
        String[] blackIsNotInCheckTestCases = {
                "rnb1kbnr/1p1ppppp/p1p5/q7/3PP3/5N2/PPP2PPP/RNBQKB1R",
                "rnbqk1nr/1ppp1ppp/p3p3/8/1b1P4/5N2/P1P1PPPP/RNBQKB1R",
                "rnb1kbnr/1p1ppppp/p7/q1P5/8/5N2/PPP1PPPP/RNBQKB1R",
                "rnbqk1nr/p1pp1ppp/1p2p3/8/1b1P4/5N2/P1P1PPPP/RNBQKB1R",
                "rnbqkbnr/p1pppppp/8/8/1p1P4/2K5/PPP1PPPP/RNBQ1BNR",
                "rnb1kbnr/pp1ppppp/8/q1P5/8/4P3/PPP2PPP/RNBQKBNR",
                "rnb1kbnr/pp1ppppp/8/q1P5/3P4/8/P1P1PPPP/RNBQKBNR",
                "rnb1kbnr/pp1ppppp/8/q1P5/4P3/8/PPP2PPP/RNBQKBNR",
                "rnb1kbnr/pp1ppppp/8/q1P5/8/N7/PPP1PPPP/R1BQKBNR",
                "rnb1kbnr/pp1ppppp/8/q1P5/8/5N2/PPP1PPPP/RNBQKB1R"};

        for (String fen : blackIsNotInCheckTestCases) {
            board.loadFen(fen);
            assertEquals(false, board.check(CoreConstants.BLACK));
        }
    }

    @Test
    public void checkBlackIsInCheck() {
        // Test cases where black is in check
        String[] blackIsInCheckTestCases = {"rnbqkbnr/ppp1pppp/8/1B1p4/4P3/8/PPPP1PPP/RNBQK1NR",
                "rnb1kbnr/pppBpppp/8/3p4/4P3/8/PPPP1PPP/RNBQK1NR",
                "rnb2bnr/pppkpppp/8/4N3/3pP3/8/PPPP1PPP/RNBQK2R",
                "rnb2bnr/ppp1pppp/4k3/4N3/3pP1Q1/8/PPPP1PPP/RNB1K2R",
                "rnb2bnr/ppp1pppp/8/4k3/3pPQ2/8/PPPP1PPP/RNB1K2R",
                "rnb2bnr/ppp1pppp/4k3/5Q2/3pP3/8/PPPP1PPP/RNB1K2R",
                "rnb2bnr/ppp1pppp/3k4/4Q3/3pP3/8/PPPP1PPP/RNB1K2R",
                "rnb2bnr/ppp1pppp/2k5/3Q4/3pP3/8/PPPP1PPP/RNB1K2R",
                "rnb2bnr/ppp1pppp/1k6/2Q5/3pP3/8/PPPP1PPP/RNB1K2R",
                "rnb2bnr/ppp1pppp/k7/1Q6/3pP3/8/PPPP1PPP/RNB1K2R"};

        for (String fen : blackIsInCheckTestCases) {
            board.loadFen(fen);
            assertEquals(true, board.check(CoreConstants.BLACK));
        }
    }


}
