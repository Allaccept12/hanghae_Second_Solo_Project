package second.solo.repository.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import second.solo.domain.Likes;

public interface LikesRepository extends JpaRepository<Likes, Long>,LikesRepositoryCustom {


}
