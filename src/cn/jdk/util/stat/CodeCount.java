package cn.jdk.util.stat;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CodeCount {
   //����һ�����ϲ�������������е���Ϣͳ���������ǲ�ͳ�ƿ��д���
	private static Map<String,Long> map = new HashMap<String,Long>();
	public static void listCount(File file) throws Exception{  //���д���ͳ��
		if(file.isDirectory()) {   //�ļ���һ��Ŀ¼
			File result[] = file.listFiles();
			for(int x = 0;x<result.length;x++) {
				listCount(result[x]);
			}
		}else {
			if(file.isFile()) {   //����������ļ����жϺ�׺����
				String ext = file.getName().substring(file.getName().lastIndexOf(".")+1);
				switch(ext) {
				case "java":{
					long count = map.get("java");  //����Ѿ����������
					map.put("java", count+rowCount(file));
					break;
				}
				case "jsp":{
					long count = map.get("jsp");  //����Ѿ����������
					map.put("jsp", count+rowCount(file));
					break;
				}
				}
			}
		}
	}
	public static long rowCount(File file) throws Exception{
		long count = 0L;
		Scanner scan = new Scanner(file);
		scan.useDelimiter("\n");
		while(scan.hasNext()) {
			if(scan.next().trim().length()>0) {
				count++;
			}
		}
		scan.close();
		return count;
	}
	public static void main(String args[]) throws Exception{
		map.put("java", 0L);
		map.put("jsp", 0L);
		File file = new File();
		listCount(file);
		System.out.println("java����������"+map.get("java"));
	}
}
