package MeeW.intern.WheelOfFortune.repository;


import MeeW.intern.WheelOfFortune.entities.WheelOfFortune;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WheelOfFortuneRepository extends JpaRepository<WheelOfFortune, Integer> {
    // There are no methods here, but we can still use all those, which we inherit from JpaRepository
}
