package reactive.by.liukang._5_test_and_debug;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class _3_TestPublisherTest {
  @Test
  public void test_publisher() {
    TestPublisher<Integer> publisher = TestPublisher.<Integer>create().emit(1, 2, 3);
    StepVerifier.create(publisher.flux().map(i -> i * i)).expectNext(1, 4, 9).expectComplete();
  }
}
