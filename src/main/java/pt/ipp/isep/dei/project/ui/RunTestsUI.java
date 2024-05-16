package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RunTestsController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The RunTestsUI class provides a user interface for running tests.
 */
public class RunTestsUI implements Runnable {
    private String folderpath;

    /**
     * Executes the tests.
     *
     * @throws FileNotFoundException if the specified file is not found.
     */
    public void execute() throws FileNotFoundException {
        RunTestsController g1 = new RunTestsController();

        g1.execute(seeFiles(folderpath));
    }

    /**
     * Prompts the user to upload a file.
     */
    public void uploadFile() {
        System.out.print("Enter the folder path with all the CSV files: ");
        Scanner sc = new Scanner(System.in);
        this.folderpath = sc.nextLine();
    }

    /**
     * This method opens the folder and records all the files in a list.
     *
     * @param folder the folderpath
     * @return the list containing the files in a folder.
     */
    public List<String> seeFiles(String folder) {
        List<String> fileList = new ArrayList<>();

        File directory = new File(folder);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String fileName = file.getName();
                        int dotIndex = fileName.lastIndexOf('.');
                        if (dotIndex > 0) {
                            fileName = folder+"/"+fileName.substring(0, dotIndex);
                        }
                        fileList.add(fileName);
                    }
                }
            }
        }

        return fileList;
    }

    /**
     * Runs the user interface for running the tests.
     */
    @Override
    public void run() {
        uploadFile();
        try {
            execute();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
