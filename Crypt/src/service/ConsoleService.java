package service;

import model.CryptoModel;
import model.CryptoPaths;

import java.util.Scanner;

import static consts.Constants.ENTER_DESTINATION_FILE;
import static consts.Constants.ENTER_KEY;

public class ConsoleService {
    private final Scanner scanner;

    public ConsoleService(Scanner scanner) {
        this.scanner = scanner;
    }
    public CryptoPaths createCryptoPath(){
        CryptoPaths cryptoPaths = new CryptoPaths();
        System.out.println("Адрес зашифрованного файла");
        cryptoPaths.setPathFrom(scanner.nextLine());

        System.out.println("Адрес файла с примером");
        cryptoPaths.setResource(scanner.nextLine());

        System.out.println("Адрес файла с результатом");
        cryptoPaths.setPathTo(scanner.nextLine());
        return cryptoPaths;
    }
    public CryptoModel createCryptoModel (String enterSourceDecryptFile, int mode) {
        CryptoModel cryptoModel = new CryptoModel();

        System.out.println(enterSourceDecryptFile);
        cryptoModel.setPathFrom(scanner.nextLine());

        System.out.println(ENTER_DESTINATION_FILE);
        cryptoModel.setPathTo(scanner.nextLine());

        System.out.println(ENTER_KEY);
        cryptoModel.setKey(scanner.nextInt());
        scanner.nextLine();
        cryptoModel.setMode(mode);
        return cryptoModel;
    }
    public int choose(){
        System.out.println("Введите режим работы 1 - Шифрование, 2 - Дешифрование, 3 - BruteForce");
        int choose = scanner.nextInt();
        scanner.nextLine();
        return choose;
    }
}
