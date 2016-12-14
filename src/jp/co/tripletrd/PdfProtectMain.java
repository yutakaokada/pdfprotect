package jp.co.tripletrd;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;;
public class PdfProtectMain {

	/**
	 * �������F�ϊ����f�B���N�g��
	 * �������F�ϊ���f�B���N�g�� ��
	 * ��O�����F�Ώۃt�@�C�����i�ȗ��\�j
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		if(args.length<2) {
			System.out.println("invalid args. this program requires more than 2 args");
			System.exit(-1);
			return;
		}
		String path_from = args[0];
		String path_to = args[1];
		String file_name=null;
		if(args.length>=3) file_name=args[2];

		String[] file_names=null;
		if(file_name!=null){
			file_names=new String[]{file_name};
		}else{
			file_names=new File(path_from).list();
		}

		for(String f : file_names){

			String from = path_from + File.separator + f;
			if( !new File(from).exists() ){
				System.out.println("target file does not exist["+from+"]");
				System.exit(-1);
				return;
			}
			String to=path_to+File.separator+f;

			try{
				doProtect(from, to);
			}catch(Exception e){
				System.out.println("exception occurred while protecting pdf file ["+ from + "]");
				e.printStackTrace();
				System.exit(-1);
				return;
			}
		}

		System.out.println(file_names.length+" files is successfully protected");
		System.exit(0);

	}

	public static void doProtect(String from , String to) throws Exception{
		PdfReader reader = null;

		reader = new PdfReader(from);

		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(to));

		stamper.setEncryption(null,null,~(PdfWriter.ALLOW_PRINTING),PdfWriter.STANDARD_ENCRYPTION_128);

		stamper.close();

		reader.close();


	}

}
