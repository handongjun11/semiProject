package semi.kh.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;
/**
 * 
 * 1.중복된 파일 덮어쓰기 방지
 * 2.한글/특수문자가 섞인 파일명의 에러방지
 *  -> 사용자가 업로드한 원래 파일명은 db에 보관함.
 * (여러 에러날일 없는 문자 rename이름으로 등록이 된다. 원래 파일 이름은 original에 보관 //파일이 다운로드할때는 original로 보낸다.)
 * 
 * 곽경국.jpg -> 20181220_163230567_34436.jpg //덮어쓰기 방지
 * 
 * originalFileName : 곽경국.jpg
 * renamedFileName :  20181220_163230567_34436.jpg
 * 
 *
 */
public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oFile) {
		File rFile = null;
		//yyyyMMdd_HHmmssSSS 시분초밀리세컨즈
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
		int rndNum = (int)(Math.random()*1000) ; //0~999까지 난수 발생
		
		//★확장자★
		String oFileName = oFile.getName(); // 인자로 받은 oFile에서 파일명 가져오기
		String ext = ""; //확장자 받기 : ex).jpg
		int dot = oFileName.lastIndexOf('.');
		if(dot > -1) {
			ext = oFileName.substring(dot); //처음부터 끝까지 가져오기	
		}
		
		//새파일명 생성
		String rFileName = sdf.format(new Date()) + "_"+rndNum+ext; //아까 만든 format에다가 현재 날짜 집어넣기 + _ + 난수 + 확장자명
		
		//renamed파일객체 생성
		//부모디렉토리 , renamedFileName
		rFile = new File(oFile.getParent() , rFileName); //부모 디렉토리에 rFilename전달
		//메모리 상에 존재하는 파일객체지 파일자체가 아니다.
		
		System.out.printf("[rFileName = %s]\n", rFile.getName());
		
		//위에는 객체만 있는 것이다. 실제 파일 x
		//그렇기 때문에 실제 생성 해야한다.
		try {
			//실제파일 생성
			rFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return rFile;
	}

}
