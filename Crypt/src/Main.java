import service.ConsoleService;
import service.CryptoService;
import service.FileService;

import java.util.Scanner;

import static consts.Constants.ENTER_SOURCE_CRYPT_FILE;
import static consts.Constants.ENTER_SOURCE_DECRYPT_FILE;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileService fileService = new FileService();
        CryptoService cryptoService = new CryptoService(fileService);
        ConsoleService consoleService = new ConsoleService(scanner);

        int value = consoleService.choose();

        switch (value) {
            case 1: {
                cryptoService.execute(consoleService.createCryptoModel(ENTER_SOURCE_CRYPT_FILE, 1));
                break;
            }
            case 2: {
                cryptoService.execute(consoleService.createCryptoModel(ENTER_SOURCE_DECRYPT_FILE, 2));
                break;
            }
            case 3:{
                cryptoService.brute(consoleService.createCryptoPath());
                break;
            }
        }

    }
}