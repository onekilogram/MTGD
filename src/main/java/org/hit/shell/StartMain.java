package org.hit.shell;

import java.io.IOException;
import java.util.Scanner;

public class StartMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		MainUtils.showHelp();

		// System.out.println();
		Scanner scanner = new Scanner(System.in);

		System.out.print(">");
		String shell = scanner.nextLine();

		String[] shells = shell.split("\\s+");

		Long l = StrategyUtils.getDefaultBlockSize();
		
		System.out.println("default block size is "+l);
		
		while (shells[0] != null && !shells[0].equals("-e")) {
			System.out.println(shell);
			System.out.print(">");
			shell = scanner.nextLine();
			shells = shell.split("\\s+");
		};
	}
}
