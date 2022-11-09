import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static final int FATOR_KELVIN = 273;
    static final double FATOR_FARENHEIT1 = 9.0/5;
    static final int FATOR_FARENHEIT2 = 32;
    static final double FATOR_FARENHEIT3 = 5.0/9;

    public static void main(String[] args) {
        transformaTemp();
    }

    public static void transformaTemp(){
        Scanner input = new Scanner(System.in);

        int numTemps;

        System.out.println("Digite a quantidade de temperaturas a serem transformadas: ");
        try {
            numTemps = input.nextInt();
        }catch(InputMismatchException e){
            System.err.println("Erro: Insira um número inteiro positivo.");
        }finally {
            numTemps = 0;
        }

        if(numTemps <= 0) {
            System.out.println("Quantidade de temperaturas inválida. Insira um número inteiro positivo.");
            System.exit(0);
        }

        System.out.println("Escolha a unidade de medida de origem (C, K, F): ");
        String unidadeOrigem = input.next();
        System.out.println("Escolha a unidade de medida a ser transformada (C, K, F): ");
        String unidadeTrans = input.next();

        double[] temps = new double[numTemps];
        double[] tempsConv = new double[numTemps];

        for(int i = 0; i < numTemps; i++) {
            System.out.printf("Insira a temperatura %d: \n", i + 1);
            temps[i] = input.nextDouble();
        }

        switch (unidadeOrigem + unidadeTrans) {
            case "CK":
                tempsConv = celsiusToKelvin(temps, numTemps);
                break;
            case "CF":
                tempsConv = celsiusToFarenheit(temps, numTemps);
                break;
            case "FC":
                tempsConv = farenheitToCelsius(temps, numTemps);
                break;
            case "FK":
                tempsConv = farenheitToKelvin(temps, numTemps);
                break;
            case "KC":
                tempsConv = kelvinToCelsius(temps, numTemps);
                break;
            case "KF":
                tempsConv = kelvinToFarenheit(temps, numTemps);
                break;
            default:
                System.out.println("Unidade de temperatura inválida inserida.");
                System.exit(0);
        }
        imprimeTemps(numTemps, temps, tempsConv, unidadeOrigem, unidadeTrans);
    }

    public static void imprimeTemps(int numTemps, double[] temps, double[] tempsConv, String unidOrigem, String unidConv){
        double mediaInicial = 0;
        double mediaConv = 0;

        for(int i = 0; i < numTemps; i++){
            mediaInicial += temps[i];
            mediaConv +=  tempsConv[i];

            System.out.printf("Temperatura %d: %.2f%s.\n", i+1, temps[i], unidOrigem);
            System.out.printf("Temperatura %d convertida: %.2f%s.\n", i+1, tempsConv[i], unidConv);
        }

        System.out.printf("Média das temperaturas iniciais: %.2f.\n", mediaInicial/numTemps);
        System.out.printf("Média das temperaturas convertidas: %.2f.\n", mediaConv/numTemps);
    }

    public static double[] celsiusToKelvin(double[] C, int numTemps){
        double[] tempsConv = new double[numTemps];

        for(int i = 0; i < numTemps; i++)
            tempsConv[i] = C[i] + FATOR_KELVIN;

        return tempsConv;
    }

    public static double[] kelvinToCelsius(double[] K, int numTemps){
        double[] tempsConv = new double[numTemps];

        for(int i = 0; i < numTemps; i++)
            tempsConv[i] = K[i] - FATOR_KELVIN;

        return tempsConv;
    }

    public static double[] celsiusToFarenheit(double[] C, int numTemps){
        double[] tempsConv = new double[numTemps];

        for(int i = 0; i < numTemps; i++)
            tempsConv[i] = ((FATOR_FARENHEIT1*C[i]) + FATOR_FARENHEIT2);

        return tempsConv;
    }

    public static double[] farenheitToCelsius(double[] F, int numTemps){
        double[] tempsConv = new double[numTemps];

        for(int i = 0; i < numTemps; i++)
            tempsConv[i] = ((F[i] - FATOR_FARENHEIT2)/FATOR_FARENHEIT1);

        return tempsConv;
    }

    public static double[] kelvinToFarenheit(double[] K, int numTemps){
        double[] tempsConv = new double[numTemps];

        for(int i = 0; i < numTemps; i++)
            tempsConv[i] = ((K[i] - FATOR_KELVIN)*FATOR_FARENHEIT1 + FATOR_FARENHEIT2);

        return tempsConv;
    }

    public static double[] farenheitToKelvin(double[] F, int numTemps){
        double[] tempsConv = new double[numTemps];

        for(int i = 0; i < numTemps; i++)
            tempsConv[i] = ((F[i] - 32)*FATOR_FARENHEIT3 + FATOR_KELVIN);

        return tempsConv;
    }
}