package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private static final String EMPTY_STRING = "";
    private static final String SPACE_STRING = " ";
    public static final String TASK = "task";
    public static final String MODULE = "module";
    public static final String HELP = "help";
    public static final String ADD = "add";
    public static final String DELETE = "delete";
    public static final String LIST = "list";
    public static final String TIMETABLE = "timetable";
    public static final String EXIT = "bye";
    public static final String MODULETYPE = "/m";
    public static final String ADDCLASS = "addclass";
    public static final String ADDTASK = "addtask";
    public static final String ADDGRADE = "addgrade";
    public static final String DELETECLASS = "deleteclass";
    public static final String DELETETASK = "deletetask";
    public static final String DELETEGRADE = "deletegrade";
    public static final String START_OF_DATE = "/by";

    static String taskType;
    static String taskDescription;
    protected String moduleName;
    protected boolean isExit;
    public static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");

    public Parser() {
        this.isExit = false;
    }

    public void chooseTask(String line) {
        splitInput(line);
        switch (taskType) {
        case HELP:
            //print help Ui
            System.out.println("Help Test");
            break;
        case ADD:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            //add module
            System.out.println("Add Test");
            break;
        case DELETE:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            //delete module
            System.out.println("Delete Test");
            break;
        case LIST:
            if (taskDescription.equals(TASK)) {
                //list task
                System.out.println("List Task Test");
                break;
            }
            if (taskDescription.equals(MODULE)) {
                //list module
                System.out.println("List Module Test");
                break;
            }
            Ui.missingDescription();
            break;
        case TIMETABLE:
            //show timetable
            System.out.println("Timetable Test");
            break;
        case EXIT:
            //Ui print exit message
            System.out.println("Exit Test");
            this.isExit = true;
            break;
        case MODULETYPE:
            //the functions that are module specific
            moduleParser(taskDescription);
            break;
        default:
            System.out.println("Invalid Input!");
            break;
        }
    }

    /**
     * First separate the input into two parts. The first part is saved as moduleName.
     * The next part is split again to obtain the new taskType and taskDescription
     *
     * @param input String to be separated
     */

    void moduleParser(String input) {
        splitInput(input);
        this.moduleName = taskType;
        //access module tasklist
        TaskList tasks = new TaskList();
        System.out.println("Module: " + moduleName);

        if (taskDescription.isEmpty()) {
            Ui.missingDescription();
            return;
        }
        splitInput(taskDescription);

        switch (taskType) {
        case ADDCLASS:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            //moduleName -> addclass method
            System.out.println("AddClass test");
            break;
        case ADDTASK:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            if (!taskDescription.contains(START_OF_DATE)) {
                Ui.invalidDate();
                break;
            }
            tasks.addTask(taskDescription);
            Ui.addTaskMessage(tasks.taskList.get(tasks.getTaskCount()), this.moduleName);
            break;
        case ADDGRADE:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            //moduleName -> addgrade method
            System.out.println("AddGrade test");
            break;
        case DELETECLASS:
            //moduleName -> deleteclass method
            System.out.println("DeleteClass test");
            break;
        case DELETETASK:
            //moduleName -> deletetask method
            System.out.println("DeleteTask test");
            break;
        case DELETEGRADE:
            //moduleName -> deletegrade method
            System.out.println("DeleteGrade test");
            break;
        default:
            System.out.println("Invalid Input!");
            break;
        }
    }

    //split string on first space
    static void splitInput(String input) {
        try {
            int typePos = input.indexOf(SPACE_STRING);
            taskType = input.substring(0, typePos);
            taskDescription = input.substring(typePos).trim();
        } catch (StringIndexOutOfBoundsException e) {
            taskType = input;
            taskDescription = EMPTY_STRING;
        }
    }

    public static LocalDateTime parseDate(String str) {
        return LocalDateTime.parse(str, inputFormatter);
    }

    public static String dateStringOutput(LocalDateTime dateTime) {
        return dateTime.format(outputFormatter);
    }

    public boolean isExit() {
        return this.isExit;
    }

    public String getModuleName() {
        return this.moduleName;
    }

}
