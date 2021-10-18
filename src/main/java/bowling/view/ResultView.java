package bowling.view;

import bowling.domain.FrameResult;
import bowling.domain.PlayerGame;
import bowling.domain.frame.FrameNumber;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {
    private static final int MAX_LENGTH = 6;

    private static final String BLANK = " ";

    private static final String SEPARATOR = "|";

    public static void printResult(final PlayerGame playerGame) {
        printName("NAME");
        printFrameIndex();
        printName(playerGame.getName());
        List<FrameResult> frameResults = playerGame.getFrameResults();
        printResult(toIndications(frameResults));
        printName("");
        printResult(toScores(frameResults));
        System.out.println();
    }

    private static void printName(final String name) {
        System.out.print(SEPARATOR);
        printContent(name);
    }

    private static void printFrameIndex() {
        for (int index = 1; index <= FrameNumber.FINAL_NUMBER; index++) {
            System.out.print(SEPARATOR);
            printContent(printFrameNumberDoubleDigits(index));
        }
        System.out.println(SEPARATOR);
    }

    private static void printResult(final List<String> results) {
        for (int index = 1; index <= results.size(); index++) {
            System.out.print(SEPARATOR);
            printContent(results.get(index - 1));
        }

        for (int index = results.size() + 1; index <= FrameNumber.FINAL_NUMBER; index++) {
            System.out.print(SEPARATOR);
            printContent("");
        }
        System.out.println(SEPARATOR);
    }

    private static void printContent(final String content) {
        int oddPlusBlank = content.length() % 2;
        int blankSize = (MAX_LENGTH - content.length()) / 2;
        System.out.print(printBlank(blankSize));
        System.out.print(content);
        System.out.print(printBlank(blankSize + oddPlusBlank));
    }

    private static String printBlank(final int size) {
        return IntStream.range(0, size)
                .mapToObj(operand -> BLANK)
                .collect(Collectors.joining());
    }

    private static String printFrameNumberDoubleDigits(final int index) {
        if (index < FrameNumber.FINAL_NUMBER) {
            return "0" + index;
        }
        return String.valueOf(index);
    }

    private static List<String> toIndications(final List<FrameResult> frameResults) {
        return frameResults.stream()
                .map(FrameResult::showIndication)
                .collect(Collectors.toList());
    }

    private static List<String> toScores(final List<FrameResult> frameResults) {
        return frameResults.stream()
                .map(FrameResult::showScore)
                .collect(Collectors.toList());
    }
}
