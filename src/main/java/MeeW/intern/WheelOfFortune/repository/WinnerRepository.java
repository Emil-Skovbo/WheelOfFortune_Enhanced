package MeeW.intern.WheelOfFortune.repository;

import MeeW.intern.WheelOfFortune.entities.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepository extends JpaRepository<Winner, Integer> {
    // There are no methods here, but we can still use all those, which we inherit from JpaRepository
}
