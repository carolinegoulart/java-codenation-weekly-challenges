package br.com.codenation.calculadora;


public class CalculadoraSalario {

	final double SALARIO_MINIMO = 1039.00;

	final double TAXA_INSS_01 = 0.08;
	final double TAXA_INSS_02 = 0.09;
	final double TAXA_INSS_03 = 0.11;

	final double TAXA_IRFF_01 = 0.0;
	final double TAXA_IRFF_02 = 0.075;
	final double TAXA_IRFF_03 = 0.15;

	public long calcularSalarioLiquido(double salarioBase) {
		if (salarioBase < SALARIO_MINIMO) {
			return (long)0.0;
		}
		double salarioAposDescontoInss = calcularInss(salarioBase);
		double salarioAposDescontoIrff = calcularIrff(salarioAposDescontoInss);
		return Math.round(salarioAposDescontoIrff);
	}

	public double calcularIrff(double salarioBase) {
		if (salarioBase <= 3000.00) {
			return salarioBase * (1 - TAXA_IRFF_01);
		} else if (salarioBase <= 6000.00) {
			return salarioBase * (1 - TAXA_IRFF_02);
		} else {
			return salarioBase * (1 - TAXA_IRFF_03);
		}
	}

	public double calcularInss(double salarioBase) {
		if (salarioBase <= 1500.00) {
			return salarioBase * (1 - TAXA_INSS_01);
		} else if (salarioBase <= 4000.00) {
			return salarioBase * (1 - TAXA_INSS_02);
		} else {
			return salarioBase * (1 - TAXA_INSS_03);
		}
	}
}
