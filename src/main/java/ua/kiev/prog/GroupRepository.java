package ua.kiev.prog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("SELECT g FROM Contact g WHERE g.group IS NULL")
    List<Contact> findDefaultGroup();

    @Query("SELECT g FROM Contact g WHERE g.group =:group")
    List<Contact> findByGroup(@Param("group") Group group);
}
