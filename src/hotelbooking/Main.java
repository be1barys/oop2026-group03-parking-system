package hotelbooking;

import hotelbooking.entities.Reservation;
import hotelbooking.entities.Room;
import hotelbooking.exceptions.InvalidDateRangeException;
import hotelbooking.repositories.*;
import hotelbooking.services.*;
import hotelbooking.repositories.GuestRepository;
import hotelbooking.repositories.GuestRepositoryImpl;


import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RoomRepository roomRepo = new RoomRepositoryImpl();
        ReservationRepository reservationRepo = new ReservationRepositoryImpl();
        PaymentRepository paymentRepo = new PaymentRepositoryImpl();
        GuestRepository guestRepository = new GuestRepositoryImpl();


        RoomAvailabilityService roomService = new RoomAvailabilityService(roomRepo);
        PaymentService paymentService = new PaymentService();
        ReservationService reservationService =
                new ReservationService(roomRepo, reservationRepo, paymentRepo, paymentService);

        while (true) {
            System.out.println("""
                1) Search available rooms by date
                2) Create a reservation
                3) Cancel a reservation
                4) Exit
            """);

            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Check-in (YYYY-MM-DD): ");
                        LocalDate in = LocalDate.parse(scanner.next());

                        System.out.print("Check-out (YYYY-MM-DD): ");
                        LocalDate out = LocalDate.parse(scanner.next());

                        List<Room> rooms = roomService.search(in, out);

                        if (rooms.isEmpty()) {
                            System.out.println("No available rooms.");
                        } else {
                            System.out.println("Available rooms:");
                            for (Room r : rooms) {
                                System.out.println("ID: " + r.getId()
                                        + " | " + r.getNumber()
                                        + " | " + r.getType()
                                        + " | price/night=" + r.getPricePerNight());
                            }
                        }
                    }

                    case 2 -> {
                        scanner.nextLine(); // consume newline

                        System.out.print("Guest name: ");
                        String name = scanner.nextLine();

                        System.out.print("Guest email: ");
                        String email = scanner.nextLine();

                        int guestId = guestRepository.getOrCreateGuestId(name, email);

                        System.out.print("Check-in (YYYY-MM-DD): ");
                        LocalDate in = LocalDate.parse(scanner.next());

                        System.out.print("Check-out (YYYY-MM-DD): ");
                        LocalDate out = LocalDate.parse(scanner.next());

                        // validate dates using your existing RoomAvailabilityService
                        List<Room> available = roomService.search(in, out);
                        if (available.isEmpty()) {
                            System.out.println("No rooms available.");
                            break;
                        }

                        Room chosen = available.get(0); // auto room
                        long nights = java.time.temporal.ChronoUnit.DAYS.between(in, out);
                        double totalPrice = chosen.getPricePerNight() * nights;
                        double minDeposit = totalPrice * 0.20;

                        System.out.println("Auto-selected Room ID: " + chosen.getId() + " (" + chosen.getNumber() + ")");
                        System.out.println("Nights: " + nights);
                        System.out.println("Total price: " + totalPrice);
                        System.out.println("Minimum deposit (20%): " + minDeposit);

                        System.out.print("Enter deposit amount: ");
                        double deposit = scanner.nextDouble();

                        int newId = reservationService.createReservationAutoRoom(guestId, in, out, deposit);
                        System.out.println("Reservation created! ID = " + newId);
                    }


                    case 3 -> {
                        System.out.print("Reservation ID: ");
                        int id = scanner.nextInt();

                        Reservation r = reservationService.getReservation(id);
                        if (r == null) {
                            System.out.println("Reservation not found.");
                            break;
                        }

                        boolean ok = reservationService.cancelReservation(id);
                        if (!ok) {
                            System.out.println("Reservation not found.");
                            break;
                        }

                        double fee = r.getTotalPrice() * 0.20;
                        System.out.println("Reservation cancelled. Cancellation fee kept (20%): " + fee);
                    }


                    case 4 -> {
                        System.out.println("Bye!");
                        return;
                    }

                    default -> System.out.println("Invalid option.");
                }
            } catch (InvalidDateRangeException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
