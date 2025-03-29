package br.edu.ufra.validadorcpf;

import java.util.Scanner;

public class Cpf {

    public static boolean validarCPF(String cpf) {
        // Remove os caracteres especiais (pontos e hífen)
        cpf = cpf.replaceAll("[^0-9]", "");//Expressão regular usada no código

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;  // Verifica se o CPF é uma sequência de números repetidos (exemplo: 111.111.111-11)
        }

        // Valida os dois últimos dígitos (dígitos verificadores)
        int soma = 0;
        int peso = 10;

        // Validação do primeiro dígito verificador
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso--;
        }
        int primeiroDigito = (soma % 11 < 2) ? 0 : 11 - (soma % 11);
        if (primeiroDigito != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        // Validação do segundo dígito verificador
        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso--;
        }
        int segundoDigito = (soma % 11 < 2) ? 0 : 11 - (soma % 11);
        return segundoDigito == Character.getNumericValue(cpf.charAt(10));
    }

    public static void main(String[] args) {
        // Cria um objeto Scanner para capturar entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Solicita que o usuário digite o CPF
        System.out.print("Digite o CPF (formato xxx.xxx.xxx-xx): ");
        String cpf = scanner.nextLine();  // Lê a linha do CPF digitado pelo usuário

        // Chama o método para validar o CPF
        if (validarCPF(cpf)) {
            System.out.println("CPF válido.");
        } else {
            System.out.println("CPF inválido.");
        }

        // Fecha o scanner
        scanner.close();
    }
}
