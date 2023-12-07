package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Students;
import nextstep.courses.domain.session.StudentsRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("studentsRepository")
public class JdbcStudentsRepository implements StudentsRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcStudentsRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(long sessionId, Students students) {
        String sql = "insert into session_student (session_id, user_id) values(?, ?)";

        return students.values()
                .stream()
                .mapToInt(student -> jdbcTemplate.update(sql, sessionId, student.getUserId()))
                .sum();
    }

    @Override
    public Students findBySessionId(Long sessionId) {
        String sql = "select a.id, a.user_id, a.password, a.name, a.email, a.created_at, a.updated_at from ns_user a inner join session_student b on a.user_id = b.user_id where b.session_id = ?";

        return Students.of(
                jdbcTemplate.query(
                        sql,
                        (rs, rowNum) -> new NsUser(
                                rs.getLong(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                toLocalDateTime(rs.getTimestamp(6)),
                                toLocalDateTime(rs.getTimestamp(7))),
                        sessionId));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
