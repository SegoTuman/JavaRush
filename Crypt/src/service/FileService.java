package service;
import exceptions.FileCreateException;
import exceptions.FileReadException;
import exceptions.FileWriteException;
import exceptions.UnsupportedFileException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileService {
    public List<String> readFromPath(Path path, boolean needToCheckFile) throws FileReadException, UnsupportedFileException {
        List<String> file;

        try {
            int size = 0;
            file = new ArrayList<>(Files.readAllLines(path));
            if(needToCheckFile) {
                for (String line : file) {
                    size += line.length();
                }
                if (size < 1000) {
                    throw new UnsupportedFileException("Файл слишком маленький");
                }
            }
        } catch (IOException e) {
            throw new FileReadException("Не удалось прочитать файл");
        }
        return file;
    }
    public void writeToPath(Path path, List<String> fileText) throws FileCreateException, FileWriteException {
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new FileCreateException("Не удалось создать файл");
        }
        try {
            Files.write(path, fileText, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileWriteException("Не удалось записать в файл");
        }
    }

}
