package bowling.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FramesTest {

    private static final int FIVE = 5;
    private static final int ZERO = 0;

    @Test
    @DisplayName("1 ~ 10 프레임을 관리할 일급 콜렉션 Frames 생성 및 투구")
    void frameShootAndEndTest() {
        Frames frames = Frames.create();

        frames.shoot(Round.from(ZERO), ShootScore.from(FIVE));
        assertThat(frames.isRoundEnd(Round.from(ZERO))).isFalse();
    }

    @Test
    @DisplayName("투구가 끝난 Frame 에 지속적으로 투구할 경우 예외 발생")
    void invalidFrame() {
        Frames frames = Frames.create();

        frames.shoot(Round.from(ZERO), ShootScore.from(FIVE));
        frames.shoot(Round.from(ZERO), ShootScore.from(FIVE));

        assertThatThrownBy(() -> frames.shoot(Round.from(ZERO), ShootScore.from(FIVE)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}