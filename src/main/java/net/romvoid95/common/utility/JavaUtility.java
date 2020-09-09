package net.romvoid95.common.utility;

public class JavaUtility extends SecurityManager {

	public static JavaUtility INSTANCE = new JavaUtility();

	public boolean isCalledBy (Class<?>... clazz) {
		Class<?>[] context = getClassContext();

		int imax = Math.min(context.length, 6);
		for (int i = 2; i < imax; i++) {
			Class<?> test = context[i];
			for (Class<?> cl : clazz) {
				if (test == cl) {
					return true;
				}
			}
		}
		return false;
	}
}
