package nextstep.lms.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FileMetadataTest {
    static FileMetadata NORMAL_FILE_METADATA = new FileMetadata(1024 * 1024l, FileSizeTest.NORMAL_FILE_SIZE);

    @DisplayName("파일용량이 허용 최대 용량보다 크다면 예외 발생")
    @Test
    void 파일_용량_확인() {
        assertThatThrownBy(() -> new FileMetadata(1024 * 1024 * 3l, FileSizeTest.NORMAL_FILE_SIZE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("파일이 너무 큽니다.");
    }
}