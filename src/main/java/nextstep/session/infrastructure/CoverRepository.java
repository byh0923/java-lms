package nextstep.session.infrastructure;

import nextstep.session.dto.CoverDto;

public interface CoverRepository {

    long save(CoverDto coverDto);

    CoverDto findById(Long coverId);
}
