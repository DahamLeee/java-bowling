package bowling.domain;

import bowling.exception.BusinessException;

import java.util.Objects;

public class FrameNumber {
    private static final String CREATE_FRAME_NUMBER_ERROR = "프레임은 " + BowlingGame.MAX_FRAME_SIZE + "개 입니다.";

    private final int frameNumber;

    public FrameNumber(final int frameNumber) {
        if (frameNumber > BowlingGame.MAX_FRAME_SIZE || frameNumber < Frames.FIRST_FRAME_INDEX) {
            throw new BusinessException(CREATE_FRAME_NUMBER_ERROR);
        }
        this.frameNumber = frameNumber;
    }

    public int nextNumber() {
        return frameNumber + 1;
    }

    public boolean isLastNormalNumber() {
        return frameNumber == Frames.LAST_FRAME_INDEX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameNumber that = (FrameNumber) o;
        return frameNumber == that.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}