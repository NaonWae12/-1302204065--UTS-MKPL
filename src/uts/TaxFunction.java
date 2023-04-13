/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts;

/**
 *
 * @author Saeful anwar
 */
public class TaxFunction {

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus
     * dibayarkan setahun.
     * 
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan
     * bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan)
     * dikurangi penghasilan tidak kena pajak.
     * 
     * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena
     * pajaknya adalah Rp 54.000.000.
     * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah
     * sebesar Rp 4.500.000.
     * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya
     * ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
     * 
     */

    private static final int MAX_NUM_CHILDREN = 3;
    private static final int MARRIED_DEDUCTIBLE = 58500000;
    private static final int SINGLE_DEDUCTIBLE = 54000000;
    private static final int CHILD_DEDUCTIBLE = 1500000;

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus
     * dibayarkan setahun.
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible,
            boolean isMarried, int numberOfChildren) {
        int tax = 0;
        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }
        numberOfChildren = Math.min(numberOfChildren, MAX_NUM_CHILDREN);
        int taxableIncome = calculateTaxableIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking, deductible,
                isMarried, numberOfChildren);
        tax = (int) Math.round(0.05 * taxableIncome);
        return Math.max(tax, 0);
    }

    /**
     * Fungsi untuk menghitung penghasilan kena pajak.
     */
    private static int calculateTaxableIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking,
            int deductible, boolean isMarried, int numberOfChildren) {
        int taxableIncome = ((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible;
        taxableIncome -= getDeductible(isMarried, numberOfChildren);
        return taxableIncome;
    }

    /**
     * Fungsi untuk menghitung pengurangan pajak berdasarkan kondisi pernikahan dan
     * jumlah anak.
     */
    private static int getDeductible(boolean isMarried, int numberOfChildren) {
        int deductible = 0;
        if (isMarried) {
            deductible += MARRIED_DEDUCTIBLE;
        } else {
            deductible += SINGLE_DEDUCTIBLE;
        }
        deductible += numberOfChildren * CHILD_DEDUCTIBLE;
        return deductible;
    }
}