import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class AutoCreateReadme {
	private static final String START_FILE_PATH = "D:\\hand\\markdown文档";
	private static final String TARGET_FILE_PATH = "D:/";
	private static final String TARGET_FILE_NAME = "ReadMe.md";
	
	public static void main(String[] args) {
		File file = new File(START_FILE_PATH);
		if(file != null&&file.exists()) {
			StringBuilder context = new StringBuilder();
			context.append("<span id=\"head\"></span>\n") 
					.append("\n") 
					.append("# 目录");
			File[] listFiles = file.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				appendList(context, listFiles[i], 2);
			}
			context.append("\n").append("---\n" + 
					"\n" + 
					"### [TOP](#head)");
			System.err.println(context.toString());
			File outFile = new File(TARGET_FILE_PATH+TARGET_FILE_NAME);
			OutputStream outputStream = null;
			try{
				outputStream = new FileOutputStream(outFile);
				outputStream.write(context.toString().getBytes(Charset.forName("UTF8")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					outputStream.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void appendList(StringBuilder context,File file,int titleSize) {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < titleSize; i++) {
			sBuilder.append("#");
		}
		StringBuilder hn = sBuilder.append(" ");
		if(file != null&&file.exists()) {
			if(file.isDirectory()) {
				if(file.getName().endsWith(".image") || file.getName().endsWith(".git") || file.getName().endsWith(".vscode")) return;
				context
				.append("\n").append("\n")
				.append(hn)
				.append(file.getName())
				.append("\n");
				File[] listFiles = file.listFiles();
				for (int i = 0; i < listFiles.length; i++) {
					appendList(context,listFiles[i],titleSize+1);
				}
			}else {
				if(file.getName().toLowerCase().endsWith(".md") || file.getName().toLowerCase().equals("readme.md")) {
					context
					.append("\n")
					.append(">* [")
					.append(file.getName())
					.append("](.")
					.append(file.getAbsolutePath().replace(START_FILE_PATH, ""))
					.append(")");
				}
			}
		}
	}
}
