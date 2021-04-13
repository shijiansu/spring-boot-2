package spring.boot._2.based.on.howtodoinjava;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
