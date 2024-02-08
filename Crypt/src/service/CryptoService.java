package service;

import exceptions.FileCreateException;
import exceptions.FileReadException;
import exceptions.FileWriteException;
import exceptions.UnsupportedFileException;
import model.CryptoModel;
import model.CryptoPaths;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static consts.Constants.ALPHABET;

public class CryptoService {
        FileService fileService;
        public CryptoService(FileService fileService) {
                this.fileService = fileService;
        }
        public void execute(CryptoModel cryptoModel){
                Path file = Path.of(cryptoModel.getPathFrom());
                try {
                        List<String> fileText = fileService.readFromPath(file, false );
                        for(int j = 0; j < fileText.size(); j++){
                                StringBuilder tempLine = new StringBuilder();
                                char[] charArray = fileText.get(j).toCharArray();
                                for (char letter : charArray) {
                                        boolean isFound = false;
                                        for (int i = 0; i < ALPHABET.length; i++) {
                                                if (Character.toLowerCase(letter) == ALPHABET[i]) {
                                                        if(cryptoModel.getMode() == CryptoModel.Mode.ENCODE) {
                                                                tempLine.append(ALPHABET[(i + cryptoModel.getKey()) % ALPHABET.length]);
                                                        } else if(cryptoModel.getMode() == CryptoModel.Mode.DECODE){
                                                                tempLine.append(ALPHABET[(ALPHABET.length + (i - cryptoModel.getKey())) % ALPHABET.length]);
                                                        }
                                                        isFound = true;
                                                        break;
                                                }
                                        }
                                        if(!isFound){
                                                tempLine.append(letter);
                                        }
                                }
                                fileText.set( j, String.valueOf(tempLine));
                        }
                        fileService.writeToPath(Path.of(cryptoModel.getPathTo()), fileText );
                } catch (FileReadException | UnsupportedFileException | FileWriteException | FileCreateException e) {
                        System.out.println(e.getMessage());
                }
        }

        public void brute(CryptoPaths cryptoPaths){
                Path file = Path.of(cryptoPaths.getPathFrom());
                try {
                        List<String> fileText = fileService.readFromPath(file, false);
                        List<String> dictText = fileService.readFromPath(Path.of(cryptoPaths.getResource()), false);
                        for (int k = 0; k < ALPHABET.length; k++){
                                List<String> tempText = new ArrayList<>();
                                for (String s : fileText) {
                                        StringBuilder tempLine = new StringBuilder();
                                        char[] charArray = s.toCharArray();
                                        for (char letter : charArray) {
                                                boolean isFound = false;
                                                for (int i = 0; i < ALPHABET.length; i++) {
                                                        if (Character.toLowerCase(letter) == ALPHABET[i]) {
                                                                tempLine.append(ALPHABET[(ALPHABET.length + (i - k)) % ALPHABET.length]);
                                                                isFound = true;
                                                                break;
                                                        }
                                                }
                                                if (!isFound) {
                                                        tempLine.append(letter);
                                                }
                                        }
                                        tempText.add(String.valueOf(tempLine));
                                }
                                int countExist = 0;
                                int countNotExist = 0;
                                for (String line : tempText) {
                                        if(countNotExist == 10){
                                                break;
                                        }
                                       String[] words = line.split(" ");
                                       for(String word : words){
                                           if(dictText.contains(word.replaceAll("[^А-ЯЁа-яё]+",""))){
                                                   countExist++;
                                           }  else {
                                                   countNotExist++;
                                           }
                                           if(countExist == 3){
                                                   fileService.writeToPath(Path.of(cryptoPaths.getPathTo()), tempText);
                                                   System.out.println("Ключ: " + k);
                                                   return;
                                           }
                                           if(countNotExist == 10){
                                                   break;
                                           }
                                       }
                                }
                        }
                } catch (FileReadException | UnsupportedFileException | FileWriteException | FileCreateException e) {
                        System.out.println(e.getMessage());
                }
        }
}