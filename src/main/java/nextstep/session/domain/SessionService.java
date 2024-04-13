package nextstep.session.domain;

import nextstep.payments.domain.Payment;
import nextstep.session.dto.SessionUpdateBasicPropertiesVO;
import nextstep.users.domain.NsUser;

public interface SessionService {

    long save(Session session);

    Session findById(long sessionId);

    int updateBasicProperties(long sessionId, SessionUpdateBasicPropertiesVO sessionUpdateDto);

    void updateCover(long sessionId, long oldCoverId, Cover newCover, NsUser requestUser);

    Session apply(long sessionId, Payment payment, Student student);

    Session deleteStudent(long sessionId, Student student, NsUser requestUser);

    void delete(long sessionId, NsUser requestUser);
}
