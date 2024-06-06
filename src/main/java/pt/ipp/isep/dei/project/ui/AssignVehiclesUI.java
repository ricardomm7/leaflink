/**
 * This class provides a user interface for assigning vehicles to agenda entries.
 * <p>
 * <p>
 * <p>
 * package pt.ipp.isep.dei.project.ui;
 * <p>
 * import pt.ipp.isep.dei.project.application.controller.AssignVehiclesController;
 * import pt.ipp.isep.dei.project.application.session.ApplicationSession;
 * import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
 * import pt.ipp.isep.dei.project.dto.VehicleDto;
 * <p>
 * import java.util.ArrayList;
 * import java.util.List;
 * import java.util.Scanner;
 * <p>
 * public class AssignVehiclesUI implements Runnable {
 * private final AssignVehiclesController controller;
 * <p>
 * <p>
 * public AssignVehiclesUI() {
 * this.controller = new AssignVehiclesController();
 * }
 * <p>
 * <p>
 * public void assignVehicles() {
 * List<AgendaEntryDto> a = controller.getAgendaEntryList(ApplicationSession.getInstance().getCurrentSession());
 * <p>
 * if (a.isEmpty()) {
 * throw new IllegalArgumentException("The agenda entry is empty.");
 * }
 * int i = 1;
 * for (AgendaEntryDto d : a) {
 * System.out.println(i + d.toString());
 * i++;
 * }
 * Scanner sc = new Scanner(System.in);
 * System.out.println("Select an entry:");
 * int entry = sc.nextInt() - 1;
 * <p>
 * List<VehicleDto> vv = new ArrayList<>();
 * boolean loop = true;
 * List<VehicleDto> availableVehicleList = controller.getVehicleList();
 * if (availableVehicleList.isEmpty()) {
 * throw new IllegalArgumentException("The vehicle list is empty.");
 * }
 * <p>
 * while (loop) {
 * int o = 1;
 * for (VehicleDto d : availableVehicleList) {
 * System.out.println(o + d.toString());
 * o++;
 * }
 * System.out.println("Select a vehicle:");
 * int index = sc.nextInt() - 1;
 * vv.add(availableVehicleList.get(index));
 * controller.setVehicleAvailability(index, false);
 * System.out.println("Do you want to select another vehicle? (Y/N)");
 * String decision = sc.nextLine();
 * loop = decision.trim().equalsIgnoreCase("Y");
 * }
 * controller.updateEntryWithVehicles(entry, availableVehicleList);
 * <p>
 * }
 *
 * @Override public void run() {
 * assignVehicles();
 * }
 * }
 */
/**
 *

 package pt.ipp.isep.dei.project.ui;

 import pt.ipp.isep.dei.project.application.controller.AssignVehiclesController;
 import pt.ipp.isep.dei.project.application.session.ApplicationSession;
 import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
 import pt.ipp.isep.dei.project.dto.VehicleDto;

 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;

 public class AssignVehiclesUI implements Runnable {
 private final AssignVehiclesController controller;


 public AssignVehiclesUI() {
 this.controller = new AssignVehiclesController();
 }


 public void assignVehicles() {
 List<AgendaEntryDto> a = controller.getAgendaEntryList(ApplicationSession.getInstance().getCurrentSession());

 if (a.isEmpty()) {
 throw new IllegalArgumentException("The agenda entry is empty.");
 }
 int i = 1;
 for (AgendaEntryDto d : a) {
 System.out.println(i + d.toString());
 i++;
 }
 Scanner sc = new Scanner(System.in);
 System.out.println("Select an entry:");
 int entry = sc.nextInt() - 1;

 List<VehicleDto> vv = new ArrayList<>();
 boolean loop = true;
 List<VehicleDto> availableVehicleList = controller.getVehicleList();
 if (availableVehicleList.isEmpty()) {
 throw new IllegalArgumentException("The vehicle list is empty.");
 }

 while (loop) {
 int o = 1;
 for (VehicleDto d : availableVehicleList) {
 System.out.println(o + d.toString());
 o++;
 }
 System.out.println("Select a vehicle:");
 int index = sc.nextInt() - 1;
 vv.add(availableVehicleList.get(index));
 controller.setVehicleAvailability(index, false);
 System.out.println("Do you want to select another vehicle? (Y/N)");
 String decision = sc.nextLine();
 loop = decision.trim().equalsIgnoreCase("Y");
 }
 controller.updateEntryWithVehicles(entry, availableVehicleList);

 }

 @Override public void run() {
 assignVehicles();
 }
 }
 */
