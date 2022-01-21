package MeeW.intern.WheelOfFortune.repository;

import MeeW.intern.WheelOfFortune.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Integer> {
    // There are no methods here, but we can still use all those, which we inherit from JpaRepository
}
