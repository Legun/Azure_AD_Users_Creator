package transliterator;
import com.ibm.icu.text.Transliterator;

public class Main extends executor{

public static String CYRILLIC_TO_LATIN = "Cyrillic-Latin";

public static void main(String[] args) {
	String realName ="'"+ args[0]+ " " + args[1]+"'";
	System.out.println(realName);
	
	PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
	        .useDigits(true)
	        .useLower(true)
	        .useUpper(true)
	        .build();
	String password = passwordGenerator.generate(8); // output ex.: lrU12fmM 75iwI90o
	
	System.out.println("Password= " + password);
	
    Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
    String secondName = toLatinTrans.transliterate(args[0]);
    String firstName = toLatinTrans.transliterate(args[1]);
    String principalUserName = secondName + "." + firstName + "@smartcredit.ru";
    System.out.println(principalUserName);
    
    String command = "az ad user create --display-name " + realName + " --password "+ password + " --user-principal-name " + principalUserName + " --force-change-password-next-login false";
  System.out.println(command);
    // String command =  "az --help";
    
   try {
	   String out=executeCommand(command);
	   System.out.println(out);
   }catch (Exception e) {
	   e.printStackTrace();
}
    	
   

	}
		
	
}