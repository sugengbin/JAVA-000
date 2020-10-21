import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * 
 * Created by gengbinsu 2020年10月21日
 */
public class MyClassLoader extends ClassLoader {

	private String path;

	public MyClassLoader(String fileName) {
		path = fileName;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class clazz = null;
		byte[] classData = readClassData();
		if (Objects.nonNull(classData)) {
			clazz = defineClass(name, classData, 0, classData.length);
		}
		return clazz;
	}

	/**
	 * read from file
	 * 
	 * @return
	 */
	private byte[] readClassData() {
		try (InputStream in = MyClassLoader.class.getResourceAsStream(path);
			 ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[1024];
			int read = 0;
			int index = 0;
			while ((read = in.read()) != -1) {
				buffer[index++] = (byte) (255 - read);
			}
			out.write(buffer, 0, index);
			return out.toByteArray();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MyClassLoader myClassLoader = new MyClassLoader("Hello.xlass");
			Class<?> target = myClassLoader.loadClass("Hello");
			target.getDeclaredMethod("hello").invoke(target.newInstance());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}