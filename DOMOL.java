import java.io.Console;


public class DOMOL{

    static String input; 
    static int i=0; 
    static int count = 0; 
    public static void main(String[] args) {
        Console console = System.console(); 
        if(console == null){
            System.err.println("Nincs konzol!");
            System.exit(1);
        }

        System.out.println("Adja meg a kifejezést!");
        try{
        input = console.readLine();
      

        for(i = 0; i<input.length(); i++){
            if(Character.isDigit(input.charAt(i))==true){
                szamban();
            }

            else if(Character.isLetter(input.charAt(i))){
                azonositoban();
            }

            else{
                switch(input.charAt(i)){
                    case ':':
                        kettospont();
                        break;
                    case '<':
                        kisebben();
                        break; 
                    case '>':
                        nagyobban();
                        break; 
                    case '{':
                        kommentben();
                        break;
                    case '(':
                        kerek_kommentben();
                        break; 
                    case '$':
                        break;
                    default:
                        hiba();
                        break;
                }   
            }
        }
    }
    catch(IndexOutOfBoundsException e){
        System.out.println("A kifejezést $-el kell zárni.");
    }
    }

    public static void azonositoban(){ 
        i++; 
        if(Character.isDigit(input.charAt(i))==true || Character.isLetter(input.charAt(i)) == true)
            azonositoban();
        else
            azonosito_vege();
    }

    public static void azonosito_vege(){
        i--; 
        System.out.println("<azonosito>");
        if(input.charAt(i)=='$'){System.exit(1);}
    }

    public static void szamban(){
        i++;
        if(Character.isDigit(input.charAt(i))==true)
            szamban();
        else
            szam_vege();
    }

    public static void szam_vege() {
        i--;
        System.out.println("<szám>");
        if(input.charAt(i)=='$'){System.exit(1);}
    }

    public static void kettospont() {
        if(input.charAt(i+1)=='='){
            System.out.println("<:= operátor>");
        }
        else{
            hiba();

        }
        if(input.charAt(i)=='$'){System.exit(1);}  
    }

    public static void kisebben() {
        i++;
        if(input.charAt(i)=='>')
            kisebben_nagyobb();
        else if (input.charAt(i)=='=')
            kisebben_egyenlo();
        else{
            hiba();
            i--;
        }
    }

    public static void kisebben_nagyobb() {
        System.out.println("<<> operátor");
        if(input.charAt(i)=='$'){System.exit(1);}  
    }

    public static void kisebben_egyenlo() {
        System.out.println("<<= operátor>");
        if(input.charAt(i)=='$'){System.exit(1);} 
    }

    public static void nagyobban() {
        i++;
        if(input.charAt(i)=='=')
            System.out.println("<>= operátor>");
        else{
            hiba();
            i--;
        }
        if(input.charAt(i)=='$'){System.exit(1);} 
    }

    public static void kommentben() {
        i++;
        count=0;
        if(input.charAt(i)=='}')
            komment_vege();
        else if(input.charAt(i)=='$'){
            hiba();
            count++;
        }
        else
            kommentben();
        i-=count;
    }

    public static void komment_vege(){
        System.out.println("<{} kommentár>");
        if(input.charAt(i)=='$'){System.exit(1);} 
    }

    public static void kerek_kommentben() {
        i++;
        if(input.charAt(i)=='*')
            kerek_csillag_kommentben();
        else
            hiba();
    }

    public static void kerek_csillag_kommentben(){
        i++;
        count=0;
        if(input.charAt(i)==')'&&input.charAt(i-1)=='*'){
            kerek_csillag_komment_vege();
        }
        else if(input.charAt(i)=='$'){
            hiba();
            count++;
        }
        else{
            kerek_csillag_kommentben();
        }
        i-=count;
    }

    public static void kerek_csillag_komment_vege() {
        System.out.println("<(**) kommentár>");
        if(input.charAt(i)=='$'){System.exit(1);} 
    }

    public static void hiba(){
        System.out.println("<hiba>");
    }

    
    
   
}