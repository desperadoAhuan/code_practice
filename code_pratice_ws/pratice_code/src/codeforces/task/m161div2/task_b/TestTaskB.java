package codeforces.task.m161div2.task_b;

import java.io.PrintWriter;
import java.util.Scanner;

public class TestTaskB {

	public static void main(String[] s) {
		Scanner in = new Scanner(TestTaskB.class.getResourceAsStream("testC.txt"));
		PrintWriter out = new PrintWriter(System.out);

		new TaskB().solve(in, out);

		in.close();
		out.close();
	}
}
