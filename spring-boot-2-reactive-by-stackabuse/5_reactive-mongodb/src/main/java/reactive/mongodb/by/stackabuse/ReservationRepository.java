package reactive.mongodb.by.stackabuse;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

interface ReservationRepository extends ReactiveMongoRepository<Reservation, String> {}
