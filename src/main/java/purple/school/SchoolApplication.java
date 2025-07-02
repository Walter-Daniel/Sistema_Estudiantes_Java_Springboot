package purple.school;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import purple.school.model.Student;
import purple.school.service.StudentService;

import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class SchoolApplication implements CommandLineRunner {

	@Autowired
	private StudentService studentService;

	private static final Logger logger = LoggerFactory.getLogger(SchoolApplication.class);

	String nl = System.lineSeparator();


	public static void main(String[] args) {
		logger.info("Iniciando la aplicación...");

		//Up spring
		SpringApplication.run(SchoolApplication.class, args);

		logger.info("Aplicación finalizada!!!!");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("ejecutando metodo run de spring!!");
		Boolean exit = false;
		Scanner console = new Scanner(System.in);
		while(!exit){
			showMenu();
			exit = showOptions(console);
			logger.info(nl);
		}//end while
	}

	private void showMenu(){
		logger.info(nl);
		logger.info("""
				
				*** SISTEMA DE ESTUDIANTES ***
				1. Listar estudiantes
				2. Buscar estudiante
				3. Agregar estudiantes
				4. Modificar estudiantes
				5. Eliminar estudiante
				6. Salir
				Elige una opcion:""");
	}

	private boolean showOptions(Scanner console) {
		Integer option = Integer.parseInt(console.nextLine());
		boolean exit = false;
		switch (option){
			case 1 -> {
				logger.info(nl + "Listado de estudiantes: " + nl);
				List<Student> studentList = studentService.getAllStudents();
				studentList.forEach((student -> logger.info(student.toString())));
			}
			case 2 -> {
				logger.info("Indroduce el id del estudiante a buscar: ");
				Integer idStudent = Integer.parseInt(console.nextLine());
				Student student = studentService.findStudentById(idStudent);
				if (student != null) {
					logger.info("Estudiante encontrado: " + student + nl);
				} else {
					logger.info("No se encontró estudiante con el id ingresado." + nl);
				}
			}
			case 3 -> {
				logger.info("Agregar Estudiante: " + nl);
				logger.info("Nombre: ");
				String firstName = console.nextLine();
				logger.info("Apellido: ");
				String lastName = console.nextLine();
				logger.info("Teléfono: ");
				String numberPhone = console.nextLine();
				logger.info("Correo electrónico: ");
				String email = console.nextLine();

				//Create student object
				Student student = new Student();
				student.setFirstName(firstName);
				student.setLastName(lastName);
				student.setEmail(email);
				student.setNumberPhone(numberPhone);

				studentService.saveStudent(student);
				logger.info("Estudiante agregado: " + student + nl);
			}
			case 4 -> {
				logger.info("Modificar estudiante: " + nl);
				logger.info("Ingresa el ID del estudiante: ");
				Integer idStudent = Integer.parseInt(console.nextLine());

				// find student in DB
				Student student = studentService.findStudentById(idStudent);
				if(student != null){
					logger.info("Nombre: ");
					String firstName = console.nextLine();
					logger.info("Apellido: ");
					String lastName = console.nextLine();
					logger.info("Teléfono: ");
					String numberPhone = console.nextLine();
					logger.info("Correo electrónico: ");
					String email = console.nextLine();

					student.setFirstName(firstName);
					student.setLastName(lastName);
					student.setEmail(email);
					student.setNumberPhone(numberPhone);

					studentService.saveStudent(student);
					logger.info("Estudiante modificado: " + student + nl);
				}else {
					logger.info("No se encontró un estudiante con el ID brindado." + nl);
				}
			}
			case 5 -> {
				logger.info("Eliminar estudiante" + nl);
				logger.info("Ingrese el ID del studiante a eliminar: ");
				Integer idStudent = Integer.parseInt(console.nextLine());
				Student student = studentService.findStudentById(idStudent);

				if(student != null){
					studentService.deleteStudent(student);
					logger.info("Se eliminó correctamente el estudiante con id: " + idStudent + nl);
				}else {
					logger.info("No se encontró un estudiante con el ID brindado." + nl);
				}
			}
			case 6 -> {
				logger.info("Hasta pronto!!!" + nl +nl);
				exit = true;
			}
			default -> logger.info("Opcion NO reconocida: " + option + nl);
		}//end switch
		return exit;
	}
}
