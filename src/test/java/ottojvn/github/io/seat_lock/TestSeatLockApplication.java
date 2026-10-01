package ottojvn.github.io.seat_lock;

import org.springframework.boot.SpringApplication;

public class TestSeatLockApplication {

	public static void main(String[] args) {
		SpringApplication.from(SeatLockApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
